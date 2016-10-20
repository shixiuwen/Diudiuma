package com.shixia.diudiuma.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by AmosShi on 2016/10/20.
 * <p>
 * Description:设置界面顶部状态栏颜色的工具类（沉浸式状态栏）
 *
 * @link (有所修改)http://blog.csdn.net/lmj623565791/article/details/48649563/
 */

public class StatusBarCompat {

    private static final int INVALID_VAL = -1;
    private static final int COLOR_DEFAULT = Color.parseColor("#20000000");
    // TODO: 2016/10/20 此处需修改防止内存泄露
    private static View statusBarView;

    /**
     * 设置状态栏为自己想要的颜色
     *
     * @param activity    对应activity
     * @param statusColor 要设置的颜色Id
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void compat(Activity activity, int statusColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                activity.getWindow().setStatusBarColor(statusColor);
            }
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            int color = COLOR_DEFAULT;
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            statusBarView = new View(activity);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, layoutParams);
        }
    }

    /**
     * 设置状态栏为默认半透明灰黑色 "#20000000"
     *
     * @param activity 对应activity
     */
    public static void compat(Activity activity) {
        compat(activity, INVALID_VAL);
    }

    /**
     * 在需要侧滑的页面中不采用setStatusBarColor或者addView的形式改变状态栏颜色，会显得很突兀，
     * 采用以下方式直接将对应界面Activity的StatusBar设置为透明效果较好
     *
     * 主要用在第一次使用App的连续侧滑欢迎页面中
     *
     * 注意：采用该种方式，不可在对应xml文件中添加 android:fitsSystemWindows="true",否则
     * 顶部状态栏会变为白色或者黑色
     *
     * @param activity 对应 activity
     */
    public static void compatBarTraslucent(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 用于在Fragment中改变bar颜色，以适应不同的Fragment
     *
     * @param statusColor change color
     */
    @Deprecated //发现实际效果并不好,被方法compatBarTraslucent(Activity activity)替代
    public static void changeStatusBarColor(Activity activity, int statusColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(statusColor);
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP
                && statusBarView != null) {
            statusBarView.setBackgroundResource(statusColor);
        }
    }

    //获取系统状态栏高度
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelOffset(resourceId);
        }
        return result;
    }

}
