package com.shixia.diudiuma.bmob.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by AmosShi on 2016/10/27.
 * <p>
 * Description:丢失物品或者找回物品的物品描述
 */

public class LoserGoodsInfo extends BmobObject {

    private String goodsName;   //品名
    private String loseDate;    //日期
    private String loseAddress; //地址
    private Boolean isCard;     //是否卡类
    private Boolean isCredit;   //是否证件类
    private Float reward;       //赏金
    private String discribe;    //描述（200字以内）
    private String goodsTag;    //标签
    private String goodsIcon; //照片
    private String tel;         //手机号
    private String wechat;      //微信
    private String qq;          //QQ

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLoseDate() {
        return loseDate;
    }

    public void setLoseDate(String loseDate) {
        this.loseDate = loseDate;
    }

    public String getLoseAddress() {
        return loseAddress;
    }

    public void setLoseAddress(String loseAddress) {
        this.loseAddress = loseAddress;
    }

    public Boolean getCard() {
        return isCard;
    }

    public void setCard(Boolean card) {
        isCard = card;
    }

    public Boolean getCredit() {
        return isCredit;
    }

    public void setCredit(Boolean credit) {
        isCredit = credit;
    }

    public Float getReward() {
        return reward;
    }

    public void setReward(Float reward) {
        this.reward = reward;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getGoodsIcon() {
        return goodsIcon;
    }

    public void setGoodsIcon(String goodsIcon) {
        this.goodsIcon = goodsIcon;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

}
