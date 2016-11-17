package com.shixia.diudiuma.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.shixia.diudiuma.R;

/**
 * Created by Administrator on 2016/11/17.
 */

public class TagTextView extends TextView {

    private int tagType;    //用于设置标签类型，是否可点击以及如何处理点击事件

    public TagTextView(Context context) {
//        super(context);
        this(context,null);
    }

    public TagTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setTextColor(Color.argb(255,17,34,51));

        setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.y36));

        setBackgroundResource(R.drawable.half_circle_radio_bg);

        setPadding((int) getResources().getDimension(R.dimen.x30), (int) getResources().getDimension(R.dimen.y8)
                , (int) getResources().getDimension(R.dimen.x30), (int) getResources().getDimension(R.dimen.y8));
    }

    public void setType(int type){  //标签类型 1.不可点击   2.可点击，点击添加  3.可点击，点击移除
        this.tagType = type;
    }
}
