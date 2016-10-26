package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.shixia.diudiuma.bmob.bean.DDMUser;
import com.shixia.diudiuma.common_utils.SpUtil;
import com.shixia.diudiuma.mvp.activity.MainActivity_2;
import com.shixia.diudiuma.mvp.iview.PersonalCenterIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class PresenterPersonalCenter extends BasePresenter {

    private MainActivity_2 activity;
    private PersonalCenterIView iView;

    public PresenterPersonalCenter(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (MainActivity_2) context;
        this.iView = (PersonalCenterIView) iView;
    }

    /**
     * 登录或者退出操作
     */
    public void loginOrExitBtnClick() {
        //1.从本地读取数据判断是否登录，如果登录，点击退出登录，否则显示登录的Dialog
        if (SpUtil.getBoolean(activity, "isLogin", false)) {
            // TODO: 2016/10/24 退出登录

        } else {
            // TODO: 2016/10/24 显示登录的dialog
            iView.onShowLoginDialog();
        }
    }

    /**
     * 注册新用户
     *
     * @param username  用户名
     * @param password  密码
     * @param email     邮箱
     */
    public void register(String username, String password, String email) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return;
        }
        DDMUser ddmUser = new DDMUser();
        ddmUser.setUsername(username);
        // TODO: 2016/10/26 删除该测试数据
        ddmUser.setWeChat("sxw-1991");
        ddmUser.setPassword(password);
        if (!TextUtils.isEmpty(email)) {
            ddmUser.setEmail(email);
        }
        ddmUser.signUp(new SaveListener<DDMUser>() {
            @Override
            public void done(DDMUser ddmUser, BmobException e) {
                if(e == null){
                    //注册成功
                    CToast.makeCText(activity,"注册成功", Toast.LENGTH_SHORT).show();
                    iView.onDismissDialog();
                }else {
                    //注册失败
                    e.getMessage();
                }
            }
        });
    }

    /**
     * 老用户登录
     * @param userName  用户名
     * @param password  密码
     */
    public void login(String userName,String password) {
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)){
            iView.onShowRemind("用户名或密码不能为空！");
            return;
        }
        DDMUser ddmUser = new DDMUser();
        ddmUser.setUsername(userName);
        ddmUser.setPassword(password);
        ddmUser.login(new SaveListener<DDMUser>() {
            @Override
            public void done(DDMUser ddmUser, BmobException e) {
                if(e==null){
                    iView.onShowRemind("登录成功" + ddmUser.getUsername());
                    iView.onDismissDialog();
                    iView.onLoginSuccessful();
                    SpUtil.put(activity,"isLogin",true);    //本地储存已登录标志
                }else{
                    iView.onShowRemind("登录失败");
                }
            }
        });
    }

    /**
     * 点击将界面转转换为注册模式
     */
    public void changeToRegisterDialog() {
        iView.onChangeToRegisterPage();
    }

    public void changeToLoginDialog() {
        iView.onChangeToLoginPage();
    }

    public void dismissDialog() {
        iView.onDismissDialog();
    }
}
