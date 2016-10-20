package com.shixia.diudiuma.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/20.
 *
 * Description:自定义弹出Toast样式
 */

public class CustomToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CustomToast(Context context) {
        super(context);
    }

    public static CustomToast showToast(Context context,String msg,int duration){
        CustomToast toast = new CustomToast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_custom_toast_layout, null);
        TextView tvToastContent = (TextView) view.findViewById(R.id.tv_toast_content);
        tvToastContent.setText(msg);
        toast.setDuration(duration);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);   //设置Toast弹出的位置
        return toast;
    }
}
