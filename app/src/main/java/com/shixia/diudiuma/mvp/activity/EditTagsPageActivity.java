package com.shixia.diudiuma.mvp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
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
import com.shixia.diudiuma.view.TagTextRedView;
import com.shixia.diudiuma.view.TagTextView;

/**
 * Created by AmosShi on 2016/11/17.
 * Description:注意标签个数限制，最多8个
 */

public class EditTagsPageActivity extends BaseActivity implements EditTagsPageIView {

    private PresenterEditTagsPage presenter;

    private CommonTitleView ctvCommonTitleView;
    private FlowLayoutTag fltTags;

    private FlowLayoutTag fltTagsUsed;
    private EditText etCustomTag;
    private TextView tvSureAddCustomTag;

    private String tags;         //传入的标签

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_edit_tag_page);

        ctvCommonTitleView = (CommonTitleView) findViewById(R.id.ctv_title_view);
        ctvCommonTitleView.setBtnCommonSureText("确定");
        fltTags = (FlowLayoutTag) findViewById(R.id.flt_tags);
        fltTags.setHorizontalSpacing(getResources().getDimension(R.dimen.x24));
        fltTags.setVerticalSpacing(getResources().getDimension(R.dimen.y24));

        fltTagsUsed = (FlowLayoutTag) findViewById(R.id.flt_tags_used); //常用标签
        fltTagsUsed.setHorizontalSpacing(getResources().getDimension(R.dimen.x24));
        fltTagsUsed.setVerticalSpacing(getResources().getDimension(R.dimen.y24));
        etCustomTag = (EditText) findViewById(R.id.et_custom_tag);  //自定义标签输入框
        tvSureAddCustomTag = (TextView) findViewById(R.id.tv_sure_add_custom_tag);  //确认添加自定义标签

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
        tagTextView.setOnClickListener(v -> {
            etCustomTag.requestFocus();
            SystemUtils.getInstance(this).hideOrShowSoftInput(etCustomTag);
        });
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

        tvSureAddCustomTag.setOnClickListener(v -> presenter.addNewTag(etCustomTag.getText()));
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterEditTagsPage(this, this);
        presenter.initTag(tags);
        return presenter;
    }

    @Override
    public void onAddTag(String tag, boolean isAddToTagList, boolean isAddToAlwaysUsedTag) {
        if (isAddToTagList) {
            TagTextView tagTextView = new TagTextView(this);
            tagTextView.setText(tag);
            tagTextView.setTag(tagTextView.getText().toString());
            tagTextView.setOnClickListener(v -> presenter.removeTag((String) tagTextView.getTag()));
            fltTags.addView(tagTextView, fltTags.getChildCount() - 1);  //添加普通标签
        }
        if (isAddToAlwaysUsedTag) {   //当前为添加自定义标签,添加到常用标签列表
            TagTextRedView tagTextRedView = new TagTextRedView(this);
            tagTextRedView.setText(tag);
            tagTextRedView.setTag(tagTextRedView.getText().toString());
            tagTextRedView.setOnClickListener(v -> presenter.addTag((String) tagTextRedView.getTag()));
            fltTagsUsed.addView(tagTextRedView);
            fltTagsUsed.requestLayout();
            fltTagsUsed.invalidate();
        }
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
        CToast.makeCText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddTagFinish() {
        SystemUtils.getInstance(this).hideOrShowSoftInput(etCustomTag);
        etCustomTag.setText(null);  //清空输入框
    }
}
