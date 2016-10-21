package com.shixia.diudiuma.mvp.activity;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.common_utils.SpUtil;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Single;
import rx.functions.Action1;

public class SplashActivity extends BaseActivity {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void afterActivityOnCreate() {
        //如果是第一次使用，3秒之后打开欢迎页面，否则3秒之后进入主页
        Single
          .just(SpUtil.getBoolean(this,"isFirstOpenApp",true))
          .delay(3, TimeUnit.SECONDS)
          .subscribe(openWelcomePage());
    }

    /**
     * 打开欢迎页面
     * @return action1
     */
    private Action1<Boolean> openWelcomePage(){
        return isFirstOpen -> {
            if(!isFirstOpen){
                startActivity(SplashActivity.this,MainActivity.class,null,true);
            }else {
                startActivity(SplashActivity.this,WelcomeActivity.class,null,true);
            }
            finish();
        };
    }
}
