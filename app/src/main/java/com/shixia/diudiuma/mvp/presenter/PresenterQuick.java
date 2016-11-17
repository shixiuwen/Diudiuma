package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.mvp.activity.EditInfoPageActivity;
import com.shixia.diudiuma.mvp.activity.EditTagsPageActivity;
import com.shixia.diudiuma.mvp.activity.QuickAddGoodsActivity;
import com.shixia.diudiuma.mvp.activity.QuickFindGoodsActivity;
import com.shixia.diudiuma.mvp.activity.QuickFindLoserActivity;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.QuickIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import cn.bmob.v3.BmobUser;

/**
 * Created by AmosShi on 2016/10/27.
 * Description:
 */

public class PresenterQuick extends BasePresenter {

    public static final int EDIT_GOODS_NAME_REQUEST_CODE = 0x001;
    public static final int EDIT_GOODS_DATE_REQUEST_CODE = 0x002;
    public static final int EDIT_GOODS_ADDRESS_REQUEST_CODE = 0x003;
    public static final int EDIT_GOODS_REWARD_REQUEST_CODE = 0x004;
    public static final int EDIT_GOODS_IS_CARD_REQUEST_CODE = 0x005;
    public static final int EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE = 0x006;
    public static final int EDIT_GOODS_TEL_REQUEST_CODE = 0x007;
    public static final int EDIT_GOODS_WECHAT_REQUEST_CODE = 0x008;
    public static final int EDIT_GOODS_QQ_REQUEST_CODE = 0x009;
    public static final int EDIT_GOODS_ICON = 0x010;
    public static final int EDIT_GOODS_DESCRIPTION = 0x011;
    public static final int EDIT_GOODS_DDM = 0x012;
    public static final int EDIT_GOODS_TAG = 0x013;

    private BaseActivity activity;
    private QuickIView iView;

    private LoserGoodsInfo loserGoodsInfo;

    public PresenterQuick(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (BaseActivity) context;
        this.iView = (QuickIView) iView;
        initGoodsInfo(context);
    }

    private void initGoodsInfo(Context context) {
        loserGoodsInfo = new LoserGoodsInfo();
        //如果已经登录过了，添加查询条件用户名，以后可以通过用户名查询发布信息
        if (BmobUser.getCurrentUser() != null) {
            loserGoodsInfo.setPublisherName(BmobUser.getCurrentUser().getUsername());   //未登录的时候不可设置
        }
        if (context instanceof QuickFindGoodsActivity) {
            loserGoodsInfo.setType(1);      //该界面发布的为寻物启事
        } else if (context instanceof QuickFindLoserActivity) {
            loserGoodsInfo.setType(2);      //该界面发布的为失物招领
        } else if (context instanceof QuickAddGoodsActivity) {
            loserGoodsInfo.setType(3);      //该界面发布的注册物品
        }
        loserGoodsInfo.setCard(false);
        loserGoodsInfo.setCredit(false);
        loserGoodsInfo.setGoodsTag("这是#物品#标签");
        loserGoodsInfo.setDiscribe("望失主联系本人 ^_^ ");
    }


    /**
     * 跳转到编辑条目信息页面
     *
     * @param pageTitle        编辑页面Title
     * @param isSureBtnVisible 编辑页面Title的按钮是否可见
     * @param titleRemind      编辑页面的输入框Title提示信息
     * @param contentRemind    编辑页面的输入框内容提示信息
     * @param iconResourceId   编辑页面的输入框icon
     */
    public void toEditInfoPage(int requestCode, String defValue, String pageTitle, boolean isSureBtnVisible, String titleRemind, String contentRemind, int iconResourceId) {
        Bundle bundle = new Bundle();
        bundle.putString("pageTitle", pageTitle);
        bundle.putString("defValue", defValue);
        bundle.putBoolean("isSureBtnVisible", isSureBtnVisible);
        bundle.putString("titleRemind", titleRemind);
        bundle.putString("contentRemind", contentRemind);
        bundle.putInt("iconResourceId", iconResourceId);

        activity.startActivityForResult(activity, EditInfoPageActivity.class, bundle, requestCode, false);
    }

    /**
     * 进入修改标签页面
     *
     * @param tags 当前标签
     */
    public void toAddTagPage(String tags) {
        Bundle bundle = new Bundle();
        bundle.putString("tags", tags);
        activity.startActivityForResult(activity, EditTagsPageActivity.class, bundle, EDIT_GOODS_TAG, false);
    }

    public void changeGoodsType(int requestCode, String type) {
        String s = type.split("\\|")[0];
        if (TextUtils.equals(s, "否")) {        //当前为否，点击改为是
            loserGoodsInfo.setCard(false);
            if (requestCode == EDIT_GOODS_IS_CARD_REQUEST_CODE) {
                iView.onChangeValueAfterEdit(EDIT_GOODS_IS_CARD_REQUEST_CODE, activity.getResources().getString(R.string.edit_with_yes));
            } else if (requestCode == EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE) {
                iView.onChangeValueAfterEdit(EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE, activity.getResources().getString(R.string.edit_with_yes));
            }
        } else {                              //当前为是，点击改为否
            loserGoodsInfo.setCredit(true);
            if (requestCode == EDIT_GOODS_IS_CARD_REQUEST_CODE) {
                iView.onChangeValueAfterEdit(EDIT_GOODS_IS_CARD_REQUEST_CODE, activity.getResources().getString(R.string.edit_with_no));
            } else if (requestCode == EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE) {
                iView.onChangeValueAfterEdit(EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE, activity.getResources().getString(R.string.edit_with_no));
            }
        }
    }

    public void setLoserGoodsInfo(int requestCode, String value) {
        if (requestCode == EDIT_GOODS_NAME_REQUEST_CODE) {
            loserGoodsInfo.setGoodsName(value);
        } else if (requestCode == EDIT_GOODS_DATE_REQUEST_CODE) {
            loserGoodsInfo.setLoseDate(value);
        } else if (requestCode == EDIT_GOODS_ADDRESS_REQUEST_CODE) {
            loserGoodsInfo.setLoseAddress(value);
        } else if (requestCode == EDIT_GOODS_REWARD_REQUEST_CODE) {
            loserGoodsInfo.setReward(Float.valueOf(value));
        } else if (requestCode == EDIT_GOODS_IS_CARD_REQUEST_CODE) {
            loserGoodsInfo.setCard(true);
        } else if (requestCode == EDIT_GOODS_IS_CERTIFICATE_REQUEST_CODE) {
            loserGoodsInfo.setCredit(false);
        } else if (requestCode == EDIT_GOODS_TEL_REQUEST_CODE) {
            loserGoodsInfo.setTel(value);
        } else if (requestCode == EDIT_GOODS_WECHAT_REQUEST_CODE) {
            loserGoodsInfo.setWechat(value);
        } else if (requestCode == EDIT_GOODS_QQ_REQUEST_CODE) {
            loserGoodsInfo.setQq(value);
        } else if (requestCode == EDIT_GOODS_ICON) {
            loserGoodsInfo.setGoodsIcon(value);
        } else if (requestCode == EDIT_GOODS_DESCRIPTION) {
            loserGoodsInfo.setDiscribe(value);
        } else if (requestCode == EDIT_GOODS_TAG) {
            loserGoodsInfo.setGoodsTag(value);
        }
    }

    public LoserGoodsInfo getLoserGoodsInfo() {
        return this.loserGoodsInfo;
    }

    public void resetTag(String tags) {
        this.setLoserGoodsInfo(PresenterQuick.EDIT_GOODS_TAG, tags);
        if (tags != null) {
            iView.onNewTagsAdded(tags);
        }
    }
}
