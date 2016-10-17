package com.shixia.diudiuma;

import android.app.Application;

import com.shixia.diudiuma.handler.CrashHandler;

/**
 * Created by AmosShi on 2016/10/17.
 * Description:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Crash信息收集初始化
        CrashHandler instance = CrashHandler.getInstance();
        instance.init(this);
    }
}
