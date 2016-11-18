package com.shixia.diudiuma.mvp.iview;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:
 */

public interface QuickFindLoserIView extends QuickIView {

    /**
     * 显示图片预览界面
     */
    void onShowPreviewPop();

    /**
     * 提交数据成功之后关闭当前页面，防止重复提交
     */
    void onFinish();
}
