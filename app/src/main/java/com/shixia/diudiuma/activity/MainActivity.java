package com.shixia.diudiuma.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.TextView;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.activity.base.BaseActivity;
import com.shixia.diudiuma.iview.MainActivityIView;
import com.shixia.diudiuma.presenter.PresenterMain;


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
    protected void initPresenter() {
        sellCarPresenter = new PresenterMain(this,this);
    }

    @Override
    protected void afterActivityOnCreate() {
        super.afterActivityOnCreate();
        startLocate();  //界面启动后开始定位
    }

    @Override
    public void onStartLocation() {
        tvLocation.setText("正在获取位置信息，请稍后……");
    }

    @Override
    public void onEndLocation(String location) {
        tvLocation.setText(location);
    }

    /**
     * 检测应用权限后定位
     */
    private void startLocate(){
        //SDK在Android 6.0下需要进行运行检测的权限如下：
//        Manifest.permission.ACCESS_COARSE_LOCATION,
//        Manifest.permission.ACCESS_FINE_LOCATION,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.READ_PHONE_STATE

        //这里以ACCESS_COARSE_LOCATION为例
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
        }else {
            sellCarPresenter.locate();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == WRITE_COARSE_LOCATION_REQUEST_CODE && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sellCarPresenter.locate();
        }
    }
}
