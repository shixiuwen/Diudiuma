package com.shixia.diudiuma.mvp.activity;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CommonTitleView;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public class AboutUsActivity extends BaseActivity {

    private CommonTitleView ctvTitleView;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_about_us);

        ctvTitleView = (CommonTitleView) findViewById(R.id.ctv_title_view);
    }

    @Override
    protected void initListener() {

        ctvTitleView.setOnCommonTitleClickListener(new CommonTitleView.OnCommonTitleClickListener() {
            @Override
            public void onBackClickListener() {
                finish();
            }

            @Override
            public void onSureClickListener() {

            }
        });

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
