package com.shixia.diudiuma.mvp.activity;

import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickScanDDMIView;
import com.shixia.diudiuma.mvp.presenter.PresenterQuickScanDDM;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;

/**
 * Created by AmosShi on 2016/10/26.
 * Description:扫码
 */

public class QuickScanGoodsActivity extends BaseActivity implements QuickScanDDMIView {

    private PresenterQuickScanDDM presenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_scan_ddm_page);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterQuickScanDDM(this,this);
        return presenter;
    }

    @Override
    protected void afterActivityOnCreate() {
        super.afterActivityOnCreate();
        presenter.scanZXingImg();
    }

    @Override
    public void onShowRemind(String s) {
        CToast.makeCText(this,s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowScanResult(String s) {
        CToast.makeCText(this,s, Toast.LENGTH_SHORT).show();
        finish();
    }
}
