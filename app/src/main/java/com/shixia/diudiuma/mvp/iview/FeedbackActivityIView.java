package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public interface FeedbackActivityIView extends BaseIView {

    /**
     * 开始提交数据
     */
    void onStartSubmit();

    /**
     * 提交数据结束
     */
    void onEndSubmit();

    /**
     * 显示提示信息
     *
     * @param s 提示内容
     */
    void onShowRemind(String s);

    /**
     * 提交数据成功之后结束当前Activity
     */
    void onFinishActivity();
}
