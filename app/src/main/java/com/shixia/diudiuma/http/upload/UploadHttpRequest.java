package com.shixia.diudiuma.http.upload;

import com.shixia.diudiuma.http.base.HttpBaseRequest;

import java.util.List;


/**
 * Created by AmosShi on 2016/10/13.
 * Description:意见反馈请求
 */

public class UploadHttpRequest extends HttpBaseRequest {


    /*ArrayMap<String, Object> map = new ArrayMap<>();
    map.put("fbDesc", et_feedback.getText().toString());
    map.put("mobile", feedbackPhone);
    map.put("fbPlatform", "1");*/

    public UploadHttpRequest(List<String> picUri) {
        super();

        setParamsList(picUri);
    }

}
