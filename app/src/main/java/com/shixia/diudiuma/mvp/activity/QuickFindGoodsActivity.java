package com.shixia.diudiuma.mvp.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickAddGoodsIView;
import com.shixia.diudiuma.mvp.presenter.PresenterQuickFindGoods;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.EditItemView;

/**
 * Created by AmosShi on 2016/10/26.
 * Description:
 */

public class QuickFindGoodsActivity extends BaseActivity implements QuickAddGoodsIView {

    private PresenterQuickFindGoods presenter;

    private ImageView imgLoseGoodsPic;
    private Button btnChangePic;
    private EditItemView etvName;
    private EditItemView etvDate;
    private EditItemView etvAddress;
    private EditItemView etvMoney;
    private EditItemView etvIsCard;
    private EditItemView etvIsCertificate;
    private EditItemView etvTel;
    private EditItemView etvWechat;
    private EditItemView etvQq;
    private ImageView imgGoodsTag;
    private EditText etDescribe;
    private Button btnSubmit;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_find_goods_page);

        imgLoseGoodsPic = (ImageView) findViewById(R.id.img_lose_goods_pic);
        btnChangePic = (Button) findViewById(R.id.btn_change_pic);
        etvName = (EditItemView) findViewById(R.id.etv_name);
        etvDate = (EditItemView) findViewById(R.id.etv_date);
        etvAddress = (EditItemView) findViewById(R.id.etv_address);
        etvMoney = (EditItemView) findViewById(R.id.etv_money);
        etvIsCard = (EditItemView) findViewById(R.id.etv_is_card);
        etvIsCertificate = (EditItemView) findViewById(R.id.etv_is_certificate);
        etvTel = (EditItemView) findViewById(R.id.etv_tel);
        etvWechat = (EditItemView) findViewById(R.id.etv_wechat);
        etvQq = (EditItemView) findViewById(R.id.etv_qq);
        imgGoodsTag = (ImageView) findViewById(R.id.img_goods_tag);
        etDescribe = (EditText) findViewById(R.id.et_describe);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

    }

    @Override
    protected void initListener() {
        etvName.setOnEditItemClickListener(() -> {
            presenter.toEditGoodsInfo();
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterQuickFindGoods(this,this);
        return presenter;
    }
}
