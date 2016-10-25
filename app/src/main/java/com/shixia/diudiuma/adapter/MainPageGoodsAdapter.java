package com.shixia.diudiuma.adapter;

import com.jlcf.lib_adapter.base.BaseQuickAdapter;
import com.jlcf.lib_adapter.base.BaseViewHolder;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bean.MyGoods;

import java.util.List;

/**
 * Created by AmosShi on 2016/10/25.
 * Description:
 */

public class MainPageGoodsAdapter extends BaseQuickAdapter<MyGoods> {

    public MainPageGoodsAdapter(int layoutResId, List<MyGoods> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyGoods item) {
        helper.setText(R.id.tv_goods_name,item.getGoodsName())
                .setText(R.id.tv_goods_register_data,item.getGoodsRegisterData())
                .setText(R.id.tv_goods_tag,item.getGoodsTag().toString());
    }
}
