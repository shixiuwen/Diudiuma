package com.shixia.diudiuma.common_utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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

    //展开全键盘
    public void showSoftInput(EditText editText){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInputFromInputMethod(editText.getWindowToken(),0);
        }
    }

    //收起软键盘
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow((activity.getWindow().getDecorView().getWindowToken()), 0);
        }
    }

    /**
     * 展开或者隐藏软键盘
     */
    public void hideOrShowSoftInput(EditText editText){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
        imm.toggleSoftInputFromWindow(editText.getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
