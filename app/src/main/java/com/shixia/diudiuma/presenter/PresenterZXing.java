package com.shixia.diudiuma.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;

import com.shixia.diudiuma.activity.ZXingActivity;
import com.shixia.diudiuma.bean.Constants;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.iview.ZXingActivityIView;
import com.shixia.diudiuma.iview.base.BaseIView;
import com.shixia.diudiuma.presenter.base.BasePresenter;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import static android.app.Activity.RESULT_OK;

/**
 * Created by AmosShi on 2016/10/21.
 * Description:
 */

public class PresenterZXing extends BasePresenter {

    private ZXingActivity activity;
    private ZXingActivityIView iView;

    public PresenterZXing(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (ZXingActivity) context;
        this.iView = (ZXingActivityIView) iView;
    }

    /**
     * 开始扫面二维码图片解析为String
     */
    public void scanZXingImg() {
        PermissionUtils.initCurrentActivity(activity, this);
        PermissionUtils.doBizAfterRequestPermission(Manifest.permission.CAMERA, PermissionUtils.CAMERA);
    }

    /**
     * 将字符串decode为二维码图片
     */
    public void decodeToZXingImg(String s) {
        if (TextUtils.isEmpty(s)) {
            iView.showRemind("您还没有输入任何信息");
            return;
        }
        //通过文本生成二维码图片
        Bitmap image = EncodingUtils.createQRCode(s, 350, 350, null);
//        是否生成带logo的二维码？
//        mCheckBox.isChecked() ?
//        BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher) :
        iView.onShowDecodeResult(image);
    }

    @Override
    public void doBizWithPermissionRequest(int requestCode, String[] permissions) {
        super.doBizWithPermissionRequest(requestCode, permissions);
        //如果获取了相机授权，执行以下操作
        if (PermissionUtils.isPermissionGranted(requestCode, PermissionUtils.CAMERA, permissions, Manifest.permission.CAMERA)) {
            Intent intent = new Intent(activity.getApplicationContext(), com.xys.libzxing.zxing.activity.CaptureActivity.class);
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
