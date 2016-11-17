package com.shixia.diudiuma.mvp.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickFindLoserIView;
import com.shixia.diudiuma.mvp.presenter.PresenterQuick;
import com.shixia.diudiuma.mvp.presenter.PresenterQuickFindLoser;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.EditItemView;

import java.io.File;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * Created by AmosShi on 2016/10/26.
 * Description:
 */

public class QuickFindLoserActivity extends BaseActivity implements QuickFindLoserIView {

    private PresenterQuickFindLoser presenter;

    private ScrollView scrollView;
    private ImageView imgLoseGoodsPic;
    private Button btnChangePic;
    private EditItemView etvName;
    private EditItemView etvDate;
    private EditItemView etvAddress;
    private EditItemView etvIsCard;
    private EditItemView etvIsCertificate;
    private EditItemView etvTel;
    private EditItemView etvWechat;
    private EditItemView etvQq;
    private ImageView imgGoodsTag;
    private EditText etDescribe;
    private Button btnSubmit;

    private Uri uri;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quick_find_loser_page);

        scrollView = (ScrollView) findViewById(R.id.sv_scroll_view);

        imgLoseGoodsPic = (ImageView) findViewById(R.id.img_lose_goods_pic);
        btnChangePic = (Button) findViewById(R.id.btn_change_pic);
        etvName = (EditItemView) findViewById(R.id.etv_name);
        etvDate = (EditItemView) findViewById(R.id.etv_date);
        etvAddress = (EditItemView) findViewById(R.id.etv_address);
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

        //以下代码解决ScrollView中EditText导致自动滚动问题
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener((v, event) -> {
            v.requestFocusFromTouch();
            return false;
        });

        btnChangePic.setOnClickListener(v -> presenter.selectPic());
        imgLoseGoodsPic.setOnClickListener(v -> presenter.toPreviewPic());

        etvName.setOnEditItemClickListener(() -> presenter.toEditInfoPage(PresenterQuick.EDIT_GOODS_NAME_REQUEST_CODE, etvName.getTvItemValue(),
                "修改品名", false, "您拾到的物品名称", "物品名称请尽量简洁明了，如钱包，钥匙", R.drawable.img_hall_02));
        etvDate.setOnEditItemClickListener(() -> presenter.toEditInfoPage(PresenterQuick.EDIT_GOODS_DATE_REQUEST_CODE, etvDate.getTvItemValue(),
                "拾到日期", false, "您拾到的物品丢失日期", "不是今天的日期而是您什么时候拾到了物品的日期，请务必注意哦~", R.drawable.img_hall_03));
        etvAddress.setOnEditItemClickListener(() -> presenter.toEditInfoPage(PresenterQuick.EDIT_GOODS_ADDRESS_REQUEST_CODE, etvAddress.getTvItemValue(),
                "拾到地点", false, "您拾到的物品地点", "请输入您拾到物品的地点，如果不知道，请精确到区", R.drawable.img_hall_04));
//        etvIsCard.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_IS_CARD_REQUEST_CODE, etvIsCard.getTvItemValue(),
//                "是否卡类", false, "拾到物品是否属于卡类", "如果选择了是，我们将会要求您输入其他额外选项，帮助您增加找到失主的几率~", R.drawable.img_hall_04));
//        etvIsCertificate.setOnEditItemClickListener(() -> presenter.toEditInfoPage(EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE, etvIsCertificate.getTvItemValue(),
//                "是否证件", false, "是否证件类", "如果选择了是，我们将会要求您输入其他额外选项，帮助您增加找到失主的几率~", R.drawable.img_hall_03));

        etvIsCard.setOnEditItemClickListener(() -> {
            presenter.setLoserGoodsInfo(PresenterQuick.EDIT_GOODS_IS_CARD_REQUEST_CODE, etvIsCard.getTvItemValue());
            presenter.changeGoodsType(PresenterQuick.EDIT_GOODS_IS_CARD_REQUEST_CODE, etvIsCard.getTvItemValue());
        });
        etvIsCertificate.setOnEditItemClickListener(() -> {
            presenter.setLoserGoodsInfo(PresenterQuick.EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE, etvIsCertificate.getTvItemValue());
            presenter.changeGoodsType(PresenterQuick.EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE, etvIsCertificate.getTvItemValue());
        });

        etvTel.setOnEditItemClickListener(() -> presenter.toEditInfoPage(PresenterQuick.EDIT_GOODS_TEL_REQUEST_CODE, etvTel.getTvItemValue(),
                "我的手机号", false, "您的手机号", "输入手机号能够使失主直接联系您", R.drawable.img_hall_03));
        etvWechat.setOnEditItemClickListener(() -> presenter.toEditInfoPage(PresenterQuick.EDIT_GOODS_WECHAT_REQUEST_CODE, etvWechat.getTvItemValue(),
                "我的微信号", false, "您的微信号", "微信号可使失主更加方便的联系您，和您沟通~", R.drawable.img_hall_03));
        etvQq.setOnEditItemClickListener(() -> presenter.toEditInfoPage(PresenterQuick.EDIT_GOODS_QQ_REQUEST_CODE, etvQq.getTvItemValue(), "QQ号", false, "您的QQ号", "QQ号可使失主更加方便的联系您，和您沟通~", R.drawable.img_hall_03));

        btnSubmit.setOnClickListener(v -> {
            presenter.setLoserGoodsInfo(PresenterQuick.EDIT_GOODS_DESCRIPTION, etDescribe.getText().toString());
            presenter.submitData();
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        if (presenter == null) {
            presenter = new PresenterQuickFindLoser(this, this);
        }
        return presenter;
    }

    @Override
    public void onChangeValueAfterEdit(int requestCode, String value) {
        if (requestCode == PresenterQuick.EDIT_GOODS_NAME_REQUEST_CODE) {
            etvName.setTvItemValue(value);
        } else if (requestCode == PresenterQuick.EDIT_GOODS_DATE_REQUEST_CODE) {
            etvDate.setTvItemValue(value);
        } else if (requestCode == PresenterQuick.EDIT_GOODS_ADDRESS_REQUEST_CODE) {
            etvAddress.setTvItemValue(value);
        } else if (requestCode == PresenterQuick.EDIT_GOODS_IS_CARD_REQUEST_CODE) {
            etvIsCard.setTvItemValue(value);
        } else if (requestCode == PresenterQuick.EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE) {
            etvIsCertificate.setTvItemValue(value);
        } else if (requestCode == PresenterQuick.EDIT_GOODS_TEL_REQUEST_CODE) {
            etvTel.setTvItemValue(value);
        } else if (requestCode == PresenterQuick.EDIT_GOODS_WECHAT_REQUEST_CODE) {
            etvWechat.setTvItemValue(value);
        } else if (requestCode == PresenterQuick.EDIT_GOODS_QQ_REQUEST_CODE) {
            etvQq.setTvItemValue(value);
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

            presenter.setLoserGoodsInfo(PresenterQuick.EDIT_GOODS_ICON, photos.get(0));

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
    public void onNewTagsAdded(String tags) {

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
}
