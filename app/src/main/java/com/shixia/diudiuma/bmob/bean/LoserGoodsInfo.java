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
    private String goodsIcon;   //照片
    private String tel;         //手机号
    private String wechat;      //微信
    private String qq;          //QQ

    private Integer type;       //1为寻物启事，2为失物招领，3为注册物品（注册物品一定会有丢丢码）
    private String ddm;         //该选项为注册物品才有，其他类型（非3）该选项为空

    private String publisherName;   //发布者名称，默认无需设置，不显示在界面上，用于查询

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getDdm() {
        return ddm;
    }

    public void setDdm(String ddm) {
        this.ddm = ddm;
    }

}
