package com.shixia.diudiuma.presenter;

import android.Manifest;
import android.content.Context;

import com.shixia.diudiuma.activity.SellCarActivity;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.http.base.HttpApiBase;
import com.shixia.diudiuma.http.upload.UploadApi;
import com.shixia.diudiuma.http.upload.UploadHttpRequest;
import com.shixia.diudiuma.http.upload.UploadHttpResponse;
import com.shixia.diudiuma.iview.SellCarIView;
import com.shixia.diudiuma.iview.base.BaseIView;
import com.shixia.diudiuma.presenter.base.BasePresenter;

import java.util.List;

import me.iwf.photopicker.PhotoPicker;

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
        if(selectedPhotoList != null && selectedPhotoList.size() > 0){
            UploadApi uploadApi = new UploadApi();
            UploadHttpRequest uploadHttpRequest = new UploadHttpRequest(selectedPhotoList);
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
                }
            });
        }
    }

    @Override
    public void doBizWithPermissionRequest(int resultCode, String[] permissions) {
        super.doBizWithPermissionRequest(resultCode, permissions);
        if((resultCode == PermissionUtils.PERMISSION_ALREADY_GRANTED && permissions == null)    //该情况表示上一次已经取得了授权
                || (resultCode == PermissionUtils.READ_EXTERNAL_STORAGE
                && permissions != null
                && permissions.length > 0
                && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE))){  //该情况表示本次需要请求授权然后执行接下来的操作

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
