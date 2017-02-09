package com.shixia.diudiuma.mvp.activity;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by ShiXiuwen on 2017/2/9.
 *
 * Description:点击搜索后显示的界面
 */

public class SearchActivity extends BaseActivity {
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
