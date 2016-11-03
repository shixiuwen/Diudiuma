package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public interface EditPersonalInfoIView extends BaseIView {

    /**
     * 显示提示信息
     * @param s 提示内容
     */
    void onShowRemind(String s);

    /**
     * 结束当前Activity
     */
    void onCurrentActivityFinished(String name,String englishName,String tele,String wechat,String qq,String email);

    /**
     * 开始传输数据
     */
    void onSubmitStart();

    /**
     * 传输数据结束
     */
    void onSubmitEnd();
}
