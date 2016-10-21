package com.shixia.diudiuma.mvp.iview;


import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public interface SellCarIView extends BaseIView {

    /**
     * 显示例如Toast的提示信息
     * @param msg message
     */
    void onShowRemind(String msg);

    /**
     * 下载进度更新回调
     * @param progress 下载进度
     */
    void onDownloadProgressUpdate(String progress);
}
