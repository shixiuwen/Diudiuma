package com.shixia.diudiuma.mvp.activity.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/11.
 * <p>
 * Description:activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    private BasePresenter basePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContentView();
        initListener();
        initPresenter();
        afterActivityOnCreate();

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    /**
     * 做类似初始化界面的操作，包括：
     * 1.setContentView(R.layout.activity_splash);
     * 2.findViewById
     */
    protected abstract void initContentView();

    /**
     * 初始化控件的监听事件
     */
    protected abstract void initListener();

    /**
     * 初始化Presenter
     */
    protected abstract BasePresenter initPresenter();

    /**
     * 可选操作，数据初始化之后的操作在该方法中执行
     */
    protected void afterActivityOnCreate() {
    }

    public void startActivity(Context context, Class c, Bundle bundle, boolean isFinishCurrentActivity) {
        Intent intent = new Intent(context, c);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinishCurrentActivity) {
            finish();
        }
    }

    public void startActivityForResult(Context context, Class c, Bundle bundle, int resultCode, boolean isFinishCurrentActivity) {
        Intent intent = new Intent(context, c);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
//        startActivity(intent);
        startActivityForResult(intent, resultCode);
        if (isFinishCurrentActivity) {
            finish();
        }
    }

    /**
     * 权限申请的回调
     *
     * @param requestCode  请求码
     * @param permissions  请求的权限
     * @param grantResults 请求结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (basePresenter == null) {
            basePresenter = initPresenter();
        }
        if (basePresenter == null) {    //初始化之后依然为空，不处理
            return;
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            basePresenter.getBasePresenter().doBizWithPermissionRequest(requestCode, permissions);
        } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            basePresenter.getBasePresenter().doBizWithPermissionRequest(PermissionUtils.PERMISSION_DENIED, permissions);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (basePresenter == null) {
            basePresenter = initPresenter();
        }
        if (basePresenter == null) {
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            basePresenter.getBasePresenter().onActivityResult(requestCode, resultCode, data);
        }
    }
}
