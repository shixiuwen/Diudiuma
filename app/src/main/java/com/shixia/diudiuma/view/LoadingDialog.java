package com.shixia.diudiuma.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/17.
 * Description:
 */

public class LoadingDialog extends Dialog {

    private static LoadingDialog loadingDialog;
    private static Context context;

    private LoadingDialog(Context context) {
        super(context);
    }

    private LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static LoadingDialog getInstance(Context context,String message){
        LoadingDialog.context = context;
        if(loadingDialog == null){
            loadingDialog = new LoadingDialog(context,R.style.loading_dialog);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setCancelable(true);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialog_view, null);
            ImageView imgProgress = (ImageView) view.findViewById(R.id.img_progress_image);
            ObjectAnimator animator = ObjectAnimator.ofFloat(imgProgress, "rotation", 0f, 360f);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setDuration(1000);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
            loadingDialog.setContentView(view);
        }
        return loadingDialog;
    }
}
