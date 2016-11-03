package com.shixia.diudiuma.mvp.activity;

import android.widget.EditText;
import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.FeedbackActivityIView;
import com.shixia.diudiuma.mvp.presenter.PresenterFeedback;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.CommonTitleView;
import com.shixia.diudiuma.view.LoadingDialog;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public class FeedbackActivity extends BaseActivity implements FeedbackActivityIView {

    private PresenterFeedback presenter;

    private CommonTitleView ctvTitleView;
    private EditText etFeedbackContent;
    private EditText etFeedbackContact;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_feedback);

        ctvTitleView = (CommonTitleView) findViewById(R.id.ctv_title_view);
        etFeedbackContent = (EditText) findViewById(R.id.et_feedback_content);
        etFeedbackContact = (EditText) findViewById(R.id.et_feedback_contact);

    }

    @Override
    protected void initListener() {
        ctvTitleView.setOnCommonTitleClickListener(new CommonTitleView.OnCommonTitleClickListener() {
            @Override
            public void onBackClickListener() {
                presenter.finishCurrentActivity();
            }

            @Override
            public void onSureClickListener() {
                presenter.submitFeedback(etFeedbackContent.getText(),etFeedbackContact.getText());
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterFeedback(this,this);
        return presenter;
    }

    @Override
    public void onStartSubmit() {
        LoadingDialog.getInstance(this,getString(R.string.on_submitting_data)).show();
    }

    @Override
    public void onEndSubmit() {
        LoadingDialog.getInstance(this,getString(R.string.on_submitting_data)).dismiss();
    }

    @Override
    public void onShowRemind(String s) {
        CToast.makeCText(this,s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishActivity() {
        this.finish();
    }
}
