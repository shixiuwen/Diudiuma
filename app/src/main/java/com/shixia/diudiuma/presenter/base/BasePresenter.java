package com.shixia.diudiuma.presenter.base;

import android.content.Context;

import com.shixia.diudiuma.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/11.
 * Description:
 */

public class BasePresenter {

    public BasePresenter(Context context, BaseIView iView){

    }

    public BasePresenter getBasePresenter(){
        return this;
    }

    /**
     * 申请完权限后需要进行的操作
     */
    public void doBizWithPermissionRequest(int requestCode, String[] permissions){

    }
}
