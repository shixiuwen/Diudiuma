package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.common_utils.SpUtil;
import com.shixia.diudiuma.mvp.activity.MainActivity_2;
import com.shixia.diudiuma.mvp.iview.PersonalCenterIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class PresenterPersonalCenter extends BasePresenter{

    private MainActivity_2 activity;
    private PersonalCenterIView iView;

    public PresenterPersonalCenter(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (MainActivity_2) context;
        this.iView = (PersonalCenterIView) iView;
    }

    /**
     * 登录或者退出操作
     */
    public void loginOrExitBtnClick(){
        //1.从本地读取数据判断是否登录，如果登录，点击退出登录，否则显示登录的Dialog
        if(SpUtil.getBoolean(activity,"isLogin",false)){
            // TODO: 2016/10/24 退出登录

        }else {
            // TODO: 2016/10/24 显示登录的dialog
            iView.onShowLoginDialog();
        }
    }

    public void login(){

    }

    /**
     * 点击将界面转转换为注册模式
     */
    public void changeToRegisterDialog(){
        iView.onChangeToRegisterPage();
    }

    public void changeToLoginDialog(){
        iView.onChangeToLoginPage();
    }

    public void dismissDialog(){
        iView.onDismissDialog();
    }
}
