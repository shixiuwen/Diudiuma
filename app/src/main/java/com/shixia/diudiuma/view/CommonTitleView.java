package com.shixia.diudiuma.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/27.
 *
 * Description:带有返回按钮和确定按钮的公用Title
 *
 * 包含以下自定义属性：
 * 1.titleName:title名
 * 2.btnSureText:确认按钮上显示的文字
 * 3.isSureBtnVisible:确认按钮是否可见
 */

public class CommonTitleView extends RelativeLayout {

    private RelativeLayout btnCommonBack;
    private Button btnCommonSure;
    private TextView tvCommonTitle;

    public CommonTitleView(Context context) {
//        super(context);
        this(context,null);
    }

    public CommonTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleView);
        String titleName = typedArray.getString(R.styleable.CommonTitleView_titleName);
        String sureText = typedArray.getString(R.styleable.CommonTitleView_btnSureText);
        boolean isSureBtnVisible = typedArray.getBoolean(R.styleable.CommonTitleView_isSureBtnVisible, true);

        tvCommonTitle.setText(titleName);
        btnCommonSure.setText(sureText);

        //部分页面不用显示该按钮
        if(!isSureBtnVisible){
            btnCommonSure.setVisibility(INVISIBLE);
        }

        typedArray.recycle();
    }

    public void setTvCommonTitle(String titleName){
        if(!TextUtils.isEmpty(titleName)){
            tvCommonTitle.setText(titleName);
        }
    }

    public void setBtnCommonSureText(String btnName){
        if(!TextUtils.isEmpty(btnName)){
            btnCommonSure.setText(btnName);
        }
    }

    public void setBtnCommonSureVisible(boolean isVisible){
        if(isVisible){
            btnCommonSure.setVisibility(VISIBLE);
        }else {
            btnCommonSure.setVisibility(INVISIBLE);
        }
    }

    private void initView(Context context){
        View view = View.inflate(context, R.layout.view_common_title_view, this);

        btnCommonBack = (RelativeLayout) view.findViewById(R.id.ll_title_common_back);
        btnCommonSure = (Button) view.findViewById(R.id.btn_common_sure);
        tvCommonTitle = (TextView) view.findViewById(R.id.tv_common_title);

        btnCommonBack.setOnClickListener(v -> {
            if(onCommonTitleClickListener!=null){
                onCommonTitleClickListener.onBackClickListener();
            }
        });

        btnCommonSure.setOnClickListener(v -> {
            if(onCommonTitleClickListener!=null){
                onCommonTitleClickListener.onSureClickListener();
            }
        });
    }

    /** ########################点击事件回调########################### */

    private OnCommonTitleClickListener onCommonTitleClickListener;

    public void setOnCommonTitleClickListener(OnCommonTitleClickListener onCommonTitleClickListener){
        this.onCommonTitleClickListener = onCommonTitleClickListener;
    }

    public interface OnCommonTitleClickListener{
        void onBackClickListener(); //返回按键点击事件
        void onSureClickListener(); //确定按键点击事件
    }
}
