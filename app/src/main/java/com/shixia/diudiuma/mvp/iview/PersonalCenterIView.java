package com.shixia.diudiuma.mvp.iview;

import android.graphics.Bitmap;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public interface PersonalCenterIView extends BaseIView{

    void onShowLoginDialog();

    void onLoginSuccessful();

    void onLoginFailure();

    void onChangeToRegisterPage();

    void onChangeToLoginPage();

    void onRegisterSuccessful();

    void onRegisterFailure();

    void onShowRemind(String content);

    void onDismissDialog();

    void onChangeLoginStatus(boolean isLogin);

    /**
     * 显示用户昵称
     * @param userName  用户昵称
     */
    void onShowUserName(String userName);

    /**
     * 显示用户头像
     * @param bitmap    用户头像
     */
    void onShowUserAvatar(Bitmap bitmap);
}
