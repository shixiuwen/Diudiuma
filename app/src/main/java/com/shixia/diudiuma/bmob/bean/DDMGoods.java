package com.shixia.diudiuma.bmob.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by AmosShi on 2016/10/28.
 * Description:
 */

public class DDMGoods extends BmobObject {
    //以下五条条是简洁信息
    private String ddmGoodsName;        //品名
//    private Date date;                  //注册日期服务器会自动保存,所以不需要特殊声明
    private Float ddmGoodsReward;       //赏金
    private String ddmGoodsTag;         //标签
    private BmobFile pic;               //图像

    //以下几条是展开的详细信息
    private String ddmGoodsAddress;     //地址
    private String ddmCode;             //丢丢码
    private Boolean isCard;             //是否卡类
    private Boolean isCertificate;      //是否证件类
    private String tel;                 //电话
    private String wechat;              //微信
    private String qq;                  //QQ
    private String describe;            //描述信息

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String discribe) {
        this.describe = discribe;
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

    public BmobFile getPic() {
        return pic;
    }

    public void setPic(BmobFile pic) {
        this.pic = pic;
    }

    public String getDdmGoodsTag() {
        return ddmGoodsTag;
    }

    public void setDdmGoodsTag(String ddmGoodsTag) {
        this.ddmGoodsTag = ddmGoodsTag;
    }

    public Float getDdmGoodsReward() {
        return ddmGoodsReward;
    }

    public void setDdmGoodsReward(Float ddmGoodsReward) {
        this.ddmGoodsReward = ddmGoodsReward;
    }

    public String getDdmGoodsName() {
        return ddmGoodsName;
    }

    public void setDdmGoodsName(String ddmGoodsName) {
        this.ddmGoodsName = ddmGoodsName;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
}
