package com.shixia.diudiuma.mvp.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.fragment.base.BaseFragment;
import com.shixia.diudiuma.mvp.iview.PersonalCenterIView;
import com.shixia.diudiuma.mvp.presenter.PresenterPersonalCenter;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.EditLoginInfoView;

import cn.bmob.v3.BmobUser;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class MainPagePersonalFragment extends BaseFragment implements PersonalCenterIView {

    private Button btnLoginOrExit;  //登录或者退出按钮
    private PresenterPersonalCenter presenter;
    private View view;

    //popWindow
    private View popView;
    private Button btnLoginPop;
    private TextView tvRegisterPop;
    private RelativeLayout rlLoginCardPop;
    private RelativeLayout rlRegisterCardPop;
    private boolean isCardChanged;
    private ImageButton imgBtnCloseDialogLoginPop;
    private ImageButton imgBtnBackToLoginDialogPop;
    private ImageButton imgBtnCloseDialogRegisterPop;
    private Button btnRegisterPop;
    private PopupWindow popupWindow;

    private EditLoginInfoView eivUserName;
    private EditLoginInfoView eivPassword;
    private EditLoginInfoView eivRegisterName;
    private EditLoginInfoView eivRegisterPassword;
    private EditLoginInfoView eivRegisterRePassword;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_page_personal_center_layout, container, false);
        //设置沉浸式状态栏颜色
        btnLoginOrExit = (Button) view.findViewById(R.id.btn_login_or_exit);
        return view;
    }

    @Override
    public BasePresenter initPresenter() {
        presenter = new PresenterPersonalCenter(getActivity(), this);
        return presenter;
    }

    @Override
    public void initListener() {
        //点击登录或者退出
        btnLoginOrExit.setOnClickListener(v -> presenter.loginOrExitBtnClick());
    }

    @Override
    public void afterFragmentCreated() {
        super.afterFragmentCreated();
        if (BmobUser.getCurrentUser() != null) {   //如果已登录，显示退出登录
            btnLoginOrExit.setText("退出登录");
        } else {                                                    //如果未登录，显示登录
            btnLoginOrExit.setText("登录");
        }
    }

    @Override
    public void onShowLoginDialog() {
        //显示登录弹窗
        View popView = initPopWindowView();
        //设置按钮监听事件
        initPopWindowListener();
        //设置popWindow相关属性
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.login_pop_anim);
        popupWindow.setFocusable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)); //点击外部消失
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(view, 0, 0, Gravity.CENTER);
    }

    private View initPopWindowView() {
        //显示登录注册弹窗
        popView = LayoutInflater.from(getActivity()).inflate(R.layout.view_pop_login_or_regiester_layout, null);
        btnLoginPop = (Button) popView.findViewById(R.id.btn_login);
        tvRegisterPop = (TextView) popView.findViewById(R.id.tv_register);
        rlLoginCardPop = (RelativeLayout) popView.findViewById(R.id.rl_login_card);
        rlRegisterCardPop = (RelativeLayout) popView.findViewById(R.id.rl_register_card);
        imgBtnCloseDialogLoginPop = (ImageButton) popView.findViewById(R.id.imgBtn_close_dialog_login);
        imgBtnBackToLoginDialogPop = (ImageButton) popView.findViewById(R.id.imgBtn_back_to_login_dialog);
        imgBtnCloseDialogRegisterPop = (ImageButton) popView.findViewById(R.id.imgBtn_close_dialog_register);
        btnRegisterPop = (Button) popView.findViewById(R.id.btn_register);

        eivUserName = (EditLoginInfoView) popView.findViewById(R.id.eiv_user_name);
        eivPassword = (EditLoginInfoView) popView.findViewById(R.id.eiv_password);
        eivRegisterName = (EditLoginInfoView) popView.findViewById(R.id.eiv_register_name);
        eivRegisterPassword = (EditLoginInfoView) popView.findViewById(R.id.eiv_register_password);
        eivRegisterRePassword = (EditLoginInfoView) popView.findViewById(R.id.eiv_register_re_password);

        ObjectAnimator.ofFloat(rlRegisterCardPop, "rotationY", 0.0f, 180.0f).setDuration(500).start();
        return popView;
    }

    private void initPopWindowListener() {
        tvRegisterPop.setOnClickListener(v -> presenter.changeToRegisterDialog());
        imgBtnBackToLoginDialogPop.setOnClickListener(v -> presenter.changeToLoginDialog());
        imgBtnCloseDialogLoginPop.setOnClickListener(v -> presenter.dismissDialog());
        imgBtnCloseDialogRegisterPop.setOnClickListener(v -> presenter.dismissDialog());
        //点击注册
        btnRegisterPop.setOnClickListener(v -> presenter.register(eivRegisterName.getEtLoginInfo(), eivRegisterPassword.getEtLoginInfo(),
                eivRegisterRePassword.getEtLoginInfo(), null));
        //点击登录
        btnLoginPop.setOnClickListener(v -> presenter.login(eivUserName.getEtLoginInfo(), eivPassword.getEtLoginInfo()));
    }

    @Override
    public void onLoginSuccessful() {
        btnLoginOrExit.setText("退出登录");
    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onChangeToRegisterPage() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(popView, "rotationY", 0.0f, 180.0f).setDuration(500);
        animator.start();
//        isCardChanged = false;
        animator.addUpdateListener(animation -> {
            float arg = (float) animation.getAnimatedValue();
            if ((int) arg / 30 == 3 /*&& !isCardChanged*/) {
                rlLoginCardPop.setVisibility(View.GONE);
                rlRegisterCardPop.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(rlLoginCardPop, "rotationY", 0.0f, 180.0f).setDuration(500).start();
//                isCardChanged = true;
                animator.removeAllListeners();
            }
        });
    }

    @Override
    public void onChangeToLoginPage() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(popView, "rotationY", 360.0f, 180.0f).setDuration(500);
        animator.start();
//        isCardChanged = false;
        animator.addUpdateListener(animation -> {
            float arg = (float) animation.getAnimatedValue();
            if ((int) arg / 90 == 3 /*&& !isCardChanged*/) {
                rlLoginCardPop.setVisibility(View.VISIBLE);
                rlRegisterCardPop.setVisibility(View.GONE);
                ObjectAnimator.ofFloat(rlRegisterCardPop, "rotationY", 0.0f, 180.0f).setDuration(500).start();
//                isCardChanged = true;
                animation.removeAllListeners();
            }
        });
    }

    @Override
    public void onRegisterSuccessful() {

    }

    @Override
    public void onRegisterFailure() {

    }

    @Override
    public void onShowRemind(String content) {
        CToast.makeCText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismissDialog() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    @Override
    public void onChangeLoginStatus(boolean isLogin) {
        if(isLogin){
            btnLoginOrExit.setText("退出登录");
        }else {
            btnLoginOrExit.setText("登录");
        }
    }
}
