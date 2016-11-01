package com.shixia.diudiuma.bmob.bean;

import com.shixia.diudiuma.adapter.ExpandableItemAdapter;
import com.shixia.diudiuma.bean.MultiItemEntity;

/**
 * Created by AmosShi on 2016/10/31.
 * Description:
 */

public class DDMGoodsLever0Item extends AbstractExpandableItem<DDMGoodsLever1Item> implements MultiItemEntity {

    //以下五条条是简洁信息
    private String ddmGoodsName;        //品名
    private String registerDate;                  //注册日期服务器会自动保存,所以不需要特殊声明
    private Float ddmGoodsReward;       //赏金
    private String ddmGoodsTag;         //标签
    private String pic;               //图像

    public DDMGoodsLever0Item(String ddmGoodsName, String registerDate, Float ddmGoodsReward,
                              String ddmGoodsTag, String pic) {
        this.ddmGoodsName = ddmGoodsName;
        this.registerDate = registerDate;
        this.ddmGoodsReward = ddmGoodsReward;
        this.ddmGoodsTag = ddmGoodsTag;
        this.pic = pic;
    }

    public String getDdmGoodsName() {
        return ddmGoodsName;
    }

    public void setDdmGoodsName(String ddmGoodsName) {
        this.ddmGoodsName = ddmGoodsName;
    }

    public Float getDdmGoodsReward() {
        return ddmGoodsReward;
    }

    public void setDdmGoodsReward(Float ddmGoodsReward) {
        this.ddmGoodsReward = ddmGoodsReward;
    }

    public String getDdmGoodsTag() {
        return ddmGoodsTag;
    }

    public void setDdmGoodsTag(String ddmGoodsTag) {
        this.ddmGoodsTag = ddmGoodsTag;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String date) {
        this.registerDate = date;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
