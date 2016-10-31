package com.shixia.diudiuma.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/31.
 * Description:
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
}
