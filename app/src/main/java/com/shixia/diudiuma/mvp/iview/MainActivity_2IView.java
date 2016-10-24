package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public interface MainActivity_2IView extends BaseIView {
    /**
     * 切换引起的界面布局变化
     * @param position 切换到的页面 0~3
     */
    void onPageChanged(int position);
}
