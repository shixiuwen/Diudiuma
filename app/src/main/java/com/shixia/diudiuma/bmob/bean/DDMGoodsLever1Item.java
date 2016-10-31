package com.shixia.diudiuma.bmob.bean;

import com.shixia.diudiuma.adapter.ExpandableItemAdapter;
import com.shixia.diudiuma.bean.MultiItemEntity;

/**
 * Created by AmosShi on 2016/10/31.
 * Description:
 */

public class DDMGoodsLever1Item implements MultiItemEntity {

    //以下几条是展开的详细信息
    private String ddmGoodsAddress;     //地址
    private String ddmCode;             //丢丢码
    private Boolean isCard;             //是否卡类
    private Boolean isCertificate;      //是否证件类
    private String tel;                 //电话
    private String wechat;              //微信
    private String qq;                  //QQ

    public DDMGoodsLever1Item(String ddmGoodsAddress, String ddmCode,
                              Boolean isCard, Boolean isCertificate,
                              String tel, String wechat,
                              String qq, String describe) {
        this.ddmGoodsAddress = ddmGoodsAddress;
        this.ddmCode = ddmCode;
        this.isCard = isCard;
        this.isCertificate = isCertificate;
        this.tel = tel;
        this.wechat = wechat;
        this.qq = qq;
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getCertificate() {
        return isCertificate;
    }

    public void setCertificate(Boolean certificate) {
        isCertificate = certificate;
    }

    public Boolean getCard() {
        return isCard;
    }

    public void setCard(Boolean card) {
        isCard = card;
    }

    public String getDdmCode() {
        return ddmCode;
    }

    public void setDdmCode(String ddmCode) {
        this.ddmCode = ddmCode;
    }

    public String getDdmGoodsAddress() {
        return ddmGoodsAddress;
    }

    public void setDdmGoodsAddress(String ddmGoodsAddress) {
        this.ddmGoodsAddress = ddmGoodsAddress;
    }

    private String describe;            //描述信息

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}
