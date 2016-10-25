package com.shixia.diudiuma.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by AmosShi on 2016/10/25.
 * Description:
 */

public class UserInfo extends BmobObject {
    private String name;
    private String password;

    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
