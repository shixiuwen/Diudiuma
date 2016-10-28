package com.shixia.diudiuma.mvp.activity;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickAddGoodsIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/26.
 * Description:
 */

public class QuickScanGoodsActivity extends BaseActivity implements QuickAddGoodsIView {
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_scan_ddm_page);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onChangeValueAfterEdit(int requestCode, String value) {

    }

    @Override
    public void onShowRemind(String content) {

    }
}
