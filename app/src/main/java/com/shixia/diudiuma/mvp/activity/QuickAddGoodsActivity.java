package com.shixia.diudiuma.mvp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickAddGoodsIView;
import com.shixia.diudiuma.mvp.presenter.PresenterQuickAddGoods;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.EditItemView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.bmob.v3.BmobUser;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * Created by AmosShi on 2016/10/26.
 * <p>
 * Description:找回物品
 */

public class QuickAddGoodsActivity extends BaseActivity implements QuickAddGoodsIView {

    private PresenterQuickAddGoods presenter;

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
    private EditText etDescribe;
    private Button btnSubmit;

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

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_add_goods_page);

        imgLoseGoodsPic = (ImageView) findViewById(R.id.img_lose_goods_pic);
        imgDDM = (ImageView) findViewById(R.id.img_my_goods_ddm);
        btnChangePic = (Button) findViewById(R.id.btn_change_pic);
        btnDecodeDDM = (Button) findViewById(R.id.btn_decode_ddm);
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
        etDescribe = (EditText) findViewById(R.id.et_describe);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        // TODO: 2016/10/28 点击加号先判断是否登录（判断是否需要，如果无需登录可以不登录）
        loserGoodsInfo = new LoserGoodsInfo();
        //如果已经登录过了，添加查询条件用户名，以后可以通过用户名查询发布信息
        if (BmobUser.getCurrentUser() != null) {
            loserGoodsInfo.setPublisherName(BmobUser.getCurrentUser().getUsername());   //未登录的时候不可设置
        }
        loserGoodsInfo.setType(3);      //该界面发布的为注册物品
        loserGoodsInfo.setGoodsTag("黑色#两根#宝马");
        loserGoodsInfo.setDiscribe("该物品对本人非常重要，请务必归还，非常感谢 ^_^ ");

        //以下为部分默认项：1.注册日期自动生成 2.丢丢码自动生成
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String strDate = simpleDateFormat.format(date);
        etvDate.setTvItemValue(strDate);

        Date dataDDM = new Date();
        SimpleDateFormat simpleDateFormatDDM = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        strDDM = "ddm" + "_" + simpleDateFormatDDM.format(dataDDM) + "_" + BmobUser.getCurrentUser().getUsername();
        etvDDM.setTvItemValue(strDDM);

    }

    @Override
    protected void initListener() {

        btnChangePic.setOnClickListener(v -> presenter.selectPic());
        btnDecodeDDM.setOnClickListener(v -> presenter.decodeDDM(strDDM));
        // TODO: 2016/11/2 预览二维码
//        imgLoseGoodsPic.setOnClickListener(v -> presenter.toPreviewPic());
        imgDDM.setOnClickListener(v -> presenter.toPreviewPic());

        etvName.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_NAME_REQUEST_CODE, etvName.getTvItemValue(),
                "修改品名", false, "您的物品名称", "物品名称请尽量简洁明了，如钱包，钥匙", R.drawable.img_hall_02));
//        etvDate.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_DATE_REQUEST_CODE, etvDate.getTvItemValue(),
//                "丢失日期", false, "您的物品注册日期", "不是今天的日期而是您什么时候遗失了您的物品日期，请务必注意哦~", R.drawable.img_hall_03));
        etvDate.setOnEditItemClickListener(() -> presenter.showRemind("该设置项不可编辑"));
        etvAddress.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_ADDRESS_REQUEST_CODE, etvAddress.getTvItemValue(),
                "丢失地点", false, "您的物品注册地点", "请输入您的物品注册地点，该地点可在将来您的物品丢失的时候供拾到者提供参考", R.drawable.img_hall_04));
        etvReward.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_REWARD_REQUEST_CODE, etvReward.getTvItemValue(),
                "悬赏金额", false, "您发布的悬赏金额", "高额的悬赏金能增加您找回宝贝的几率哦~", R.drawable.img_hall_03));
        etvIsCard.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_IS_CARD_REQUEST_CODE, etvIsCard.getTvItemValue(),
                "是否卡类", false, "丢失物品是否属于卡类", "如果选择了是，我们将会要求您输入其他额外选项，帮助您增加宝贝找回的几率~", R.drawable.img_hall_04));
        etvIsCertificate.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE, etvIsCertificate.getTvItemValue(),
                "是否证件", false, "是否证件类", "如果选择了是，我们将会要求您输入其他额外选项，帮助您增加宝贝找回的几率~", R.drawable.img_hall_03));
        etvTel.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_TEL_REQUEST_CODE, etvTel.getTvItemValue(),
                "手机号", false, "您的手机号", "输入手机号能够使找到您宝贝的人直接联系您", R.drawable.img_hall_03));
        etvWechat.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_WECHAT_REQUEST_CODE, etvWechat.getTvItemValue(),
                "微信号", false, "您的微信号", "微信号可使找到您宝贝的人更加方便的联系您，和您沟通~", R.drawable.img_hall_03));
        etvQq.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_QQ_REQUEST_CODE, etvQq.getTvItemValue(), "QQ号", false, "您的QQ号", "QQ号可使找到您宝贝的人更加方便的联系您，和您沟通~", R.drawable.img_hall_03));

        btnSubmit.setOnClickListener(v -> {
            loserGoodsInfo.setDiscribe(etDescribe.getText().toString());
            presenter.submitData(loserGoodsInfo);
        });

    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterQuickAddGoods(this, this);
        return presenter;
    }

    @Override
    public void onChangeValueAfterEdit(int requestCode, String value) {
        if (requestCode == EDIT_GOODS_NAME_REQUEST_CODE) {
            etvName.setTvItemValue(value);
            loserGoodsInfo.setGoodsName(value);
        } /*else if (requestCode == EDIT_GOODS_DATE_REQUEST_CODE) {
            etvDate.setTvItemValue(value);
            loserGoodsInfo.setLoseDate(value);
        }*/ else if (requestCode == EDIT_GOODS_ADDRESS_REQUEST_CODE) {
            etvAddress.setTvItemValue(value);
            loserGoodsInfo.setLoseAddress(value);
        } else if (requestCode == EDIT_GOODS_REWARD_REQUEST_CODE) {
            etvReward.setTvItemValue(value);
            loserGoodsInfo.setReward(Float.valueOf(value));
        } else if (requestCode == EDIT_GOODS_IS_CARD_REQUEST_CODE) {
            etvIsCard.setTvItemValue(value);
            loserGoodsInfo.setCard(true);
        } else if (requestCode == EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE) {
            etvIsCertificate.setTvItemValue(value);
            loserGoodsInfo.setCredit(false);
        } else if (requestCode == EDIT_GOODS_TEL_REQUEST_CODE) {
            etvTel.setTvItemValue(value);
            loserGoodsInfo.setTel(value);
        } else if (requestCode == EDIT_GOODS_WECHAT_REQUEST_CODE) {
            etvWechat.setTvItemValue(value);
            loserGoodsInfo.setWechat(value);
        } else if (requestCode == EDIT_GOODS_QQ_REQUEST_CODE) {
            etvQq.setTvItemValue(value);
            loserGoodsInfo.setQq(value);
        }
    }

    /**
     * 将选中的图片进行展示
     *
     * @param requestCode //
     * @param resultCode  //
     * @param data        //
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }

            if (photos == null) {
                return;
            }

            uri = Uri.fromFile(new File(photos.get(0)));

            loserGoodsInfo.setGoodsIcon(photos.get(0));

            Glide.with(this)
                    .load(uri)
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
                    .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
                    .into(imgLoseGoodsPic);
        }
    }

    @Override
    public void onShowRemind(String content) {
        CToast.makeCText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowPreviewPop() {
        PopupWindow popupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.view_pop_pic_preview_view, null);
        ImageView imgPreviewPic = (ImageView) view.findViewById(R.id.img_preview_pic);
        Glide.with(this)
                .load(uri)
                .centerCrop()
                .thumbnail(0.1f)
                .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
                .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
                .into(imgPreviewPic);
        popupWindow.setContentView(view);
        popupWindow.setAnimationStyle(R.style.login_pop_anim);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(imgLoseGoodsPic.getRootView(), Gravity.CENTER, 0, 0);
    }

    @Override
    public void onShowDecodeResult(Bitmap image) {
        imgDDM.setImageBitmap(image);
    }

    @Override
    public void onFinish() {
        finish();
    }
}
