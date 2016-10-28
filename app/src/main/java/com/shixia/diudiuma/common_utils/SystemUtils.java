package com.shixia.diudiuma.common_utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.shixia.diudiuma.mvp.activity.base.BaseActivity;

/**
 * Created by AmosShi on 2016/10/28.
 * Description:有关系统设置的帮助类
 */

public class SystemUtils {

    private static BaseActivity activity;
    private static SystemUtils systemUtils;

    private SystemUtils() {
    }

    public static SystemUtils getInstance(BaseActivity activity) {
        SystemUtils.activity = activity;
        if (systemUtils == null) {
            systemUtils = new SystemUtils();
        }
        return systemUtils;
    }

    //收起软键盘
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow((activity.getWindow().getDecorView().getWindowToken()), 0);
        }
    }

}
