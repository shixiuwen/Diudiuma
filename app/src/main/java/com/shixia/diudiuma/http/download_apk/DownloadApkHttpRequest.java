package com.shixia.diudiuma.http.download_apk;

import android.support.v4.util.ArrayMap;

import com.shixia.diudiuma.http.base.HttpBaseRequest;

/**
 * Created by AmosShi on 2016/10/19.
 * Description:
 */

public class DownloadApkHttpRequest extends HttpBaseRequest {

    private static final String CURRENT_VERSION = "current_version";

    public DownloadApkHttpRequest(String currentVersion){
        ArrayMap<String,String> paramsMap = new ArrayMap<String,String>();
        paramsMap.put(CURRENT_VERSION,currentVersion);
        setParamsMap(paramsMap);
    }
}
