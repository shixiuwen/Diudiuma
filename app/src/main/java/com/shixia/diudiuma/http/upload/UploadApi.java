package com.shixia.diudiuma.http.upload;

import com.shixia.diudiuma.http.ServletHttpConstants;
import com.shixia.diudiuma.http.base.HttpApiBase;

/**
 * Created by AmosShi on 2016/10/13.
 * Description:
 */

public class UploadApi extends HttpApiBase<UploadHttpRequest,UploadHttpResponse> {

    @Override
    public String getApiName() {
        return ServletHttpConstants.uploadPic;
    }

    @Override
    protected Class<UploadHttpResponse> getClazz() {
        return UploadHttpResponse.class;
    }
}
