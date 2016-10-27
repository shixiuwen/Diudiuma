package com.shixia.diudiuma.mvp.activity;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickIView;
import com.shixia.diudiuma.mvp.presenter.PresenterQuickAddGoods;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.EditItemView;

/**
 * Created by AmosShi on 2016/10/26.
 * Description:
 */

public class QuickActivity extends BaseActivity implements QuickIView {

    private PresenterQuickAddGoods presenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_add_goods_page);
        EditItemView view = (EditItemView) findViewById(R.id.etv_name);
        view.setOnEditItemClickListener(() -> {
//            view.setTvItemKey("Amos");
//            view.setTvItemValue("Ciel");
//            CToast.makeCText(this, view.getTvItemKey() + " " + view.getTvItemValue(), Toast.LENGTH_SHORT).show();
            presenter.toEditInfoPage("编辑",true,"这是Title提示信息","这是Content提示信息",R.drawable.img_hall_02);
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterQuickAddGoods(this,this);
        return presenter;
    }
}
