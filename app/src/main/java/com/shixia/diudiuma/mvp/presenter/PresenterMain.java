package com.shixia.diudiuma.mvp.presenter;

import android.Manifest;
import android.content.Context;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.shixia.diudiuma.MyApplication;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.http.base.HttpApiBase;
import com.shixia.diudiuma.http.feedback.FeedbackApi;
import com.shixia.diudiuma.http.feedback.FeedbackHttpRequest;
import com.shixia.diudiuma.http.feedback.FeedbackHttpResponse;
import com.shixia.diudiuma.mvp.activity.MainActivity;
import com.shixia.diudiuma.mvp.activity.SellCarActivity;
import com.shixia.diudiuma.mvp.activity.ZXingActivity;
import com.shixia.diudiuma.mvp.iview.MainActivityIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.LoadingDialog;


/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class PresenterMain extends BasePresenter implements AMapLocationListener {

    private Context context;
    private MainActivityIView iView;
    private AMapLocationClient mLocationClient;

    public PresenterMain(Context context, BaseIView iView) {
        super(context, iView);
        this.context = context;
        this.iView = (MainActivityIView) iView;
    }

    /**
     * 启动定位
     */
    public void locate() {
        iView.onStartLocation();

        //SDK在Android 6.0下需要进行运行检测的权限如下：
//        Manifest.permission.ACCESS_COARSE_LOCATION,
//        Manifest.permission.ACCESS_FINE_LOCATION,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.READ_PHONE_STATE

        //这里以ACCESS_COARSE_LOCATION为例
        PermissionUtils.initCurrentActivity((MainActivity)context,this);
        PermissionUtils.doBizAfterRequestPermission(Manifest.permission.ACCESS_FINE_LOCATION,PermissionUtils.WRITE_COARSE_LOCATION_REQUEST_CODE);
    }

    public void toSellCarActivity() {
        ((MainActivity) context).startActivity(context, SellCarActivity.class, null, false);
    }

    /**
     * 意见反馈
     */
    public void feedback() {
        MyApplication.UIHandler.post(() -> LoadingDialog.getInstance(context,"nihao").show());
        FeedbackApi feedbackApi = new FeedbackApi();
        FeedbackHttpRequest feedback = new FeedbackHttpRequest("这是意见反馈的内容", "12345678912", "shixiuwen");
        feedbackApi.setRequest(feedback);
        feedbackApi.post();
        feedbackApi.setOnJsonHttpResponseListener(new HttpApiBase.JsonHttpResponseListener<FeedbackHttpResponse>() {
            @Override
            public void onFailure(Exception e, String rawJsonString) {
                LoadingDialog.getInstance(context,"nihao").dismiss();
            }

            @Override
            public void onSuccessful(FeedbackHttpResponse o, String rawJsonString) {
                LoadingDialog.getInstance(context,"nihao").dismiss();
                CToast.makeCText(context, o.getStatus() + " " + o.getInfo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 测试Gson的转换
     */
    public void gsonTest() {
//        String strUser = "{\"name\":\"shixiuwen\",\"age\":2,\"sex\":\"男\"}";
//        String strUser2 = context.getString(R.string.use_json);
//        Gson userGson = new Gson();
//        User user = userGson.fromJson(strUser2, User.class);
//        Toast.makeText(context, user.getName() + " " + user.getAge() + " " + user.getSex(), Toast.LENGTH_SHORT).show();

    }

    /**
     * 跳转到测试二维码界面
     */
    public void toZXingActivity() {
        ((MainActivity) context).startActivity(context, ZXingActivity.class, null, false);
    }

    /**
     * 定位回调
     *
     * @param amapLocation /
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        iView.onEndLocation(amapLocation.getAddress());

        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
        amapLocation.getLatitude();//获取纬度
        amapLocation.getLongitude();//获取经度
        amapLocation.getAccuracy();//获取精度信息
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(amapLocation.getTime());
//        df.format(date);//定位时间
        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
        amapLocation.getCountry();//国家信息
        amapLocation.getProvince();//省信息
        amapLocation.getCity();//城市信息
        amapLocation.getDistrict();//城区信息
        amapLocation.getStreet();//街道信息
        amapLocation.getStreetNum();//街道门牌号信息
        amapLocation.getCityCode();//城市编码
        amapLocation.getAdCode();//地区编码
//        amapLocation.getAOIName();//获取当前定位点的AOI信息

        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
//        mLocationClient.onDestroy();//销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象。
    }

    @Override
    public void doBizWithPermissionRequest(int resultCode, String[] permissions) {
        super.doBizWithPermissionRequest(resultCode, permissions);
        if(PermissionUtils.isPermissionGranted(resultCode,
                PermissionUtils.WRITE_COARSE_LOCATION_REQUEST_CODE,
                permissions,
                Manifest.permission.ACCESS_FINE_LOCATION)) {  //该情况表示本次需要请求授权然后执行接下来的操作
            //初始化定位
            mLocationClient = new AMapLocationClient(context);
            //设置定位回调监听
            mLocationClient.setLocationListener(this);

            //初始化AMapLocationClientOption对象
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();

            //设置返回地址信息，默认为true
            mLocationOption.setNeedAddress(true);

            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.Hight_Accuracy);
            //设置定位间隔,单位毫秒,默认为2000ms
//      mLocationOption.setInterval(2000);

            //获取一次定位结果：
            //该方法默认为false。
            mLocationOption.setOnceLocation(true);

            //获取最近3s内精度最高的一次定位结果：
            //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
            mLocationOption.setOnceLocationLatest(true);

            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();
        }else if(resultCode == PermissionUtils.PERMISSION_DENIED){
            iView.onEndLocation("定位权限被拒绝，无法获取当前位置信息");
        }
    }
}
