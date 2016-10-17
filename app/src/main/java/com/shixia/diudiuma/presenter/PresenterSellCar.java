package com.shixia.diudiuma.presenter;

import android.Manifest;
import android.content.Context;

import com.shixia.diudiuma.activity.SellCarActivity;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.iview.SellCarIView;
import com.shixia.diudiuma.iview.base.BaseIView;
import com.shixia.diudiuma.presenter.base.BasePresenter;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class PresenterSellCar extends BasePresenter {
    private Context context;
    private SellCarIView iView;

    public PresenterSellCar(Context context, BaseIView iView) {
        super(context, iView);
        this.context = context;
        this.iView = (SellCarIView) iView;
    }

    public void showPhonePicker(){
        PermissionUtils.initCurrentActivity((SellCarActivity)context,this);
        PermissionUtils.doBizAfterRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,PermissionUtils.WRITE_COARSE_LOCATION_REQUEST_CODE);
        //申请权限通过则执行doBizWithPermissionRequest()中的内容
    }

    @Override
    public void doBizWithPermissionRequest(int resultCode, String[] permissions) {
        super.doBizWithPermissionRequest(resultCode, permissions);
        if((resultCode == PermissionUtils.PERMISSION_ALREADY_GRANTED && permissions == null)    //该情况表示上一次已经取得了授权
                || (resultCode == PermissionUtils.READ_EXTERNAL_STORAGE
                && permissions != null
                && permissions.length > 0
                && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE))){  //该情况表示本次需要请求授权然后执行接下来的操作

            PhotoPicker.builder()
                    .setPhotoCount(9)
                    .setShowCamera(true)
                    .setShowGif(true)
                    .setPreviewEnabled(false)
                    .start((SellCarActivity)context, PhotoPicker.REQUEST_CODE);
        }else if(resultCode == PermissionUtils.PERMISSION_DENIED){

        }
    }
}
