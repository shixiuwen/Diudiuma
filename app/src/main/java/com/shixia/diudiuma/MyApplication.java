package com.shixia.diudiuma;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.shixia.diudiuma.common_utils.L;

/**
 * Created by AmosShi on 2016/10/17.
 * Description:
 */

public class MyApplication extends Application {

    //初始化运行在UI线程中的handler
    public static Handler UIHandler = new Handler(Looper.getMainLooper());
    //创建全局的Context
    public static Context ctx ;

    @Override
    public void onCreate() {
        super.onCreate();
        //Crash信息收集初始化
//        CrashHandler instance = CrashHandler.getInstance();
//        instance.init(this);

        //初始化Log信息
        L.getInstance().setDebugMode(true);
        //初始化context
        ctx = MyApplication.this;
    }
}
