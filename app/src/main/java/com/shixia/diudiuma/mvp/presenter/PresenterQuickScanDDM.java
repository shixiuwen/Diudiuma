package com.shixia.diudiuma.mvp.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shixia.diudiuma.bean.Constants;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.mvp.activity.QuickScanGoodsActivity;
import com.shixia.diudiuma.mvp.iview.QuickScanDDMIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by AmosShi on 2016/11/2.
 * Description:
 */

public class PresenterQuickScanDDM extends BasePresenter {

    private QuickScanGoodsActivity activity;
    private QuickScanDDMIView iView;

    public PresenterQuickScanDDM(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (QuickScanGoodsActivity) context;
        this.iView = (QuickScanDDMIView) iView;
    }

    /**
     * 开始扫面二维码图片解析为String
     */
    public void scanZXingImg() {
        PermissionUtils.initCurrentActivity(activity, this);
        PermissionUtils.doBizAfterRequestPermission(Manifest.permission.CAMERA, PermissionUtils.CAMERA);
    }

    @Override
    public void doBizWithPermissionRequest(int requestCode, String[] permissions) {
        super.doBizWithPermissionRequest(requestCode, permissions);
        //如果获取了相机授权，执行以下操作
        if (PermissionUtils.isPermissionGranted(requestCode, PermissionUtils.CAMERA, permissions, Manifest.permission.CAMERA)) {
            Intent intent = new Intent(activity.getApplicationContext(), CaptureActivity.class);
            activity.startActivityForResult(intent, Constants.SCAN_ZXING_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.SCAN_ZXING_CODE) {
            //处理扫描结果（在界面上显示）
            if (resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getExtras();
                if(bundle!=null){
                    String scanResult = bundle.getString("result");
                    iView.onShowScanResult(scanResult);
                }
            }else {
                iView.onShowScanResult("解析失败");
            }
        }
    }
}
