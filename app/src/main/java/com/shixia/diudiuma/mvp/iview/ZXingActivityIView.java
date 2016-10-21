package com.shixia.diudiuma.mvp.iview;

import android.graphics.Bitmap;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/21.
 * Description:
 */

public interface ZXingActivityIView extends BaseIView {

    /**
     * 显示二维码图片解析结果
     *
     * @param result 解析结果字符串
     */
    void onShowScanResult(String result);

    /**
     * 显示生成二维码图片结果
     *
     * @param img
     */
    void onShowDecodeResult(Bitmap img);

    /**
     * 显示包括Toast等的提示信息
     * @param s 提示内容
     */
    void showRemind(String s);

}
