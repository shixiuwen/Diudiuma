package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/25.
 * Description:
 */

public interface DefaultFragmentIView extends BaseIView {

    /**
     * 显示快速添加等按钮的弹窗
     */
    void onShowQuickOptWindow();

    /**
     * 弹窗消失
     */
    void onQuickOptWindowDismiss();
}
