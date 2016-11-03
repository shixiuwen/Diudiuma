package com.shixia.diudiuma.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/24.
 *
 * Description:得到圆形图片设置头像
 */

public class CircleImageView extends ImageView {

    private int bitmapWidth;
    private int bitmapHeight;

    private int resultRadio;
    private Bitmap bitmap;

    private Paint paint;

    public CircleImageView(Context context) {
//        super(context);
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
        paint.setAntiAlias(true);   //抗锯齿
        paint.setDither(true);  //防抖动

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    /**
     * 将头像设置为指定图片
     *
     * @param bitmap 要设置为头像的图片
     */
    public void setAvatar(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getFinalCircleBitmap(bitmap);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureSizeWidth = getMeasureSize(widthMeasureSpec, true);
        int measureSizeHeight = getMeasureSize(heightMeasureSpec, false);
        setMeasuredDimension(measureSizeWidth, measureSizeHeight);
        resultRadio = Math.min(measureSizeHeight, measureSizeWidth); //得到画布比较小的一边
    }

    private int getMeasureSize(int measureSpec, boolean isWidth) {
        //模式
        int mode = MeasureSpec.getMode(measureSpec);
        //尺寸
        int size = MeasureSpec.getSize(measureSpec);
        //计算所得实际尺寸，要被返回。
        int resultSize;
        if (mode == MeasureSpec.EXACTLY) {    //表示精准指定
            resultSize = size;
        } else {
            resultSize = isWidth ? bitmapWidth : bitmapHeight;
            if (mode == MeasureSpec.AT_MOST) {
                resultSize = Math.min(bitmapWidth, resultSize);
            }
        }
        return resultSize;
    }

    /**
     * 该方法最终得到的是一个圆形的图片，方法中经历了三次变换，切正方形，缩放，切圆
     *
     * @param bitmap
     */
    private void getFinalCircleBitmap(Bitmap bitmap) {
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        //得到正方形图片
        Bitmap recBitmap = getRecBitmap(bitmap);
        if (resultRadio != 0) { //将图片进行缩放
            bitmap = getBitmapByPix(recBitmap, getContext(), resultRadio);
        }
        //得到圆形图片
        this.bitmap = getRoundedCornerBitmap(bitmap, resultRadio / 2);
    }

    /**
     * 图片大小缩放
     *
     * @param bitmap  要缩放的Bitmap
     * @param context 上下文
     * @param scale   缩放后的大小（dp）
     * @return 缩放后的Bitmap
     */
    private Bitmap getBitmapByPix(Bitmap bitmap, Context context, float scale) {
        // 获得图片的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 设置想要的大小
        int newWidth = (int) scale;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;

        System.gc();
        // 得到新的图片
        return Bitmap.createScaledBitmap(bitmap, (int) (width * scaleWidth), (int) (height * scaleWidth), true);
    }

    /**
     * 得到正方形图片
     *
     * @param bitmap 要转换的图片
     * @return 返回正方形图片
     */
    private Bitmap getRecBitmap(Bitmap bitmap) {
        //获得原图的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //设置挖取得区域
        Bitmap outputBitmap;
        if (width >= height) {
            outputBitmap = Bitmap.createBitmap(bitmap, (width - height) / 2, 0, height, height);
        } else {
            outputBitmap = Bitmap.createBitmap(bitmap, 0, (height - width) / 2, width, width);
        }
        System.gc();
        return outputBitmap;
    }

    /**
     * 由正方形Bitmap图片切得圆形Bitmap图片
     *
     * @param bitmap 要切割的Bitmap
     * @param pixels 切割的圆半径
     * @return output 切割后的Bitmap
     */

    private Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, (float) pixels, (float) pixels, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        System.gc();
        return output;
    }
}
