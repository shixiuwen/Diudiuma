package com.shixia.diudiuma.presenter;

import android.content.Context;

import com.shixia.diudiuma.activity.SellCarActivity;
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
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start((SellCarActivity)context, PhotoPicker.REQUEST_CODE);
    }
}
