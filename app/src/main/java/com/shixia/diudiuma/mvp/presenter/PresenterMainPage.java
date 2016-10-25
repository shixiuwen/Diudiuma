package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.mvp.iview.DefaultFragmentIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/25.
 * Description:
 */

public class PresenterMainPage extends BasePresenter {

    private Context context;
    private DefaultFragmentIView iView;

    public PresenterMainPage(Context context, BaseIView iView) {
        super(context, iView);
        this.context = context;
        this.iView = (DefaultFragmentIView) iView;
    }

    public void showQuickOptWindow(){
        iView.onShowQuickOptWindow();
    }
}
