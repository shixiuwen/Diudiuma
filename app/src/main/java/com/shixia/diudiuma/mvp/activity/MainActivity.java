package com.shixia.diudiuma.mvp.activity;

import android.widget.Button;
import android.widget.TextView;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.MainActivityIView;
import com.shixia.diudiuma.mvp.presenter.PresenterMain;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.StatusBarCompat;


/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class MainActivity extends BaseActivity implements MainActivityIView {

    private TextView tvLocation;
    private Button btnToSellCar;
    private Button btnFeedback;
    private Button btnGsonTest;
    private Button btnZXing;
    private PresenterMain sellCarPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
        //设置沉浸式状态栏颜色
        StatusBarCompat.compat(this,getResources().getColor(R.color.colorAccent));

        tvLocation = (TextView) findViewById(R.id.tv_location);
        btnToSellCar = (Button) findViewById(R.id.btn_to_sell_car);
        btnFeedback = (Button)findViewById(R.id.btn_feedback);
        btnGsonTest = (Button) findViewById(R.id.btn_test_gson);
        btnZXing = (Button) findViewById(R.id.btn_zxing);
    }

    @Override
    protected void initListener() {
        btnToSellCar.setOnClickListener(v -> sellCarPresenter.toSellCarActivity());
        btnFeedback.setOnClickListener(v -> sellCarPresenter.feedback());
        btnGsonTest.setOnClickListener(v -> sellCarPresenter.gsonTest());
        btnZXing.setOnClickListener(v -> sellCarPresenter.toZXingActivity());
    }

    @Override
    protected BasePresenter initPresenter() {
        sellCarPresenter = new PresenterMain(this,this);
        return sellCarPresenter;
    }

    @Override
    protected void afterActivityOnCreate() {
        super.afterActivityOnCreate();
        sellCarPresenter.locate();  //界面启动后开始定位
    }

    @Override
    public void onStartLocation() {
        tvLocation.setText("正在获取位置信息，请稍后……");
    }

    @Override
    public void onEndLocation(String location) {
        tvLocation.setText(location);
    }

}
