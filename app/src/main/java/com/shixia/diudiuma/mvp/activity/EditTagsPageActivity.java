package com.shixia.diudiuma.mvp.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.common_utils.SystemUtils;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.EditTagsPageIView;
import com.shixia.diudiuma.mvp.presenter.PresenterEditTagsPage;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.CommonTitleView;
import com.shixia.diudiuma.view.FlowLayoutTag;
import com.shixia.diudiuma.view.TagTextView;

/**
 * Created by AmosShi on 2016/11/17.
 * Description:注意标签个数限制，最多8个
 */

public class EditTagsPageActivity extends BaseActivity implements EditTagsPageIView {

    private PresenterEditTagsPage presenter;

    private CommonTitleView ctvCommonTitleView;
    private FlowLayoutTag fltTags;

    private String tags;         //传入的标签

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_edit_tag_page);

        ctvCommonTitleView = (CommonTitleView) findViewById(R.id.ctv_title_view);
        ctvCommonTitleView.setBtnCommonSureText("确定");
        fltTags = (FlowLayoutTag) findViewById(R.id.flt_tags);
        fltTags.setHorizontalSpacing(getResources().getDimension(R.dimen.x24));
        fltTags.setVerticalSpacing(getResources().getDimension(R.dimen.y24));

        Bundle extras = this.getIntent().getExtras();
        tags = extras.getString("tags");

        initView();

    }

    private void initView() {
        ctvCommonTitleView.setTvCommonTitle("编辑标签");
        ctvCommonTitleView.setBtnCommonSureVisible(true);

        initTags();
    }

    /**
     * 初始化标签数据
     */
    private void initTags() {
        TagTextView tagTextView = new TagTextView(this);
        tagTextView.setText("自定义");
        tagTextView.setOnClickListener(v -> CToast.makeCText(this, "添加自定义标签", Toast.LENGTH_LONG).show());
        fltTags.addView(tagTextView);
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
                presenter.submit();
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterEditTagsPage(this,this);
        presenter.addTag(tags);
        return presenter;
    }

    @Override
    public void onAddTag(String tag) {
        TagTextView tagTextView = new TagTextView(this);
        tagTextView.setText(tag);
        tagTextView.setTag(tagTextView.getText().toString());
        tagTextView.setOnClickListener(v -> presenter.removeTag((String) tagTextView.getTag()));
        fltTags.addView(tagTextView,fltTags.getChildCount()-1);
    }

    @Override
    public void onRemoveTag(String tag) {
        fltTags.removeView(fltTags.findViewWithTag(tag));
    }

    @Override
    public void onSubmitTags(String tags) {
        SystemUtils.getInstance(EditTagsPageActivity.this).hideSoftInput();  //收起软键盘
        finish();
    }

    @Override
    public void onShowRemind(String content) {

    }
}
