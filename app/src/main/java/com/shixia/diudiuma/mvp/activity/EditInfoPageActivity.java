package com.shixia.diudiuma.mvp.activity;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.EditInfoPageIView;
import com.shixia.diudiuma.mvp.presenter.PresenterEditInfoPage;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CommonTitleView;
import com.shixia.diudiuma.view.EditPageView;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:
 */

public class EditInfoPageActivity extends BaseActivity implements EditInfoPageIView{

    private CommonTitleView ctvCommonTitleView;
    private EditPageView epvEditView;

    private PresenterEditInfoPage presenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_edit_info_page);

        ctvCommonTitleView = (CommonTitleView) findViewById(R.id.ctv_title_view);
        epvEditView = (EditPageView) findViewById(R.id.epv_edit_view);

    }

    @Override
    protected void initListener() {
        //点击返回或者确认提交
        ctvCommonTitleView.setOnCommonTitleClickListener(new CommonTitleView.OnCommonTitleClickListener() {
            @Override
            public void onBackClickListener() {

            }

            @Override
            public void onSureClickListener() {

            }
        });

        //点击确认提交
        epvEditView.setOnCommitClickListener(() -> {

        });
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterEditInfoPage(this,this);
        return presenter;
    }
}
