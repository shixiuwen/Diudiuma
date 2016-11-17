package com.shixia.diudiuma.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmosShi on 2016/11/16.
 * Description:
 */

public class FlowLayoutTag extends View {

    private Paint strokePaint;
    private TextPaint textPaint;

    private List<String> tagList = new ArrayList<String>();
    private int heightPixels;
    private int widthPixels;

    private int leftSpace = widthPixels;
    private int baseLine = 40;  //用户定义绘制的文字底部坐标，随着不断换行，该值不断变大

    public FlowLayoutTag(Context context) {
//        super(context);
        this(context, null);
    }

    public FlowLayoutTag(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();

        heightPixels = getResources().getDisplayMetrics().heightPixels;
        widthPixels = getResources().getDisplayMetrics().widthPixels;
    }

    private void initPaint() {
        strokePaint = new Paint();
        strokePaint.setColor(Color.argb(255, 0, 0, 0));
        strokePaint.setAntiAlias(true);   //抗锯齿
        strokePaint.setDither(true);      //防抖动

        textPaint = new TextPaint();
        textPaint.setColor(Color.argb(255, 255, 0, 0));
        textPaint.setTextSize(36);
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
    }

    public void setTagArray(List<String> stringList) {
        tagList = stringList;   //得到标签内容 e.g.{红色，方形，高大}

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //注意，每绘制一个标签，添加其触控区域到list,用于点击不同标签的时候判断点击的是哪一个标签
        for (int i = 0; i < tagList.size(); i++) {
            float tagWidth = textPaint.measureText(tagList.get(i)) + 36; //获得绘制该Tag所需的屏幕宽度
            if (leftSpace < tagWidth){  //绘制到下一行

            }else {

            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
