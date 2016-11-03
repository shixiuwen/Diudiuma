package com.shixia.diudiuma.mvp.activity;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bmob.bean.DDMUser;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.PersonalInfoIView;
import com.shixia.diudiuma.mvp.presenter.PresenterPersonalInfo;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.CommonTitleView;
import com.shixia.diudiuma.view.EditItemView;
import com.shixia.diudiuma.view.LoadingDialog;

import cn.bmob.v3.BmobUser;

/**
 * Created by AmosShi on 2016/11/3.
 * Description:
 */

public class PersonalInfoActivity extends BaseActivity implements PersonalInfoIView {

    private PresenterPersonalInfo presenter;

    private CommonTitleView ctvTitleView;
    private TextView tvName;
    private TextView tvEnglishName;
    private TextView tvTel;
    private TextView tvWechat;
    private TextView tvQq;
    private TextView tvEmail;
    private ImageView imgPersonalIcon;
    private EditItemView eivMyFindGoods;
    private EditItemView eivMyFindLoser;

    @Override
    protected void initContentView() {

        setContentView(R.layout.activity_personal_info);

        ctvTitleView = (CommonTitleView) findViewById(R.id.ctv_title_view);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvEnglishName = (TextView) findViewById(R.id.tv_english_name);
        tvTel = (TextView) findViewById(R.id.tv_tel);
        tvWechat = (TextView) findViewById(R.id.tv_wechat);
        tvQq = (TextView) findViewById(R.id.tv_qq);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        imgPersonalIcon = (ImageView) findViewById(R.id.img_personal_icon);
        eivMyFindGoods = (EditItemView) findViewById(R.id.eiv_my_find_goods);
        eivMyFindLoser = (EditItemView) findViewById(R.id.eiv_my_find_loser);

        if (BmobUser.getCurrentUser() != null) {
//            DDMUser user = (DDMUser)BmobUser.getCurrentUser();
            DDMUser user = BmobUser.getCurrentUser(DDMUser.class);
            tvName.setText(user.getUsername());
            tvEnglishName.setText(user.getEnglishName());
            tvWechat.setText(user.getWeChat());
            tvTel.setText(user.getTele());
            tvQq.setText(user.getQQ());
            tvEmail.setText(user.getEmail());
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
                presenter.toEditPersonalInfo(tvName.getText(), tvEnglishName.getText(),
                        tvTel.getText(), tvWechat.getText(),
                        tvQq.getText(), tvEmail.getText());
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterPersonalInfo(this, this);
        return presenter;
    }

    @Override
    public void onEditCommitStart() {
        LoadingDialog.getInstance(this, "提交数据中……").show();
    }

    @Override
    public void onEditCommitEnd() {
        LoadingDialog.getInstance(this, "提交数据中……").dismiss();
    }

    @Override
    public void onFinishCurrentActivity() {
        this.finish();
    }

    @Override
    public void onShowRemind(String s) {
        CToast.makeCText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPersonalInfoChanged(String name, String englishName, String tele, String wechat, String qq, String email) {
        tvName.setText(name);
        tvEnglishName.setText(englishName);
        tvTel.setText(tele);
        tvWechat.setText(wechat);
        tvQq.setText(qq);
        tvEmail.setText(email);
    }
}
