package com.shixia.diudiuma.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jlcf.lib_adapter.base.BaseQuickAdapter;
import com.jlcf.lib_adapter.base.listener.BaseViewHolder;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bean.Constants;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AmosShi on 2016/11/1.
 * Description:失物大厅对应的adapter
 */

public class HallGoodsAdapter extends BaseQuickAdapter<LoserGoodsInfo> {

    //默认图片地址
    private String fileUrl = Constants.defPic;

    private Context context;

    public HallGoodsAdapter(int layoutResId, List<LoserGoodsInfo> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, LoserGoodsInfo item) {
        String goodsIcon = item.getGoodsIcon();

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (TextUtils.isEmpty(goodsIcon)) {
                    subscriber.onNext(fileUrl);
                } else {
                    subscriber.onNext(goodsIcon);
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
//                .map(this::returnBitmap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> {
                    helper.setText(R.id.tv_goods_name, item.getGoodsName())
//                            .setImageBitmap(R.id.img_goods_icon, bitmap)
                            .setText(R.id.tv_goods_lose_address, item.getLoseAddress())
                            .setText(R.id.tv_goods_tag, item.getGoodsTag());
                    ImageView imgGoodsIcon = helper.getView(R.id.img_goods_icon);
                    Glide.with(context)
                            .load(bitmap)
                            .dontAnimate()
                            .thumbnail(0.1f)
                            .into(imgGoodsIcon);
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
