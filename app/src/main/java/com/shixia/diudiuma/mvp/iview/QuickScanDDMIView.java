package com.shixia.diudiuma.mvp.iview;

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

}
