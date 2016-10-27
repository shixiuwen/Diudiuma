package com.shixia.diudiuma.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/27.
 *
 * Description:信息编辑页面通用控件
 * 包含以下自定义属性：
 * 1.editTitleRemind：信息头提示
 * 2.editContentRemind：信息内容提示
 * 3.editIcon：编辑中所用到的图片
 */

public class EditPageView extends RelativeLayout {

    private TextView tvEditTitleRemind;
    private TextView tvEditContentRemind;
    private ImageView imgEditIcon;
    private Button btnEditCommit;
    private EditText etEditContent;

    public EditPageView(Context context) {
//        super(context);
        this(context,null);
    }

    public EditPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditPageView);

        String editTitleRemind = typedArray.getString(R.styleable.EditPageView_editTitleRemind);
        String editContentRemind = typedArray.getString(R.styleable.EditPageView_editContentRemind);
        int resourceId = typedArray.getResourceId(R.styleable.EditPageView_editIcon, R.drawable.ic_launcher);

        tvEditTitleRemind.setText(editTitleRemind);
        tvEditContentRemind.setText(editContentRemind);
        imgEditIcon.setImageResource(resourceId);

        typedArray.recycle();
    }

    private void initView(Context context){
        View view = View.inflate(context, R.layout.view_edit_page_view, this);

        tvEditTitleRemind = (TextView) view.findViewById(R.id.tv_edit_title_remind);
        tvEditContentRemind = (TextView) view.findViewById(R.id.tv_edit_content_remind);
        imgEditIcon = (ImageView) view.findViewById(R.id.edit_icon);
        btnEditCommit = (Button) view.findViewById(R.id.btn_edit_commit);
        etEditContent = (EditText) view.findViewById(R.id.et_edit_content);

        btnEditCommit.setOnClickListener(v -> {
            if(onCommitClickListener!=null){
                onCommitClickListener.onCommitListener();
            }
        });
    }

    public void setTvEditTitleRemind(String titleRemind){
        tvEditTitleRemind.setText(titleRemind);
    }

    public void setTvEditContentRemind(String contentRemind){
        tvEditContentRemind.setText(contentRemind);
    }

    public void setImgEditIcon(int resourceId){
        imgEditIcon.setImageResource(resourceId);
    }

    public String getEditContent(){
        return etEditContent.getText().toString();
    }

    /** ######################### 回调接口 ########################*/
    private OnCommitClickListener onCommitClickListener;

    public void setOnCommitClickListener(OnCommitClickListener onCommitClickListener){
        this.onCommitClickListener = onCommitClickListener;
    }

    public interface OnCommitClickListener{
        void onCommitListener();
    }
}
