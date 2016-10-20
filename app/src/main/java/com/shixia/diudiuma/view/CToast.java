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
 * Description:自定义弹出Toast样式，C表示Custom(自定义)
 */

public class CToast extends Toast {

    /**
     * 如果需要使用自定义的Toast，设置该值为true,在makeCText中自定义Toast外观属性
     */
    private static boolean isToastCustom = true;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    private CToast(Context context) {
        super(context);
    }

    public static Toast makeCText(Context context, String msg, int duration){
        if(isToastCustom){
            CToast toast = new CToast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.view_custom_toast_layout, null);
            TextView tvToastContent = (TextView) view.findViewById(R.id.tv_toast_content);
            tvToastContent.setText(msg);
            toast.setDuration(duration);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER,0,0);   //设置Toast弹出的位置
            return toast;
        }else {
            return makeText(context,msg,duration);
        }
    }
}
