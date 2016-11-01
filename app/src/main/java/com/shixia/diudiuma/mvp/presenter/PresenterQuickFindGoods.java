package com.shixia.diudiuma.mvp.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.mvp.activity.QuickFindGoodsActivity;
import com.shixia.diudiuma.mvp.iview.QuickFindGoodsIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import me.iwf.photopicker.PhotoPicker;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:
 */

public class PresenterQuickFindGoods extends PresenterQuick {

    private QuickFindGoodsActivity activity;
    private QuickFindGoodsIView iView;

    public PresenterQuickFindGoods(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (QuickFindGoodsActivity) context;
        this.iView = (QuickFindGoodsIView) iView;
    }

    @Override
    public void toEditInfoPage(int requestCode,String defValue, String pageTitle, boolean isSureBtnVisible, String titleRemind, String contentRemind, int iconResourceId) {
        super.toEditInfoPage(requestCode,defValue, pageTitle, isSureBtnVisible, titleRemind, contentRemind, iconResourceId);
    }

    public void submitData(LoserGoodsInfo loserGoodsInfo){

        if(TextUtils.isEmpty(loserGoodsInfo.getGoodsName())||isDefValue(loserGoodsInfo.getGoodsName())){
            iView.onShowRemind("物品名不可为空");
            return;
        }

        /*if(TextUtils.isEmpty(loserGoodsInfo.getLoseAddress())||isDefValue(loserGoodsInfo.getLoseAddress())){
            iView.onShowRemind("丢失地点不可为空");
            return;
        }*/

        //以下判断表示至少有一个联系方式可用
        if(!((!isDefValue(loserGoodsInfo.getTel())||!isDefValue(loserGoodsInfo.getWechat())||!isDefValue(loserGoodsInfo.getQq()))&&
                !TextUtils.isEmpty(loserGoodsInfo.getTel())||!TextUtils.isEmpty(loserGoodsInfo.getWechat())||!TextUtils.isEmpty(loserGoodsInfo.getQq()))){
            iView.onShowRemind("请输入至少一个联系方式");
            return;
        }

        //1.压缩图片
        //2.上传图片
        //3.提交数据

        loserGoodsInfo.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    //提交成功
                    iView.onShowRemind("提交成功");
                }else {
                    //提交失败
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 跳转到选择图片页面
     */
    public void selectPic(){
        PermissionUtils.initCurrentActivity(activity,this);
        PermissionUtils.doBizAfterRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,PermissionUtils.READ_EXTERNAL_STORAGE);
        //申请权限通过则执行doBizWithPermissionRequest()中的内容
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            iView.onChangeValueAfterEdit(requestCode, data.getStringExtra("value"));
        }
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
                    .start(activity, PhotoPicker.REQUEST_CODE);
        }else if(resultCode == PermissionUtils.PERMISSION_DENIED){

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
     * @param s 传过来的数据
     * @return 是否是默认值
     */
    private boolean isDefValue(String s){
        return TextUtils.equals(s, "未知") || TextUtils.equals(s, "点击添加") || TextUtils.equals(s, "点击设置");
    }
}
