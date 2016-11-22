package com.shixia.diudiuma.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jlcf.lib_adapter.base.BaseQuickAdapter;
import com.jlcf.lib_adapter.base.listener.BaseViewHolder;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bean.Constants;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.view.ItemTagView;

import java.util.List;

import rx.Observable;
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

        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            if (TextUtils.isEmpty(goodsIcon)) {
                subscriber.onNext(fileUrl);
            } else {
                subscriber.onNext(goodsIcon);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> {
                    helper.setText(R.id.tv_goods_name, item.getGoodsName())
                            .setText(R.id.tv_goods_lose_address, item.getLoseAddress())
                            .setText(R.id.tv_goods_tag, item.getGoodsTag());
                    ItemTagView itvTagView = helper.getView(R.id.itv_item_tag);     //设置标签颜色
                    Integer type = item.getType();

                    if (type != null && type == 1) {    //寻物启示
                        itvTagView.setVisibility(View.VISIBLE);
                        itvTagView.setTagColor(Color.argb(255, 78, 99, 212), Color.argb(255, 0, 0, 0))
                                .setTagString("寻物启事");
                    } else if (type != null) { //失物招领
                        itvTagView.setVisibility(View.VISIBLE);
                        itvTagView.setTagColor(Color.argb(255, 250, 91, 145), Color.argb(255, 0, 0, 0))
                                .setTagString("失物招领");
                    } else {
                        itvTagView.setVisibility(View.GONE);
                    }
                    ImageView imgGoodsIcon = helper.getView(R.id.img_goods_icon);
                    Glide.with(context)
                            .load(bitmap)
                            .dontAnimate()
                            .thumbnail(0.1f)
                            .into(imgGoodsIcon);
                });
    }
}
