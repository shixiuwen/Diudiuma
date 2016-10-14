package com.shixia.diudiuma.http.feedback;

import com.shixia.diudiuma.http.base.HttpBaseResponse;

/**
 * Created by AmosShi on 2016/10/13.
 * Description:
 */

public class FeedbackHttpResponse extends HttpBaseResponse {
    private int status;
    private String info;
    private Data data;

    public FeedbackHttpResponse() {
    }

    public FeedbackHttpResponse(int status, String info, Data data) {
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
