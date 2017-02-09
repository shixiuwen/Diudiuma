package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.Toast;

import com.shixia.diudiuma.bmob.bean.DDMUser;
import com.shixia.diudiuma.common_utils.ImageFactory;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.mvp.activity.AboutUsActivity;
import com.shixia.diudiuma.mvp.activity.FeedbackActivity;
import com.shixia.diudiuma.mvp.activity.MainActivity_2;
import com.shixia.diudiuma.mvp.activity.PersonalInfoActivity;
import com.shixia.diudiuma.mvp.iview.PersonalCenterIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.BaseDialog;
import com.shixia.diudiuma.view.CToast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
     * 进入页面的时候初始化界面信息，用户名，头像等
     */
    public void initPersonalData() {
        if (BmobUser.getCurrentUser() != null) {
            DDMUser currentUser = BmobUser.getCurrentUser(DDMUser.class);
            iView.onShowUserName(currentUser.getUsername());
            String avatar = currentUser.getAvatar();
            if (avatar != null) {
                Observable.create(new Observable.OnSubscribe<Bitmap>() {
                    @Override
                    public void call(Subscriber<? super Bitmap> subscriber) {
                        subscriber.onNext(ImageFactory.returnBitmap(avatar));
                    }
                })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(bitmap -> {
                            iView.onShowUserAvatar(bitmap);
                        });
            }
        }
    }

    /**
     * 登录或者退出操作
     */
    public void loginOrExitBtnClick() {
        //1.从本地读取数据判断是否登录，如果登录，点击退出登录，否则显示登录的Dialog
        BmobUser currentUser = BmobUser.getCurrentUser();
        if (currentUser != null) {    //已登录，点击退出登录
            BaseDialog baseDialog = new BaseDialog(activity,"退出登录","确认退出登录吗？","确认");
            baseDialog.setOnDialogClickListener(new BaseDialog.OnDialogClickListener() {
                @Override
                public void onSureClickListener() {
                    BmobUser.logOut();
                    iView.onChangeLoginStatus(false);
                    baseDialog.dismiss();
                }

                @Override
                public void onCancelClickListener() {
                    baseDialog.dismiss();
                }
            });
            baseDialog.show();

        } else {                     //未登录，点击登录
            iView.onShowLoginDialog();
        }
    }

    /**
     * 注册新用户
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     */
    public void register(String username, String password, String rePassword, String email) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
            iView.onShowRemind("您输入的信息不完整，请重新输入");
            return;
        }
        if (!TextUtils.equals(password, rePassword)) {
            iView.onShowRemind("两次输入的密码不一致，请重新输入");
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
                if (e == null) {
                    //注册成功
                    CToast.makeCText(activity, "注册成功", Toast.LENGTH_SHORT).show();
                    iView.onDismissDialog();
                } else {
                    //注册失败
                    e.getMessage();
                }
            }
        });
    }

    /**
     * 老用户登录
     *
     * @param userName 用户名
     * @param password 密码
     */
    public void login(String userName, String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            iView.onShowRemind("用户名或密码不能为空！");
            return;
        }
        DDMUser ddmUser = new DDMUser();
        ddmUser.setUsername(userName);
        ddmUser.setPassword(password);
        ddmUser.login(new SaveListener<DDMUser>() {
            @Override
            public void done(DDMUser ddmUser, BmobException e) {
                if (e == null) {
                    iView.onShowRemind("登录成功" + ddmUser.getUsername());
                    iView.onDismissDialog();
                    iView.onLoginSuccessful();
                } else {
                    L.i("login", e.getMessage());
                    iView.onShowRemind("登录失败," + e.getMessage());
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

    /**
     * 个人信息页面
     */
    public void showPersonalInfo() {
        activity.startActivity(activity, PersonalInfoActivity.class,null,false);
    }

    /**
     * 设置界面
     */
    public void showSetting() {
        iView.onShowRemind("该功能暂未开通");
    }

    /**
     * 版本检测
     */
    public void versionCheck() {
        iView.onShowRemind("当前是最新版本");
    }

    /**
     * 意见反馈界面
     */
    public void toFeedback() {
        activity.startActivity(activity, FeedbackActivity.class,null,false);
    }

    /**
     * 关于我们界面
     */
    public void showAboutUs() {
        activity.startActivity(activity, AboutUsActivity.class,null,false);
    }
}
