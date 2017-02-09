package com.shixia.diudiuma.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jlcf.lib_adapter.base.listener.BaseViewHolder;
import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bean.Constants;
import com.shixia.diudiuma.bean.MultiItemEntity;
import com.shixia.diudiuma.bmob.bean.DDMGoodsLever0Item;
import com.shixia.diudiuma.bmob.bean.DDMGoodsLever1Item;
import com.shixia.diudiuma.mvp.activity.PicturePreviewActivity;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    private Context context;
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
    public ExpandableItemAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        this.context = context;
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
                        .create((Observable.OnSubscribe<String>) subscriber -> {
                            if (TextUtils.isEmpty(pic)) {
                                subscriber.onNext(fileUrl);
                            }else {
                                subscriber.onNext(pic);
                            }
                        })
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(bitmap -> {
                            holder.setText(R.id.tv_goods_name, lv0.getDdmGoodsName())
                                    .setText(R.id.tv_goods_register_data_title, lv0.getRegisterDate().toString())
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
                            holder.getView(R.id.img_goods_icon).setOnClickListener(v -> {
                                final Intent intent = new Intent(context, PicturePreviewActivity.class);
                                intent.putExtra("bitmapUrl",bitmap);
                                ActivityTransitionLauncher.with((BaseActivity)context).from(v).launch(intent);
                            });
                            //设置缩略图
                            Glide.with(context).load(bitmap)
                                    .dontAnimate()
                                    .thumbnail(0.1f)
                                    .into((ImageView) holder.getView(R.id.img_goods_icon));
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

}
