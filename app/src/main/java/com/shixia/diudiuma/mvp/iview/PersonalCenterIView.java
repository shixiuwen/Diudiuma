package com.shixia.diudiuma.mvp.iview;

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
}
