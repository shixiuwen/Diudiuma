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
 * <p>
 * Description:自定义组合控件，用于一些界面（如设置）的 Item 条目
 * 有如下自定义属性：
 * 1.keyText
 * 2.valueText
 * 3.editItemIcon
 * 4.topSplitVisible
 * 5.bottomSplitVisible
 */

public class EditItemView extends LinearLayout {

    private ImageView imgIcon;
    private TextView tvItemKey;
    private TextView tvItemValue;
    private LinearLayout llEditItem;

    private String tvItemKeyText;
    private String tvItemValueText;
    private View viewSplitTop;
    private View viewSplitBottom;


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
        boolean topSplitVisible = typedArray.getBoolean(R.styleable.EditItemView_topSplitVisible, false);
        boolean bottomSplitVisible = typedArray.getBoolean(R.styleable.EditItemView_bottomSplitVisible, true);

        imgIcon.setBackgroundResource(resourceId);
        tvItemKey.setText(keyText);
        tvItemValue.setText(valueText);

        //分割线是否可见
        if (topSplitVisible) {
            viewSplitTop.setVisibility(View.VISIBLE);
        } else {
            viewSplitTop.setVisibility(View.INVISIBLE);
        }
        if (bottomSplitVisible) {
            viewSplitBottom.setVisibility(View.VISIBLE);
        } else {
            viewSplitBottom.setVisibility(View.INVISIBLE);
        }

        tvItemKeyText = tvItemKey.getText().toString();
        tvItemValueText = tvItemValue.getText().toString();

        typedArray.recycle();

    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.view_edit_item_layout, this);

        imgIcon = (ImageView) view.findViewById(R.id.img_icon);
        tvItemKey = (TextView) view.findViewById(R.id.tv_item_key);
        tvItemValue = (TextView) view.findViewById(R.id.tv_item_value);
        llEditItem = (LinearLayout) view.findViewById(R.id.ll_edit_item);
        viewSplitTop = view.findViewById(R.id.view_split_top);
        viewSplitBottom = view.findViewById(R.id.view_split_bottom);

        llEditItem.setOnClickListener(v -> {
            if(onEditItemClickListener!=null){
                onEditItemClickListener.onEditClickListener();
            }
        });

    }

    /**
     * 设置
     *
     * @param keyText item名称
     */
    public void setTvItemKey(String keyText) {
        tvItemKey.setText(keyText);
        tvItemKeyText = tvItemKey.getText().toString();
    }

    /**
     * 获得Key的值
     *
     * @return key的值
     */
    public String getTvItemKey() {
        return tvItemKeyText;
    }

    /**
     * 设置Item值
     *
     * @param valueText Item值
     */
    public void setTvItemValue(String valueText) {
        tvItemValue.setText(valueText);
        tvItemValueText = tvItemValue.getText().toString();
    }

    /**
     * 获得value的值
     *
     * @return value的值
     */
    public String getTvItemValue() {
        return tvItemValueText;
    }

    private OnEditItemClickListener onEditItemClickListener;

    public interface OnEditItemClickListener {
        void onEditClickListener();
    }

    public void setOnEditItemClickListener(OnEditItemClickListener onEditItemClickListener) {
        this.onEditItemClickListener = onEditItemClickListener;
    }

}
