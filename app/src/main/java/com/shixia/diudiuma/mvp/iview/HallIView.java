package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;

import java.util.List;

/**
 * Created by AmosShi on 2016/11/1.
 * Description:
 */

public interface HallIView extends BaseIView {

    /**
     * 重新加载数据后刷新界面
     * @param loserGoodsInfosList 重新加载的数据列表
     */
    void onNotifyDataSetChange(List<LoserGoodsInfo> loserGoodsInfosList);

    /**
     * 刷新结束
     */
    void onRefreshEnd();

    /**
     * 显示提示信息
     * @param s 提示信息
     */
    void onShowRemind(String s);

    /**
     * 开始查询，显示Loading
     */
    void onSearchBegin();

    /**
     * 查询结束，dismiss Loading
     */
    void onSearchEnd();
}
