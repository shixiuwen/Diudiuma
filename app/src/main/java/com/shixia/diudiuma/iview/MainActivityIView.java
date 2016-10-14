package com.shixia.diudiuma.iview;


import com.shixia.diudiuma.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public interface MainActivityIView extends BaseIView {

    /**
     * 开始定位，界面显示定位中
     */
    void onStartLocation();

    /**
     * 定位结束，显示定位信息location
     *
     * @param s 定位结果
     */
    void onEndLocation(String s);
}
