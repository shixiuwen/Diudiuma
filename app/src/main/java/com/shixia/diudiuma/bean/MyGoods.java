package com.shixia.diudiuma.bean;

/**
 * Created by AmosShi on 2016/10/25.
 * Description:展示在首页的个人物品信息
 */

public class MyGoods {

    private String goodsName;
    private String goodsRegisterData;
    private String goodsTag;
    private String avatar;
    private float reward;
    private String tel;
    private String weChat;
    private String QQ;
    private String ddm;

    public MyGoods() {
    }

    public MyGoods(String goodsName, String goodsRegisterData,
                   String goodsTag, String avatar, float reward,
                   String tel, String weChat,
                   String QQ, String ddm) {
        this.goodsName = goodsName;
        this.goodsRegisterData = goodsRegisterData;
        this.goodsTag = goodsTag;
        this.avatar = avatar;
        this.reward = reward;
        this.tel = tel;
        this.weChat = weChat;
        this.QQ = QQ;

        this.ddm = ddm;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsRegisterData() {
        return goodsRegisterData;
    }

    public void setGoodsRegisterData(String goodsRegisterData) {
        this.goodsRegisterData = goodsRegisterData;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public float getReward() {
        return reward;
    }

    public void setReward(float reward) {
        this.reward = reward;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getDdm() {
        return ddm;
    }

    public void setDdm(String ddm) {
        this.ddm = ddm;
    }
}
