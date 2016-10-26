package com.shixia.diudiuma.bmob.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by AmosShi on 2016/10/26.
 * <p>
 * Description:默认用户类，登录注册的时候使用
 */

public class DDMUser extends BmobUser {
    /**
     * BmobUser除了从BmobObject继承的属性外，还有几个特定的属性：
     * <p>
     * username: 用户的用户名（必需）。
     * password: 用户的密码（必需）。
     * email: 用户的电子邮件地址（可选）。
     * emailVerified:邮箱认证状态（可选）。
     * mobilePhoneNumber：手机号码（可选）。
     * mobilePhoneNumberVerified：手机号码的认证状态（可选）。
     */

    public DDMUser() {

    }

    private Integer sex;    //性别

    private Integer age;    //年龄

    private String weChat;  //微信号

    private String QQ;      //QQ号

    private BmobFile avatar;    //用户头像

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }
}
