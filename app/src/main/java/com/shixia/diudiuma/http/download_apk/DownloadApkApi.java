package com.shixia.diudiuma.http.download_apk;

import com.shixia.diudiuma.http.ServletHttpConstants;
import com.shixia.diudiuma.http.base.HttpApiBase;

/**
 * Created by AmosShi on 2016/10/19.
 * Description:
 */

public class DownloadApkApi extends HttpApiBase<DownloadApkHttpRequest,DownloadApkHttpResponse>{
    @Override
    public String getApiName() {
        return ServletHttpConstants.updataApk;
    }

    @Override
    protected Class<DownloadApkHttpResponse> getClazz() {
        return DownloadApkHttpResponse.class;
    }
}
