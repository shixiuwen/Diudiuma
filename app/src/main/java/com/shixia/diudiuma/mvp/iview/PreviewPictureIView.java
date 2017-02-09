package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by ShiXiuwen on 2017/2/9.
 *
 * Description:对应预览图片界面
 */

public interface PreviewPictureIView extends BaseIView {
    /**
     * 结束预览，关闭当前页面
     */
    void onPreviewFinish();
}
