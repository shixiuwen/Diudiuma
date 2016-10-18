package com.shixia.diudiuma.http.base;

import android.support.v4.util.ArrayMap;

import java.util.List;

/**
 * Created by AmosShi on 2016/10/10.
 * Description:
 */

public abstract class HttpBaseRequest {

    private ArrayMap<String, String> paramsMap;
    private List<String> paramList;

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

    /**
     * 设置上传文件的参数集
     * @param paramList 文件地址
     */
    protected void setParamList(List<String> paramList){
        this.paramList = paramList;
    }

    List<String> getParamsList(){
        return paramList;
    }
}
