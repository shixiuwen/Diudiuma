package com.shixia.diudiuma.mvp.activity;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.SplashActivityIView;
import com.shixia.diudiuma.mvp.presenter.PresenterSplash;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

public class SplashActivity extends BaseActivity implements SplashActivityIView{

    private PresenterSplash presenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterSplash(this,this);
        return presenter;
    }

    @Override
    protected void afterActivityOnCreate() {
        presenter.openNextPage();
    }
}
