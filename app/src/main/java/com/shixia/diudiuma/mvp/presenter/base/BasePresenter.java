package com.shixia.diudiuma.mvp.presenter.base;

import android.content.Context;
import android.content.Intent;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

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
     *
     */
    public void doBizWithPermissionRequest(int requestCode, String[] permissions){

    }

    /**
     * 调用完startActivityForResult()后的回调
     * @param requestCode   请求码
     * @param resultCode    结果码
     * @param data          请求结果携带的数据
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
