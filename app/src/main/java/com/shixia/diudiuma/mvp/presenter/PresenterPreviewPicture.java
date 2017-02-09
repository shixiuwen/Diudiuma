package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.mvp.iview.PreviewPictureIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by ShiXiuwen on 2017/2/9.
 * Description:
 */

public class PresenterPreviewPicture extends BasePresenter {

    private Context context;
    private PreviewPictureIView iView;

    public PresenterPreviewPicture(Context context, BaseIView iView) {
        super(context, iView);
        this.context = context;
        this.iView = (PreviewPictureIView) iView;
    }

    /**
     * 点击图片结束预览
     */
    public void finishPreview() {
        iView.onPreviewFinish();
    }
}
