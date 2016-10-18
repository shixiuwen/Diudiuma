package com.shixia.diudiuma.http.upload;

import com.shixia.diudiuma.http.base.HttpBaseResponse;

/**
 * Created by AmosShi on 2016/10/13.
 * Description:
 */

public class UploadHttpResponse extends HttpBaseResponse {
    private int status;
    private String info;
    private Data data;

    public UploadHttpResponse() {
    }

    public UploadHttpResponse(int status, String info, Data data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private class Data{

    }
}
