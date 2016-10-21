package com.shixia.diudiuma.presenter;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import com.shixia.diudiuma.MyApplication;
import com.shixia.diudiuma.activity.SellCarActivity;
import com.shixia.diudiuma.common_utils.FileUtils;
import com.shixia.diudiuma.common_utils.ImageFactory;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.http.base.HttpApiBase;
import com.shixia.diudiuma.http.download_apk.DownloadApkApi;
import com.shixia.diudiuma.http.download_apk.DownloadApkHttpRequest;
import com.shixia.diudiuma.http.download_apk.DownloadApkHttpResponse;
import com.shixia.diudiuma.http.upload.UploadApi;
import com.shixia.diudiuma.http.upload.UploadHttpRequest;
import com.shixia.diudiuma.http.upload.UploadHttpResponse;
import com.shixia.diudiuma.iview.SellCarIView;
import com.shixia.diudiuma.iview.base.BaseIView;
import com.shixia.diudiuma.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.LoadingDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class PresenterSellCar extends BasePresenter {
    private Context context;
    private SellCarIView iView;

    private List<String> selectedPhotoList;

    public PresenterSellCar(Context context, BaseIView iView) {
        super(context, iView);
        this.context = context;
        this.iView = (SellCarIView) iView;
    }

    public void showPhonePicker(){
        PermissionUtils.initCurrentActivity((SellCarActivity)context,this);
        PermissionUtils.doBizAfterRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,PermissionUtils.READ_EXTERNAL_STORAGE);
        //申请权限通过则执行doBizWithPermissionRequest()中的内容
    }

    /**
     * 上传图片的操作
     */
    public void uploadPic(){
        selectedPhotoList = ((SellCarActivity)context).selectedPhotos;
        Observable
                .create((Observable.OnSubscribe<List<String>>) subscriber -> {
                    subscriber.onNext(getCopyPhotoUriList(selectedPhotoList));
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(stringList -> {
                    if(stringList != null && stringList.size() > 0){
                        MyApplication.UIHandler.post(() -> LoadingDialog.getInstance(context,"上传文件中……").show());
                        UploadApi uploadApi = new UploadApi();
                        UploadHttpRequest uploadHttpRequest = new UploadHttpRequest(stringList);
                        uploadApi.setRequest(uploadHttpRequest);
                        uploadApi.multFilePost();
                        uploadApi.setOnJsonHttpResponseListener(new HttpApiBase.JsonHttpResponseListener<UploadHttpResponse>() {
                            @Override
                            public void onFailure(Exception e, String rawJsonString) {
                                iView.onShowRemind("上传文件失败");
                            }

                            @Override
                            public void onSuccessful(UploadHttpResponse uploadHttpResponse, String rawJsonString) {
                                iView.onShowRemind("上传文件成功");
                                deleteTempFile(stringList);
                            }

                            //上传文件成功之后删除临时文件
                            private void deleteTempFile(List<String> stringList) {
                                for (String tempFileUri : stringList) {
                                    FileUtils.deleteFile(tempFileUri);
                                }
                            }
                        });
                    }
                });

    }

    private List<String> getCopyPhotoUriList(List<String> filePath) {
        MyApplication.UIHandler.post(() -> LoadingDialog.getInstance(context,"文件压缩中").show());
        List<String> picUriList = new ArrayList<>();
        if(filePath == null || filePath.size() == 0){
            return null;
        }
        for (String s : filePath) {
            // TODO: 2016/10/19 利用线程池压缩图片
            try {
                String picUri = copyBitmap(s);
                picUriList.add(picUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MyApplication.UIHandler.post(() -> LoadingDialog.getInstance(context,"文件压缩中").dismiss());
        return picUriList;
    }

    //创建Bitmap副本用于压缩，压缩完成之后删除
    private String copyBitmap(String filePath) throws IOException {
        //得到文件后缀
        L.i("fiel path",filePath);
        String[] split = filePath.split("\\.");
        String fileType = split[split.length - 1];
        if(TextUtils.isEmpty(fileType)){
            fileType = "jpg";
        }

        //copy的文件名及文件路径
        String copyFileName = String.valueOf(System.currentTimeMillis());
        String newFilePath = Environment.getExternalStorageDirectory() + "/" + copyFileName + "." + fileType;

        //压缩图片
        ImageFactory imageFactory = new ImageFactory();
        imageFactory.compressAndGenImage(filePath,newFilePath,200,false);
        //返回新的图片地址List
        return newFilePath;

        /*FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bufis = new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream(newFilePath);
        //复制后的文件全名
        L.i("copy path",newFilePath);

        BufferedOutputStream bufos = new BufferedOutputStream(fos);

        int len ;
        while ((len = bufis.read()) != -1) {
            bufos.write(len);
        }
        bufis.close();
        bufos.close();*/
    }

    //下载软件更新包
    public void downloadApk() {
        DownloadApkApi downloadApkApi = new DownloadApkApi();
        DownloadApkHttpRequest downloadApkHttpRequest = new DownloadApkHttpRequest("0.3");
        downloadApkApi.setRequest(downloadApkHttpRequest);
        downloadApkApi.downloadFilePost();
        downloadApkApi.setOnJsonHttpResponseListener(new HttpApiBase.JsonHttpResponseListener<DownloadApkHttpResponse>() {
            @Override
            public void onFailure(Exception e, String rawJsonString) {
                Toast.makeText(context,"下载更新包失败，请检查网络设置",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccessful(DownloadApkHttpResponse downloadApkHttpResponse, String rawJsonString) {
                Toast.makeText(context,"下载更新包成功",Toast.LENGTH_SHORT).show();
            }
        });
        downloadApkApi.setOnProgressUpdateListener((current, total, done) ->
                iView.onDownloadProgressUpdate(String.valueOf(current)));
    }

    @Override
    public void doBizWithPermissionRequest(int resultCode, String[] permissions) {
        super.doBizWithPermissionRequest(resultCode, permissions);
        if(PermissionUtils.isPermissionGranted(resultCode,
                PermissionUtils.READ_EXTERNAL_STORAGE,
                permissions,
                Manifest.permission.READ_EXTERNAL_STORAGE)){//该情况表示本次需要请求授权然后执行接下来的操作

            PhotoPicker.builder()
                    .setPhotoCount(9)
                    .setShowCamera(true)
                    .setShowGif(true)
                    .setPreviewEnabled(false)
                    .start((SellCarActivity)context, PhotoPicker.REQUEST_CODE);
        }else if(resultCode == PermissionUtils.PERMISSION_DENIED){

        }
    }
}
