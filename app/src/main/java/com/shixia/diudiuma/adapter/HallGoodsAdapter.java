package com.shixia.diudiuma.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jlcf.lib_adapter.base.BaseQuickAdapter;
import com.jlcf.lib_adapter.base.listener.BaseViewHolder;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AmosShi on 2016/11/1.
 * Description:
 */

public class HallGoodsAdapter extends BaseQuickAdapter<LoserGoodsInfo> {

    //默认图片地址
    private String fileUrl = "http://bmob-cdn-7143.b0.upaiyun.com/2016/11/01/cf9f12004007632b8046fd60b661516b.png";

    /*private ImageView imgGoodsIcon;
    private TextView tvGoodsName;
    private TextView tvGoodsLoseAddress;
    private TextView tvGoodsTag;

    imgGoodsIcon = (ImageView) findViewById(R.id.img_goods_icon);
    tvGoodsName = (TextView) findViewById(R.id.tv_goods_name);
    tvGoodsLoseAddress = (TextView) findViewById(R.id.tv_goods_lose_address);
    tvGoodsTag = (TextView) findViewById(R.id.tv_goods_tag);*/


    public HallGoodsAdapter(int layoutResId, List<LoserGoodsInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoserGoodsInfo item) {
        BmobFile goodsIcon = item.getGoodsIcon();
        if (goodsIcon != null) {
            fileUrl = goodsIcon.getFileUrl();
        }
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(fileUrl);
                    }
                })
                .map(this::returnBitmap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> {
                    helper.setImageBitmap(R.id.img_goods_icon, bitmap)
                            .setText(R.id.tv_goods_name, item.getGoodsName())
                            .setText(R.id.tv_goods_lose_address, item.getLoseAddress())
                            .setText(R.id.tv_goods_tag, item.getGoodsTag());
                });
    }


    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }
}
