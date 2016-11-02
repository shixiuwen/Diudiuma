package com.shixia.diudiuma.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.jlcf.lib_adapter.base.listener.BaseViewHolder;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bean.Constants;
import com.shixia.diudiuma.bean.MultiItemEntity;
import com.shixia.diudiuma.bmob.bean.DDMGoodsLever0Item;
import com.shixia.diudiuma.bmob.bean.DDMGoodsLever1Item;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

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
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    //默认图片地址
    private String fileUrl = Constants.defPic;

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.recy_goods_item);
        addItemType(TYPE_LEVEL_1, R.layout.view_goods_detail_item);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final DDMGoodsLever0Item lv0 = (DDMGoodsLever0Item) item;
                String pic = lv0.getPic();
                Observable
                        .create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                if (TextUtils.isEmpty(pic)) {
                                    subscriber.onNext(fileUrl);
                                }else {
                                    subscriber.onNext(pic);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.newThread())
                        .map(this::returnBitmap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(bitmap -> {
                            holder.setText(R.id.tv_goods_name, lv0.getDdmGoodsName())
                                    .setText(R.id.tv_goods_register_data_title, lv0.getRegisterDate().toString())
                                    .setImageBitmap(R.id.img_goods_icon, bitmap)
                                    .setText(R.id.tv_goods_tag, lv0.getDdmGoodsTag());
                            holder.itemView.setOnClickListener(v -> {
                                int pos = holder.getAdapterPosition();
                                Log.d(TAG, "Level 0 item pos: " + pos);
                                if (lv0.isExpanded()) {
                                    collapse(pos);
                                } else {
                                    expand(pos);
                                }
                            });
                        });
                break;
            case TYPE_LEVEL_1:
                final DDMGoodsLever1Item lv1 = (DDMGoodsLever1Item) item;
                Bitmap image = EncodingUtils.createQRCode(lv1.getDdmCode(), 350, 350, null);
                holder.setText(R.id.tv_address, lv1.getDdmGoodsAddress())
                        .setText(R.id.tv_diudiu_code, lv1.getDdmCode())
                        .setText(R.id.tv_is_card, lv1.getCard() ? "是" : "否")
                        .setText(R.id.tv_is_certification, lv1.getCertificate() ? "是" : "否")
                        .setText(R.id.tv_tel, lv1.getTel())
                        .setText(R.id.tv_wechat, lv1.getWechat())
                        .setText(R.id.tv_qq, lv1.getQq())
                        .setText(R.id.tv_descrip, lv1.getDescribe())
                        .setImageBitmap(R.id.img_goods_icon,image );
                holder.itemView.setOnClickListener(v -> {
                    int pos = holder.getAdapterPosition();
                    Log.d(TAG, "Level 1 item pos: " + pos);
                });
                break;
        }
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
