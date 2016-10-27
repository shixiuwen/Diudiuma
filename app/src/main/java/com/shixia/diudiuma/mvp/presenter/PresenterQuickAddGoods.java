package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.os.Bundle;

import com.shixia.diudiuma.mvp.activity.EditInfoPageActivity;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:
 */

public class PresenterQuickAddGoods extends BasePresenter {

    private BaseActivity activity;
    private QuickIView iView;

    public PresenterQuickAddGoods(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (BaseActivity) context;
        this.iView = (QuickIView) iView;
    }

    /**
     * 跳转到编辑条目信息页面
     *
     * @param pageTitle 编辑页面Title
     * @param isSureBtnVisible 编辑页面Title的按钮是否可见
     * @param titleRemind   编辑页面的输入框Title提示信息
     * @param contentRemind 编辑页面的输入框内容提示信息
     * @param iconResourceId    编辑页面的输入框icon
     *
     */
    public void toEditInfoPage(String pageTitle,boolean isSureBtnVisible,String titleRemind,String contentRemind,int iconResourceId){
        Bundle bundle = new Bundle();
        bundle.putString("pageTitle",pageTitle);
        bundle.putBoolean("isSureBtnVisible",isSureBtnVisible);
        bundle.putString("titleRemind",titleRemind);
        bundle.putString("contentRemind",contentRemind);
        bundle.putInt("iconResourceId",iconResourceId);

        activity.startActivity(activity, EditInfoPageActivity.class,bundle,false);
    }
}
