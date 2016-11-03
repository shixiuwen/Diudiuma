package com.shixia.diudiuma.bmob.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by AmosShi on 2016/11/3.
 *
 * Description:意见反馈内容
 */

public class FeedbackContent extends BmobObject {

    private String content;
    private String contact;
    private String userName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
