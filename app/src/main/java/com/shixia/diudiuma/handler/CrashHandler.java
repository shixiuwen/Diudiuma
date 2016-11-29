package com.shixia.diudiuma.handler;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            File file = new File(getDiskCacheDir(mContext, "crash"), "crash.log");

            if (file.exists() && file.length() > 5 * 1024 * 1024) {     // <5M
                boolean delete = file.delete();
            } else {
                File folder = new File(getDiskCacheDir(mContext, "crash"));
                if (folder.exists() && folder.isDirectory()) {
                    if (!file.exists()) {
                        boolean newFile = file.createNewFile();
                    }
                } else {
                    boolean mkDir = folder.mkdir();
                    if (mkDir) {
                        boolean newFile = file.createNewFile();
                    }
                }
                FileOutputStream fos = new FileOutputStream(file, true);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                String format = sdf.format(new Date());
                byte[] bytes = (format +        //追加Crash时间
                        "\n" + ex.toString() +   //Crash内容
                        "\n" + "----------------------" +
                        "\n").getBytes();
                fos.write(bytes);
                fos.flush();
                fos.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //crash后重启
//        ComponentName componentName = new ComponentName("package name", "default activity");
//        Intent schemIntent = new Intent();
//        schemIntent.setComponent(componentName);
//        schemIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        mContext.startActivity(schemIntent);

        mDefaultHandler.uncaughtException(t, ex);
    }

    /**
     * 获得Crash文件路径
     * @param context context
     * @param dirName dirName
     * @return dir
     */
    private String getDiskCacheDir(Context context, String dirName) {
        String cachePath = null;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                cachePath = externalCacheDir.getPath();
            }
        }
        if (cachePath == null) {
            File cacheDir = context.getCacheDir();
            if ((cacheDir != null) && (cacheDir.exists())) {
                cachePath = cacheDir.getPath();
            }
        }
        //0/emulate/Android/data/data/com.jlcf.jiuxin/crash/crash.log
        return cachePath + File.separator + dirName;
    }
}
