package com.shixia.diudiuma.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.common_utils.SpUtil;
import com.shixia.diudiuma.mvp.fragment.base.BaseFragment;
import com.shixia.diudiuma.mvp.iview.PersonalCenterIView;
import com.shixia.diudiuma.mvp.presenter.PresenterPersonalCenter;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class MainPagePersonalFragment extends BaseFragment implements PersonalCenterIView{

    private Button btnLoginOrExit;  //登录或者退出按钮
    private PresenterPersonalCenter presenter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page_personal_center_layout,container,false);
        //设置沉浸式状态栏颜色
        btnLoginOrExit = (Button) view.findViewById(R.id.btn_login_or_exit);
        return view;
    }

    @Override
    public BasePresenter initPresenter() {
        presenter = new PresenterPersonalCenter(getActivity(),this);
        return presenter;
    }

    @Override
    public void initListener() {
        //点击登录或者退出
        btnLoginOrExit.setOnClickListener(v -> presenter.loginOrExit());
    }

    @Override
    public void afterFragmentCreated() {
        super.afterFragmentCreated();
        if(SpUtil.getBoolean(getActivity(),"isLogin",false)){
            btnLoginOrExit.setText("登录");
        }else {
            btnLoginOrExit.setText("退出登录");
        }
    }

    @Override
    public void onShowLoginDialog() {

    }

    @Override
    public void onLoginSuccessful() {

    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onChangeToRegisterPage() {

    }

    @Override
    public void onRegisterSuccessful() {

    }

    @Override
    public void onRegisterFailure() {

    }

    @Override
    public void onShowRemind() {

    }
}
