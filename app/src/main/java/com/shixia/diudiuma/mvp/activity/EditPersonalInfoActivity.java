package com.shixia.diudiuma.mvp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bmob.bean.DDMUser;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.EditPersonalInfoIView;
import com.shixia.diudiuma.mvp.presenter.PresenterEditPersonalInfo;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.CommonTitleView;
import com.shixia.diudiuma.view.LoadingDialog;

import cn.bmob.v3.BmobUser;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public class EditPersonalInfoActivity extends BaseActivity implements EditPersonalInfoIView {

    private PresenterEditPersonalInfo presenter;

    private CommonTitleView ctvTitleView;
    private EditText etName;
    private EditText etEnglishName;
    private EditText etTele;
    private EditText etWechat;
    private EditText etQq;
    private EditText etEmail;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_edit_personal_info);

        ctvTitleView = (CommonTitleView) findViewById(R.id.ctv_title_view);
        etName = (EditText) findViewById(R.id.et_name);
        etEnglishName = (EditText) findViewById(R.id.et_english_name);
        etTele = (EditText) findViewById(R.id.et_tele);
        etWechat = (EditText) findViewById(R.id.et_wechat);
        etQq = (EditText) findViewById(R.id.et_qq);
        etEmail = (EditText) findViewById(R.id.et_email);

        if (BmobUser.getCurrentUser() != null) {
//            DDMUser user = (DDMUser)BmobUser.getCurrentUser();    //不可强转
            DDMUser user = BmobUser.getCurrentUser(DDMUser.class);
            etName.setText(user.getUsername());
            etEnglishName.setText(user.getEnglishName());
            etWechat.setText(user.getWeChat());
            etTele.setText(user.getTele());
            etQq.setText(user.getQQ());
            etEmail.setText(user.getEmail());
        }
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
                presenter.toSubmitChangeInfo(etName.getText(), etEnglishName.getText(),
                        etTele.getText(), etWechat.getText(),
                        etQq.getText(), etEmail.getText());
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterEditPersonalInfo(this, this);
        return presenter;
    }

    @Override
    public void onShowRemind(String s) {
        CToast.makeCText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCurrentActivityFinished(String name, String englishName, String tele, String wechat, String qq, String email) {
        if(!TextUtils.isEmpty(name)){
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("englishName", englishName);
            intent.putExtra("tele", tele);
            intent.putExtra("wechat", wechat);
            intent.putExtra("qq", qq);
            intent.putExtra("email", email);

            setResult(RESULT_OK, intent);
        }

        this.finish();
    }

    @Override
    public void onSubmitStart() {
        LoadingDialog.getInstance(this,"数据提交中……").show();
    }

    @Override
    public void onSubmitEnd() {
        LoadingDialog.getInstance(this,"数据提交中……").dismiss();
    }
}
