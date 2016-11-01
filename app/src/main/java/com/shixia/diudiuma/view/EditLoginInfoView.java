package com.shixia.diudiuma.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/31.
 *
 * Description:输入账户密码信息的自定义控件，登录或者注册的时候可用
 */

public class EditLoginInfoView extends RelativeLayout {

    private TextView tvEditTitle;
    private EditText etLoginInfo;
    private ImageView imgClearInput;

    public EditLoginInfoView(Context context) {
//        super(context);
        this(context,null);
    }

    public EditLoginInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditLoginInfoView);
        CharSequence text = typedArray.getText(R.styleable.EditLoginInfoView_editTitle);
        int integer = typedArray.getInteger(R.styleable.EditLoginInfoView_inputType, InputType.TYPE_CLASS_TEXT);
        etLoginInfo.setInputType(integer);
        tvEditTitle.setText(text.toString());

        typedArray.recycle();
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.view_edit_login_info_layout, this);

        tvEditTitle = (TextView) view.findViewById(R.id.tv_item_key);
        etLoginInfo = (EditText) view.findViewById(R.id.et_login_info);
        imgClearInput = (ImageView) view.findViewById(R.id.img_clear_input);

        imgClearInput.setOnClickListener(v -> etLoginInfo.setText(""));
    }

    public void setTvEditTitle(String s){
        tvEditTitle.setText(s);
    }

    public String getTvEditTitle(){
        return tvEditTitle.getText().toString();
    }

    public void setEtLoginInfo(String s){
        etLoginInfo.setText(s);
    }

    /**
     * 获得输入内容
     *
     * @return 输入内容
     */
    public String getEtLoginInfo(){
        return etLoginInfo.getText().toString();
    }
}
