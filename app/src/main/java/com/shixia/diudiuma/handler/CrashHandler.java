package com.shixia.diudiuma.handler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.shixia.diudiuma.common_utils.FileUtils;

import java.io.File;

/**
 * Created by AmosShi on 2016/10/17.
 * Description:
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = CrashHandler.class.getSimpleName();

    private static CrashHandler INSTANCE = new CrashHandler();

    private Context mContext;

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private final static int ERROR_LEVEL = 0;


    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context ctx) {
        mContext = ctx.getApplicationContext();
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        try {
            File file = new File(FileUtils.getDiskCacheDir(mContext, "crash"), "crash.log");

            if (file.exists() && file.length() > 10 * 1024 * 1024) {
                file.delete();
            } else {
                FileUtils.writeLog(new File(FileUtils.getDiskCacheDir(mContext, "crash"), "crash.log"), ex);
            }
        } catch (Exception e) {
//            StatsUtils.reportError(mContext, new Exception(("save error log to sdcard faild")), 1);
        }

        //crash后重启
        ComponentName componentName = new ComponentName("package name", "default activity");
        Intent schemIntent = new Intent();
        schemIntent.setComponent(componentName);
        schemIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(schemIntent);
    }
}
