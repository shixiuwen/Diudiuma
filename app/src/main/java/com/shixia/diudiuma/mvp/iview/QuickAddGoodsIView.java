package com.shixia.diudiuma.mvp.iview;

import android.graphics.Bitmap;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:
 */

public interface QuickAddGoodsIView extends QuickIView {
    /**
     * 显示图片预览界面
     */
    void onShowPreviewPop();

    /**
     * 通过丢丢码生成二维码
     * @param image
     */
    void onShowDecodeResult(Bitmap image);
}
