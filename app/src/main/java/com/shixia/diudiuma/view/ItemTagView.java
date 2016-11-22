package com.shixia.diudiuma.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by AmosShi on 2016/11/22.
 * Description:倾斜45°的角标
 */

public class ItemTagView extends View {

    private Paint paint;
    private TextPaint textPaint;
    private Path strokePath;

    private int tagBgColor = -1;    //标签的背景颜色
    private int tagTextColor = -1;  //标签中文字的颜色

    private String tagContent = "这是标签";
    private int resultSpec;

    public ItemTagView(Context context) {
        this(context, null);
    }

    public ItemTagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setColor(Color.argb(255, 255, 0, 0));
        paint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        textPaint = new TextPaint();
        textPaint.setColor(Color.argb(255, 115, 115, 115));

        strokePath = new Path();
    }

    /**
     * 设置标签背景颜色和标签文字颜色
     *
     * @param tagBgColor   标签背景颜色
     * @param tagTextColor 标签文字颜色
     */
    public ItemTagView setTagColor(int tagBgColor, int tagTextColor) {
        this.tagBgColor = tagBgColor;
        this.tagTextColor = tagTextColor;
        return this;
    }

    /**
     * 设置标签内容
     * @param s 标签内容
     */
    public void setTagString(String s){
        this.tagContent = s;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        textPaint.setTextSize(resultSpec * 32 / 160F);

        //设置标签背景颜色
        if (tagBgColor != -1) {
            paint.setColor(tagBgColor);
        }

        //设置标签文字颜色
        if (tagTextColor != -1) {
            textPaint.setColor(tagTextColor);
        }

        strokePath.moveTo(0, 0);
        strokePath.lineTo(resultSpec * 0.5F, 0);
        strokePath.lineTo(resultSpec, resultSpec * 0.5F);
        strokePath.lineTo(resultSpec, resultSpec);
        strokePath.close();

        canvas.drawPath(strokePath, paint);

        canvas.save();
        canvas.translate(resultSpec * 0.5F, resultSpec * 0.5F);
        canvas.rotate(45);
        float tagWidth = textPaint.measureText(tagContent);
        canvas.drawText(tagContent, -tagWidth / 2, -(resultSpec * 0.5F / 2 - Math.abs((textPaint.ascent() - textPaint.descent()) / 2)), textPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasureBySpec(widthMeasureSpec, true);
        int height = getMeasureBySpec(heightMeasureSpec, false);
        setMeasuredDimension(width, height);
    }

    /**
     * 获取通过指定模式测量之后的宽高
     *
     * @param length  宽或者高
     * @param isWidth 是否是宽（因为为正方形，所以用不上）
     * @return 返回测量之后的宽度或者高度
     */
    private int getMeasureBySpec(int length, boolean isWidth) {
        int mode = MeasureSpec.getMode(length);
        int size = MeasureSpec.getSize(length);
        resultSpec = 0;
        if (mode == MeasureSpec.EXACTLY) {    //精确指定或者match_parent/fill_parent
            resultSpec = size;
        } else {
            if (mode == MeasureSpec.AT_MOST) {    //wrap_content
                resultSpec = 160;
            }/*else if(mode == MeasureSpec.UNSPECIFIED){  //通常用于ListView等，不用考虑
            }*/
        }
        return resultSpec;
    }
}
