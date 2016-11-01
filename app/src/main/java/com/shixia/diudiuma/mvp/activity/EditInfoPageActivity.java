package com.shixia.diudiuma.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.common_utils.SystemUtils;
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

public class EditInfoPageActivity extends BaseActivity implements EditInfoPageIView {

    private CommonTitleView ctvCommonTitleView;
    private EditPageView epvEditView;

    private PresenterEditInfoPage presenter;

    private String defValue;            //传进来的默认值
    private String pageTitle;           //title名
    private boolean isSureBtnVisible;   //是否显示确认按钮
    private String titleRemind;         //提醒头
    private String contentRemind;       //内容输入提醒
    private int iconResourceId;         //输入框图片资源

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_edit_info_page);

        ctvCommonTitleView = (CommonTitleView) findViewById(R.id.ctv_title_view);
        epvEditView = (EditPageView) findViewById(R.id.epv_edit_view);

        Bundle extras = this.getIntent().getExtras();
        defValue = extras.getString("defValue");
        pageTitle = extras.getString("pageTitle");
        isSureBtnVisible = extras.getBoolean("isSureBtnVisible");
        titleRemind = extras.getString("titleRemind");
        contentRemind = extras.getString("contentRemind");
        iconResourceId = extras.getInt("iconResourceId");

        initView();

    }

    private void initView() {
        ctvCommonTitleView.setTvCommonTitle(pageTitle);
        ctvCommonTitleView.setBtnCommonSureVisible(isSureBtnVisible);

        epvEditView.setTvEditTitleRemind(titleRemind);
        epvEditView.setTvEditContentRemind(contentRemind);
        epvEditView.setImgEditIcon(iconResourceId);

        if (!TextUtils.equals(defValue, "点击设置")
                && !TextUtils.equals(defValue, "点击添加")
                && !TextUtils.equals(defValue, "未知")) {
            epvEditView.setTvEditContent(defValue);
        }
    }

    @Override
    protected void initListener() {
        //点击返回或者确认提交
        ctvCommonTitleView.setOnCommonTitleClickListener(new CommonTitleView.OnCommonTitleClickListener() {
            @Override
            public void onBackClickListener() {
                finish();
            }

            @Override
            public void onSureClickListener() {

            }
        });

        //点击确认提交,返回数据
        epvEditView.setOnCommitClickListener(() -> {
            SystemUtils.getInstance(this).hideSoftInput();  //收起软键盘
            Intent intent = new Intent();
            intent.putExtra("value", epvEditView.getEditContent());
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterEditInfoPage(this, this);
        return presenter;
    }
}
