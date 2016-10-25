package com.shixia.diudiuma.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixia.diudiuma.R;

/**
 * Created by AmosShi on 2016/10/25.
 *
 * Description:用于登录或者注册使用的popupWindow
 */

public class LoginOrRegisterPopupWindow extends PopupWindow implements View.OnClickListener{

    private Context context;

    //popWindow
    private View popView;
    private Button btnLoginPop;
    private TextView tvRegisterPop;
    private RelativeLayout rlLoginCardPop;
    private RelativeLayout rlRegisterCardPop;
    private boolean isCardChanged;
    private ImageButton imgBtnCloseDialogLoginPop;
    private EditText etLoginNamePop;
    private EditText etLoginPasswordPop;
    private ImageButton imgBtnBackToLoginDialogPop;
    private ImageButton imgBtnCloseDialogRegisterPop;
    private EditText etRegisterNamePop;
    private EditText etRegisterPasswordPop;
    private EditText etRegisterRePasswordPop;
    private Button btnRegisterPop;
    private PopupWindow popupWindow;

    public LoginOrRegisterPopupWindow(Context context) {
        super(context);
        this.context = context;

        initPopWindowView();
        initPopWindowListener();
    }

    private View initPopWindowView() {
        //显示登录注册弹窗
        popView = LayoutInflater.from(context).inflate(R.layout.view_pop_login_or_regiester_layout, null);
        btnLoginPop = (Button) popView.findViewById(R.id.btn_login);
        tvRegisterPop = (TextView) popView.findViewById(R.id.tv_register);
        rlLoginCardPop = (RelativeLayout) popView.findViewById(R.id.rl_login_card);
        rlRegisterCardPop = (RelativeLayout) popView.findViewById(R.id.rl_register_card);
        imgBtnCloseDialogLoginPop = (ImageButton) popView.findViewById(R.id.imgBtn_close_dialog_login);
        etLoginNamePop = (EditText) popView.findViewById(R.id.et_login_name);
        etLoginPasswordPop = (EditText) popView.findViewById(R.id.et_login_password);
        imgBtnBackToLoginDialogPop = (ImageButton) popView.findViewById(R.id.imgBtn_back_to_login_dialog);
        imgBtnCloseDialogRegisterPop = (ImageButton) popView.findViewById(R.id.imgBtn_close_dialog_register);
        etRegisterNamePop = (EditText) popView.findViewById(R.id.et_register_name);
        etRegisterPasswordPop = (EditText) popView.findViewById(R.id.et_register_password);
        etRegisterRePasswordPop = (EditText) popView.findViewById(R.id.et_register_re_password);
        btnRegisterPop = (Button) popView.findViewById(R.id.btn_register);

        ObjectAnimator.ofFloat(rlRegisterCardPop, "rotationY", 0.0f, 180.0f).setDuration(500).start();
        return popView;
    }

    private void initPopWindowListener() {
        btnLoginPop.setOnClickListener(this);
        tvRegisterPop.setOnClickListener(this);
        imgBtnBackToLoginDialogPop.setOnClickListener(this);
        imgBtnCloseDialogLoginPop.setOnClickListener(this);
        imgBtnCloseDialogRegisterPop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }
}
