package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;

import com.shixia.diudiuma.bmob.bean.DDMUser;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.mvp.activity.EditPersonalInfoActivity;
import com.shixia.diudiuma.mvp.iview.EditPersonalInfoIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public class PresenterEditPersonalInfo extends BasePresenter {

    private EditPersonalInfoActivity activity;
    private EditPersonalInfoIView iView;

    private String name = null, englishName = null, tele = null, wechat = null, qq = null, email = null;

    public PresenterEditPersonalInfo(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (EditPersonalInfoActivity) context;
        this.iView = (EditPersonalInfoIView) iView;
    }

    public void finishCurrentActivity() {
        iView.onCurrentActivityFinished(null, null, null, null, null, null);
    }

    public void toSubmitChangeInfo(Editable etNameText, Editable etEnglishNameText,
                                   Editable etTeleText, Editable etWechatText,
                                   Editable etQqText, Editable etEmailText) {
        if (etNameText != null) {
            name = etNameText.toString();
        }
        if (TextUtils.isEmpty(name)) {
            iView.onShowRemind("用户名不可为空！");
            return;
        }

        if (etEnglishNameText != null) {
            englishName = etEnglishNameText.toString();
        }
        if (etTeleText != null) {
            tele = etTeleText.toString();
        }
        if (etWechatText != null) {
            wechat = etWechatText.toString();
        }
        if (etQqText != null) {
            qq = etQqText.toString();
        }
        if (etEmailText != null) {
            email = etEmailText.toString();
        }

        iView.onSubmitStart();

        DDMUser user = new DDMUser();
        user.setUsername(name);
        user.setEnglishName(englishName);
        user.setTele(tele);
        user.setWeChat(wechat);
        user.setQQ(qq);
        user.setEmail(email);
        BmobUser currentUser = BmobUser.getCurrentUser();
        user.update(currentUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    iView.onShowRemind("更改个人信息成功！");
                    iView.onCurrentActivityFinished(name, englishName, tele, wechat, qq, email);
                } else {
                    L.i("edit personal info error", e.getMessage());
                    iView.onShowRemind("数据提交失败！");
                }
                iView.onSubmitEnd();
            }
        });
    }
}
