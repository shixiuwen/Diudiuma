package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.mvp.activity.QuickFindGoodsActivity;
import com.shixia.diudiuma.mvp.iview.QuickFindGoodsIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:
 */

public class PresenterQuickFindGoods extends PresenterQuick {

    private QuickFindGoodsActivity activity;
    private QuickFindGoodsIView iView;

    public PresenterQuickFindGoods(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (QuickFindGoodsActivity)context;
        this.iView = (QuickFindGoodsIView) iView;
    }

    @Override
    public void toEditInfoPage(String pageTitle, boolean isSureBtnVisible, String titleRemind, String contentRemind, int iconResourceId) {
        super.toEditInfoPage(pageTitle, isSureBtnVisible, titleRemind, contentRemind, iconResourceId);
    }
}
