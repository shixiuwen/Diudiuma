package com.shixia.diudiuma.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shixia.diudiuma.R;

/**
 * Created by ShiXiuwen on 2017/2/9.
 * <p>
 * Description:退出登录等的通用弹窗
 */

public class BaseDialog extends Dialog {

    private Context context;
    private String title;           //dialog标题
    private String content;         //dialog提示内容
    private String positiveBtnText; //dialog确认按钮文字

    private Button btnSure;
    private Button btnCancel;
    private TextView tvTitle;
    private TextView tvContent;

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(@NonNull Context context, String title, String content, String positiveBtnText) {
        super(context, 0);
        this.context = context;
        this.title = title;
        this.content = content;
        this.positiveBtnText = positiveBtnText;
        initView();
        initSetting();
        initListener();
    }

    private void initSetting() {
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_base,null);
        setContentView(view);
        btnSure = (Button) view.findViewById(R.id.btn_sure);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvContent = (TextView) view.findViewById(R.id.tv_content);

        tvTitle.setText(title);
        tvContent.setText(content);
        btnSure.setText(positiveBtnText);
    }

    private void initListener() {
        btnSure.setOnClickListener(v -> {
            if (onDialogClickListener != null) {
                onDialogClickListener.onSureClickListener();
            }
        });
        btnCancel.setOnClickListener(v -> {
            if (onDialogClickListener != null) {
                onDialogClickListener.onCancelClickListener();
            }
        });
    }

    /********************** 回调 ************************/

    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener){
        this.onDialogClickListener = onDialogClickListener;
    }

    public interface OnDialogClickListener{
        void onSureClickListener();
        void onCancelClickListener();
    }
}
