package com.shixia.diudiuma.mvp.iview;

import android.graphics.Bitmap;

import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/11/2.
 * Description:
 */

public interface QuickScanDDMIView extends BaseIView {

    /**
     * 显示提示信息
     * @param s 提示内容
     */
    void onShowRemind(String s);

    /**
     * 处理扫描结果
     * @param s 扫描结果
     */
    void onShowScanResult(String s);

    /**
     * 查找成功之后显示详细界面信息爱
     * @param loserGoodsInfo 详细物品信息
     */
    void onShowDetailLoserPage(LoserGoodsInfo loserGoodsInfo);

    /**
     * 显示二维码图片
     * @param qrCode pic
     */
    void onShowDDMImg(Bitmap qrCode);

    /**
     * 显示物品图片的url
     *
     * @param url url
     */
    void onShowGoodsPic(String url);
}
