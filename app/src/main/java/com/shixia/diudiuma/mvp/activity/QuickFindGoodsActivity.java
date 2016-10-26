package com.shixia.diudiuma.mvp.activity;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.AddGoodsIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/26.
 * Description:
 */

public class QuickFindGoodsActivity extends BaseActivity implements AddGoodsIView{
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_find_goods_page);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
