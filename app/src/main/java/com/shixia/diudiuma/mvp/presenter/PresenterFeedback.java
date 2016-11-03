package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.text.Editable;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bmob.bean.FeedbackContent;
import com.shixia.diudiuma.mvp.activity.FeedbackActivity;
import com.shixia.diudiuma.mvp.iview.FeedbackActivityIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by AmosShi on 2016/11/3.
 * <p>
 * Description:意见反馈
 */

public class PresenterFeedback extends BasePresenter {

    private FeedbackActivity activity;
    private FeedbackActivityIView iView;

    public PresenterFeedback(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (FeedbackActivity) context;
        this.iView = (FeedbackActivityIView) iView;
    }

    /**
     * 提交意见反馈数据
     *
     * @param content 意见反馈内容
     * @param contact 反馈者联系方式
     */
    public void submitFeedback(Editable content, Editable contact) {
        if (content == null) {
            iView.onShowRemind(activity.getString(R.string.content_empty));
            return;
        }

        iView.onStartSubmit();
        FeedbackContent feedbackContent = new FeedbackContent();
        feedbackContent.setContent(content.toString());
        if (contact != null) {
            feedbackContent.setContact(contact.toString());
        }
        if (BmobUser.getCurrentUser() != null) {        //默认提交了当前用户的用户名（以防后台联系反馈者，预留）
            feedbackContent.setUserName(BmobUser.getCurrentUser().getUsername());
        }
        feedbackContent.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    iView.onShowRemind(activity.getString(R.string.submit_successful));
                    iView.onFinishActivity();
                } else {
                    iView.onShowRemind(activity.getString(R.string.submit_failure));
                }
                iView.onEndSubmit();
            }
        });
    }

    /**
     * 结束当前Activity
     */
    public void finishCurrentActivity() {
        iView.onFinishActivity();
    }
}
