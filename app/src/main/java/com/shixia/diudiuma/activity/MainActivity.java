package com.shixia.diudiuma.activity;

import android.widget.Button;
import android.widget.TextView;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.activity.base.BaseActivity;
import com.shixia.diudiuma.iview.MainActivityIView;
import com.shixia.diudiuma.presenter.PresenterMain;
import com.shixia.diudiuma.presenter.base.BasePresenter;


/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class MainActivity extends BaseActivity implements MainActivityIView {

    private TextView tvLocation;
    private Button btnToSellCar;
    private Button btnFeedback;
    private Button btnGsonTest;
    private PresenterMain sellCarPresenter;

    private static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 0x100;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);

        tvLocation = (TextView) findViewById(R.id.tv_location);
        btnToSellCar = (Button) findViewById(R.id.btn_to_sell_car);
        btnFeedback = (Button)findViewById(R.id.btn_feedback);
        btnGsonTest = (Button) findViewById(R.id.btn_test_gson);
    }

    @Override
    protected void initListener() {
        btnToSellCar.setOnClickListener(v -> sellCarPresenter.toSellCarActivity());
        btnFeedback.setOnClickListener(v -> sellCarPresenter.feedback());
        btnGsonTest.setOnClickListener(v -> sellCarPresenter.gsonTest());
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