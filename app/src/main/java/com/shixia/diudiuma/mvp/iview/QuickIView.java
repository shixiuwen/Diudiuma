package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:
 */

public interface QuickIView extends BaseIView {

    /**
     * 编辑完value后的回调
     * @param value 为Edit Item新设置的值
     */
    void onChangeValueAfterEdit(int requestCode,String value);

    void onShowRemind(String content);
}
