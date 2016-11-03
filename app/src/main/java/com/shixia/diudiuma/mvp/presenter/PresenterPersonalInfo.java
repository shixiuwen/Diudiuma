package com.shixia.diudiuma.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shixia.diudiuma.mvp.activity.EditPersonalInfoActivity;
import com.shixia.diudiuma.mvp.activity.PersonalInfoActivity;
import com.shixia.diudiuma.mvp.iview.PersonalInfoIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public class PresenterPersonalInfo extends BasePresenter {

    private PersonalInfoActivity activity;
    private PersonalInfoIView iView;

    public PresenterPersonalInfo(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (PersonalInfoActivity) context;
        this.iView = (PersonalInfoIView) iView;
    }

    public void finishCurrentActivity() {
        iView.onFinishCurrentActivity();
    }

    public void toEditPersonalInfo(CharSequence tvName, CharSequence tvEnglishName,
                                   CharSequence tvTel, CharSequence tvWeChat,
                                   CharSequence tvQQ, CharSequence tvEmail) {
        Bundle bundle = new Bundle();
        activity.startActivityForResult(activity, EditPersonalInfoActivity.class, bundle, 0x003, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            String name = data.getStringExtra("name");
            String englishName = data.getStringExtra("englishName");
            String tele = data.getStringExtra("tele");
            String wechat = data.getStringExtra("wechat");
            String qq = data.getStringExtra("qq");
            String email = data.getStringExtra("email");
            iView.onPersonalInfoChanged(name,englishName,tele,wechat,qq,email);
        }
    }
}
