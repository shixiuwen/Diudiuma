package com.shixia.diudiuma.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

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
     * 用于在Fragment中改变bar颜色，以适应不同的Fragment
     *
     * @param statusColor change color
     */
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

    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelOffset(resourceId);
        }
        return result;
    }


}
