package com.shixia.diudiuma.mvp.activity;

import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.AddGoodsIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.EditItemView;

/**
 * Created by AmosShi on 2016/10/26.
 * Description:
 */

public class QuickAddGoodsActivity extends BaseActivity implements AddGoodsIView {
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_add_goods_page);
        EditItemView view = (EditItemView) findViewById(R.id.etv_name);
        view.setOnEditItemClickListener(() -> {
            view.setTvItemKey("Amos");
            view.setTvItemValue("Ciel");
            CToast.makeCText(this, view.getTvItemKey() + " " + view.getTvItemValue(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
