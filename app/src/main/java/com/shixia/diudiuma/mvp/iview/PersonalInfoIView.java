package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public interface PersonalInfoIView extends BaseIView {

    /**
     * 开始提交编辑信息
     */
    void onEditCommitStart();

    /**
     * 提交编辑信息结束
     */
    void onEditCommitEnd();

    /**
     * 结束当前Activity
     */
    void onFinishCurrentActivity();

    /**
     * 显示提示信息
     * @param s 提示内容
     */
    void onShowRemind(String s);

    /**
     * 个人信息修改
     * @param name *
     * @param englishName *
     * @param tele *
     * @param wechat *
     * @param qq *
     * @param email *
     */
    void onPersonalInfoChanged(String name, String englishName, String tele, String wechat, String qq, String email);

}
