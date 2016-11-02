package com.shixia.diudiuma.mvp.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.shixia.diudiuma.bean.Constants;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.common_utils.PermissionUtils;
import com.shixia.diudiuma.mvp.activity.QuickScanGoodsActivity;
import com.shixia.diudiuma.mvp.iview.QuickScanDDMIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * Created by AmosShi on 2016/11/2.
 * Description:
 */

public class PresenterQuickScanDDM extends BasePresenter {

    private QuickScanGoodsActivity activity;
    private QuickScanDDMIView iView;
    private LoserGoodsInfo loserGoodsInfo;

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
                if (bundle != null) {
                    String scanResult = bundle.getString("result");
                    findLoser(scanResult)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(loserInfo -> {
                                L.i("loserInfo02", loserInfo.getGoodsName() + " ");
                                iView.onShowScanResult(scanResult);
                                if (!TextUtils.isEmpty(loserInfo.getGoodsName())) {
                                    iView.onShowDetailLoserPage(loserInfo);
                                    iView.onShowDDMImg(EncodingUtils.createQRCode(scanResult, 350, 350, null));
                                    String goodsIconUrl = loserInfo.getGoodsIcon();
                                    if (!TextUtils.isEmpty(goodsIconUrl)) {
                                        iView.onShowGoodsPic(goodsIconUrl);
                                    }
                                } else {
                                    iView.onShowRemind("查找失败 +_+");
                                }
                            });
                }
            } else {
                iView.onShowScanResult("解析失败");
            }
        }
    }

    /**
     * 通过丢丢码查找物品，找到失主
     *
     * @param ddm
     * @return
     */
    private Observable<LoserGoodsInfo> findLoser(String ddm) {
        loserGoodsInfo = new LoserGoodsInfo();
        return Observable.create(new Observable.OnSubscribe<LoserGoodsInfo>() {
            @Override
            public void call(Subscriber<? super LoserGoodsInfo> subscriber) {
                BmobQuery<LoserGoodsInfo> query = new BmobQuery<LoserGoodsInfo>();
                query.addWhereEqualTo("ddm", ddm);
                query.findObjects(new FindListener<LoserGoodsInfo>() {
                    @Override
                    public void done(List<LoserGoodsInfo> list, BmobException e) {
                        if (e == null) {
                            loserGoodsInfo = list.get(0);
                            subscriber.onNext(loserGoodsInfo);
                            L.i("loserInfo00", list.get(0).getGoodsName() + " ");
                        } else {
                            L.i("error", e.getMessage());
                        }
                    }
                });
            }
        });
    }

    /**
     * 打电话给失主
     *
     * @param telephone 失主号码
     */
    public void callLoser(String telephone) {

    }
}
