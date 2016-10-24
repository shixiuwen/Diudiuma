package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.MyApplication;
import com.shixia.diudiuma.common_utils.SpUtil;
import com.shixia.diudiuma.mvp.activity.MainActivity_2;
import com.shixia.diudiuma.mvp.activity.SplashActivity;
import com.shixia.diudiuma.mvp.activity.WelcomeActivity;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Single;
import rx.functions.Action1;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class PresenterSplash extends BasePresenter {

    private SplashActivity activity;
    private BaseIView iView;

    public PresenterSplash(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (SplashActivity) context;
        iView = iView;
    }

    /**
     * 打开欢迎页面
     */
    public void openNextPage(){
        //如果是第一次使用，3秒之后打开欢迎页面，否则3秒之后进入主页
        Single
                .just(SpUtil.getBoolean(activity,"isFirstOpenApp",true))
                .delay(3, TimeUnit.SECONDS)
                .subscribe(openNextPageWithJudge());
    }

    /**
     * 打开欢迎页面或者主界面
     * @return action1
     */
    private Action1<Boolean> openNextPageWithJudge(){
        return isFirstOpen -> {
            if(!isFirstOpen){
                activity.startActivity(activity,MainActivity_2.class,null,true);
            }else {
                activity.startActivity(activity,WelcomeActivity.class,null,true);
                SpUtil.put(MyApplication.ctx,"isFirstOpenApp",false);    //设置标记，表示已不是第一次打开应用，下次启动应用不再显示欢迎页
            }
            activity.finish();
        };
    }

}
