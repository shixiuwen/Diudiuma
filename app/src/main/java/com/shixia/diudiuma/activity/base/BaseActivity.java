package com.shixia.diudiuma.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by AmosShi on 2016/10/11.
 * Description:activity基类
 */

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContentView();
        initListener();
        initPresenter();
        afterActivityOnCreate();

    }

    /**
     * 做类似初始化界面的操作，包括：
     * 1.setContentView(R.layout.activity_splash);
     * 2.findViewById
     */
    protected abstract void initContentView();

    /**
     * 初始化控件的监听事件
     */
    protected abstract void initListener();

    /**
     * 初始化Presenter
     */
    protected abstract void initPresenter();

    /**
     * 可选操作，数据初始化之后的操作在该方法中执行
     */
    protected void afterActivityOnCreate(){}

    public void startActivity(Context context,Class c,Bundle bundle,boolean isFinishCurrentActivity){
        Intent intent = new Intent(context,c);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if(isFinishCurrentActivity){
            finish();
        }
    }

}
