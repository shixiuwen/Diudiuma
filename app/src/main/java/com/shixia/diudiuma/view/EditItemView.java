package com.shixia.diudiuma.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/26.
 *
 * Description:自定义组合控件，用于一些界面（如设置）的 Item 条目
 */

public class EditItemView extends LinearLayout {

    private ImageView imgIcon;
    private TextView tvItemKey;
    private TextView tvItemValue;
    private LinearLayout llEditItem;

    private String tvItemKeyText;
    private String tvItemValueText;


    public EditItemView(Context context) {
//        super(context);
        this(context, null);
    }

    public EditItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditItemView);
        CharSequence keyText = typedArray.getText(R.styleable.EditItemView_keyText);
        CharSequence valueText = typedArray.getText(R.styleable.EditItemView_valueText);
        int resourceId = typedArray.getResourceId(R.styleable.EditItemView_editItemIcon, R.drawable.ic_launcher);

        imgIcon.setBackgroundResource(resourceId);
        tvItemKey.setText(keyText);
        tvItemValue.setText(valueText);

        tvItemKeyText = tvItemKey.getText().toString();
        tvItemValueText = tvItemValue.getText().toString();

        typedArray.recycle();

        llEditItem.setOnClickListener(v -> onEditItemClickListener.onEditClickListener());

    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.view_edit_item_layout, this);

        imgIcon = (ImageView) view.findViewById(R.id.img_icon);
        tvItemKey = (TextView) view.findViewById(R.id.tv_item_key);
        tvItemValue = (TextView) view.findViewById(R.id.tv_item_value);
        llEditItem = (LinearLayout) view.findViewById(R.id.ll_edit_item);

    }

    /**
     * 设置
     * @param keyText item名称
     */
    public void setTvItemKey(String keyText){
        tvItemKey.setText(keyText);
        tvItemKeyText = tvItemKey.getText().toString();
    }

    /**
     * 获得Key的值
     * @return key的值
     */
    public String getTvItemKey(){
        return tvItemKeyText;
    }

    /**
     * 设置Item值
     * @param valueText Item值
     */
    public void setTvItemValue(String valueText){
        tvItemValue.setText(valueText);
        tvItemValueText = tvItemValue.getText().toString();
    }

    /**
     * 获得value的值
     * @return value的值
     */
    public String getTvItemValue(){
        return tvItemValueText;
    }

    private OnEditItemClickListener onEditItemClickListener;

    public interface OnEditItemClickListener {
        void onEditClickListener();
    }

    public void setOnEditItemClickListener(OnEditItemClickListener onEditItemClickListener){
        this.onEditItemClickListener = onEditItemClickListener;
    }

}
