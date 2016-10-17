package com.shixia.diudiuma.common_utils;

import android.util.Log;

/**
 * Created by AmosShi on 2016/10/11.
 * Description:Log打印工具类
 */

public class L {

    private static L log;
    private static boolean isDebug = true;

    private static final int LOG_INFO = 100;
    private static final int LOG_DEBUG = 200;
    private static final int LOG_WARNING = 300;
    private static final int LOG_ERRO = 400;

    private static String LOG_TITLE = "Hello Log->";

    private L() {

    }

    public static L getInstance() {
        if (log == null) {
            log = new L();
        }
        return log;
    }

    /**
     * 发布版本时将调试模式设置为false，不打印log
     *
     * @param isDebug 是否是调试模式
     */
    public void setDebugMode(boolean isDebug) {
        L.isDebug = isDebug;
    }

    /**
     * 设置是否打印Log头信息，默认打印 HelloLog->,使容易过滤
     * @param isPrintLogTitle true/false
     */
    public void isPrintLogTitle(boolean isPrintLogTitle){
        if(!isPrintLogTitle){
            LOG_TITLE = null;
        }
    }

    public static void i(String title, String msg) {
        printLog(LOG_INFO, LOG_TITLE + title, msg);
    }

    public static void d(String title, String msg) {
        printLog(LOG_DEBUG, LOG_TITLE + title, msg);
    }

    public static void w(String title, String msg) {
        printLog(LOG_WARNING, LOG_TITLE + title, msg);
    }

    public static void e(String title, String msg) {
        printLog(LOG_ERRO, LOG_TITLE + title, msg);
    }

    private static void printLog(int logType, String title, String msg) {
        if (!isDebug) {     //如果不是调试模式，不打印log
            return;
        }
        switch (logType) {
            case LOG_INFO:
                Log.i(title, msg);
                break;
            case LOG_DEBUG:
                Log.d(title, msg);
                break;
            case LOG_WARNING:
                Log.w(title, msg);
                break;
            case LOG_ERRO:
                Log.e(title, msg);
                break;
            default:
                Log.v(title, msg);
                break;
        }
    }
}
