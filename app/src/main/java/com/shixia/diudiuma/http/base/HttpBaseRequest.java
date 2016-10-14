package com.shixia.diudiuma.http.base;

import android.support.v4.util.ArrayMap;

/**
 * Created by AmosShi on 2016/10/10.
 * Description:
 */

public abstract class HttpBaseRequest {

    private ArrayMap<String, String> paramsMap;

    public HttpBaseRequest() {
    }

    /**
     * 设置参数集
     * @param paramsMap 参数集
     */
    protected void setParamsMap(ArrayMap<String, String> paramsMap) {
        this.paramsMap = paramsMap;
    }

    /**
     * 获取参数集
     * @return 参数集
     */
    ArrayMap<String, String> getParamsMap() {
        return paramsMap;
    }

}
