package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.bean.MultiItemEntity;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;

import java.util.ArrayList;

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

    void onShowEmptyView();

    /**
     * 开始刷新
     */
    void onShowRefresh();

    /**
     * 刷新结束
     */
    void onRefreshEnd();

    /**
     * 显示提示信息
     */
    void onShowRemind(String s);

    /**
     * 刷新数据
     */
    void onNotifyDataSetChange(ArrayList<MultiItemEntity> goodsList);
}
