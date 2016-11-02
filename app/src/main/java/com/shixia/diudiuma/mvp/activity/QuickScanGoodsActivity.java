package com.shixia.diudiuma.mvp.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shixia.diudiuma.MyApplication;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickScanDDMIView;
import com.shixia.diudiuma.mvp.presenter.PresenterQuickScanDDM;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.EditItemView;

/**
 * Created by AmosShi on 2016/10/26.
 * Description:扫码
 */

public class QuickScanGoodsActivity extends BaseActivity implements QuickScanDDMIView {

    private LoserGoodsInfo loserGoodsInfo;

    private ImageView imgLoseGoodsPic;
    private ImageView imgDDM;
    private Button btnChangePic;
    private Button btnDecodeDDM;
    private EditItemView etvName;
    private EditItemView etvDate;
    private EditItemView etvAddress;
    private EditItemView etvReward;
    private EditItemView etvDDM;
    private EditItemView etvIsCard;
    private EditItemView etvIsCertificate;
    private EditItemView etvTel;
    private EditItemView etvWechat;
    private EditItemView etvQq;
    private ImageView imgGoodsTag;
    private TextView tvGoodsTag;
    private TextView etDescribe;
    private Button btnConnectLoser;

    public static final int EDIT_GOODS_NAME_REQUEST_CODE = 0x001;
    //    public static final int EDIT_GOODS_DATE_REQUEST_CODE = 0x002;
    public static final int EDIT_GOODS_ADDRESS_REQUEST_CODE = 0x003;
    public static final int EDIT_GOODS_REWARD_REQUEST_CODE = 0x004;
    public static final int EDIT_GOODS_IS_CARD_REQUEST_CODE = 0x005;
    public static final int EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE = 0x006;
    public static final int EDIT_GOODS_TEL_REQUEST_CODE = 0x007;
    public static final int EDIT_GOODS_WECHAT_REQUEST_CODE = 0x008;
    public static final int EDIT_GOODS_QQ_REQUEST_CODE = 0x009;
    private Uri uri;
    private String strDDM;

    private String telephone;

    private PresenterQuickScanDDM presenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_scan_ddm_page);

        imgLoseGoodsPic = (ImageView) findViewById(R.id.img_lose_goods_pic);
        imgDDM = (ImageView) findViewById(R.id.img_my_goods_ddm);
        etvName = (EditItemView) findViewById(R.id.etv_name);
        etvDate = (EditItemView) findViewById(R.id.etv_date);
        etvAddress = (EditItemView) findViewById(R.id.etv_address);
        etvReward = (EditItemView) findViewById(R.id.etv_money);
        etvDDM = (EditItemView) findViewById(R.id.etv_ddm);
        etvIsCard = (EditItemView) findViewById(R.id.etv_is_card);
        etvIsCertificate = (EditItemView) findViewById(R.id.etv_is_certificate);
        etvTel = (EditItemView) findViewById(R.id.etv_tel);
        etvWechat = (EditItemView) findViewById(R.id.etv_wechat);
        etvQq = (EditItemView) findViewById(R.id.etv_qq);
        imgGoodsTag = (ImageView) findViewById(R.id.img_goods_tag);
        tvGoodsTag = (TextView)findViewById(R.id.tv_goods_tag);
        etDescribe = (TextView) findViewById(R.id.et_describe);
        btnConnectLoser = (Button) findViewById(R.id.btn_connect_loser);
    }

    @Override
    protected void initListener() {
        btnConnectLoser.setOnClickListener(v -> presenter.callLoser(telephone));
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterQuickScanDDM(this, this);
        return presenter;
    }

    @Override
    protected void afterActivityOnCreate() {
        super.afterActivityOnCreate();
        presenter.scanZXingImg();
    }

    @Override
    public void onShowRemind(String s) {
        CToast.makeCText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowScanResult(String s) {
        CToast.makeCText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowDetailLoserPage(LoserGoodsInfo loserGoodsInfo) {
        etvName.setTvItemValue(loserGoodsInfo.getGoodsName());
        etvDate.setTvItemValue(loserGoodsInfo.getLoseDate());
        etvAddress.setTvItemValue(loserGoodsInfo.getLoseAddress());
        etvReward.setTvItemValue(loserGoodsInfo.getReward() + "");
        etvDDM.setTvItemValue(loserGoodsInfo.getDdm());
        boolean isCard = loserGoodsInfo.getCard();
        etvIsCard.setTvItemValue(isCard ? "是" : "否");
        Boolean isCertification = loserGoodsInfo.getCredit();
        etvIsCertificate.setTvItemValue(isCertification ? "是" : "否");
        etvTel.setTvItemValue(loserGoodsInfo.getTel());
        etvWechat.setTvItemValue(loserGoodsInfo.getWechat());
        etvQq.setTvItemValue(loserGoodsInfo.getQq());
        etDescribe.setText(loserGoodsInfo.getDiscribe());
        tvGoodsTag.setText(loserGoodsInfo.getGoodsTag());

        if (TextUtils.isEmpty(loserGoodsInfo.getTel())) {
            btnConnectLoser.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onShowDDMImg(Bitmap qrCode) {
        imgDDM.setImageBitmap(qrCode);
    }

    @Override
    public void onShowGoodsPic(String url) {
        L.i("pic url",url);
        MyApplication.UIHandler.post(()-> Glide.with(QuickScanGoodsActivity.this)
                .load(url)
                .centerCrop()
                .thumbnail(0.1f)
                .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
                .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
                .into(imgLoseGoodsPic));
    }
}
