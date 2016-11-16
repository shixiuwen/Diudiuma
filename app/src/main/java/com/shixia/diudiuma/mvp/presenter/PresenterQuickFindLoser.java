package com.shixia.diudiuma.mvp.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.shixia.diudiuma.MyApplication;
import com.shixia.diudiuma.bmob.bean.GoodsIcon;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.common_utils.Luban;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.mvp.activity.QuickFindLoserActivity;
import com.shixia.diudiuma.mvp.iview.QuickFindLoserIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.LoadingDialog;

import java.io.File;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import me.iwf.photopicker.PhotoPicker;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:失物招领
 */

public class PresenterQuickFindLoser extends PresenterQuick {

    private QuickFindLoserActivity activity;
    private QuickFindLoserIView iView;

    private LoserGoodsInfo loserGoodsInfo;

    public PresenterQuickFindLoser(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (QuickFindLoserActivity) context;
        this.iView = (QuickFindLoserIView) iView;
    }

    @Override
    public void toEditInfoPage(int requestCode, String defValue, String pageTitle, boolean isSureBtnVisible, String titleRemind, String contentRemind, int iconResourceId) {
        super.toEditInfoPage(requestCode, defValue, pageTitle, isSureBtnVisible, titleRemind, contentRemind, iconResourceId);
    }

    public void submitData() {

        loserGoodsInfo = getLoserGoodsInfo();

        if (TextUtils.isEmpty(loserGoodsInfo.getGoodsName()) || isDefValue(loserGoodsInfo.getGoodsName())) {
            iView.onShowRemind("物品名不可为空");
            return;
        }

        /*if(TextUtils.isEmpty(loserGoodsInfo.getLoseAddress())||isDefValue(loserGoodsInfo.getLoseAddress())){
            iView.onShowRemind("拾到地点不可为空");
            return;
        }*/

        //以下判断表示至少有一个联系方式可用
        if (!((!isDefValue(loserGoodsInfo.getTel()) || !isDefValue(loserGoodsInfo.getWechat()) || !isDefValue(loserGoodsInfo.getQq())) &&
                !TextUtils.isEmpty(loserGoodsInfo.getTel()) || !TextUtils.isEmpty(loserGoodsInfo.getWechat()) || !TextUtils.isEmpty(loserGoodsInfo.getQq()))) {
            iView.onShowRemind("请输入至少一个联系方式");
            return;
        }

        //1.压缩图片
        //2.上传图片
        //3.提交数据

        //判断是否有选择了图片，选择了的话先压缩图片再上传数据，未选择的话直接上传数据
        String goodsIcon = loserGoodsInfo.getGoodsIcon();
        if (TextUtils.isEmpty(goodsIcon)) {
            submitData();
        } else {
            Luban.get(activity)
                    .load(new File(goodsIcon))
                    .putGear(Luban.THIRD_GEAR)
                    .launch(new Luban.OnCompressListener() {
                        @Override
                        public void onStart() {
                            MyApplication.UIHandler.post(() -> LoadingDialog.getInstance(activity, "文件压缩中").show());
                        }

                        @Override
                        public void onSuccess(File file) {
                            MyApplication.UIHandler.post(() -> {
                                LoadingDialog.getInstance(activity, "文件压缩结束").dismiss();
                                //2.上传图片,上传成功之后
                                submitPic(file.getAbsolutePath(),loserGoodsInfo);
                            });

                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
    }

    private void submitDataWithNoPic(LoserGoodsInfo goodsInfo) {
        goodsInfo.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    //提交成功
                    iView.onShowRemind("提交成功");
                } else {
                    //提交失败
                    e.printStackTrace();
                }
            }
        });
    }

    private void submitPic(String filePath, LoserGoodsInfo loserGoodsInfo) {
        GoodsIcon bmobFile = new GoodsIcon(new File(filePath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    CToast.makeCText(activity, "上传文件成功:" + bmobFile.getFileUrl(), Toast.LENGTH_SHORT).show();
                    L.i("successful", "上传文件成功:" + bmobFile.getFileUrl());
                    loserGoodsInfo.setGoodsIcon(bmobFile.getFileUrl());
                    submitDataWithNoPic(loserGoodsInfo);
                } else {
                    L.i("error permission", e.getMessage());
                    CToast.makeCText(activity, "上传文件失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
                L.i("progress", value + " ");
            }
        });
    }

    /**
     * 跳转到选择图片页面
     */
    public void selectPic() {
        PermissionUtils.initCurrentActivity(activity, this);
        PermissionUtils.doBizAfterRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, PermissionUtils.READ_EXTERNAL_STORAGE);
        //申请权限通过则执行doBizWithPermissionRequest()中的内容
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            setLoserGoodsInfo(requestCode,data.getStringExtra("value"));
            iView.onChangeValueAfterEdit(requestCode, data.getStringExtra("value"));
        }
    }

    @Override
    public void doBizWithPermissionRequest(int resultCode, String[] permissions) {
        super.doBizWithPermissionRequest(resultCode, permissions);
        if (PermissionUtils.isPermissionGranted(resultCode,
                PermissionUtils.READ_EXTERNAL_STORAGE,
                permissions,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {//该情况表示本次需要请求授权然后执行接下来的操作

            PhotoPicker.builder()
                    .setPhotoCount(9)
                    .setShowCamera(true)
                    .setShowGif(true)
                    .setPreviewEnabled(false)
                    .start(activity, PhotoPicker.REQUEST_CODE);
        } else if (resultCode == PermissionUtils.PERMISSION_DENIED) {

        }
    }

    /**
     * 显示图片预览界面
     */
    public void toPreviewPic() {
        iView.onShowPreviewPop();
    }

    /**
     * 数据是否为默认值
     *
     * @param s 传过来的数据
     * @return 是否是默认值
     */
    private boolean isDefValue(String s) {
        return TextUtils.equals(s, "未知") || TextUtils.equals(s, "点击添加") || TextUtils.equals(s, "点击设置");
    }
}
