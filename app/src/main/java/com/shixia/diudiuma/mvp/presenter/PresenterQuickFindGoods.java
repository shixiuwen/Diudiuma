package com.shixia.diudiuma.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.mvp.activity.QuickFindGoodsActivity;
import com.shixia.diudiuma.mvp.iview.QuickFindGoodsIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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
    public void toEditInfoPage(int requestCode, String pageTitle, boolean isSureBtnVisible, String titleRemind, String contentRemind, int iconResourceId) {
        super.toEditInfoPage(requestCode, pageTitle, isSureBtnVisible, titleRemind, contentRemind, iconResourceId);
    }

    public void submitData(LoserGoodsInfo loserGoodsInfo){
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            iView.onChangeValueAfterEdit(requestCode, data.getStringExtra("value"));
        }
    }
}
