package com.shixia.diudiuma.http.feedback;

import android.support.v4.util.ArrayMap;

import com.shixia.diudiuma.http.base.HttpBaseRequest;


/**
 * Created by AmosShi on 2016/10/13.
 * Description:意见反馈请求
 */

public class FeedbackHttpRequest extends HttpBaseRequest {


    /*ArrayMap<String, Object> map = new ArrayMap<>();
    map.put("fbDesc", et_feedback.getText().toString());
    map.put("mobile", feedbackPhone);
    map.put("fbPlatform", "1");*/

    private static final String content = "fbDesc";
    private static final String mobile = "mobile";
    private static final String platform = "fbPlatform";

    public FeedbackHttpRequest(String content,String mobile,String platform) {
        super();
        ArrayMap<String,String> feedbackRequest = new ArrayMap<String,String>();
        feedbackRequest.put(FeedbackHttpRequest.content,content);
        feedbackRequest.put(FeedbackHttpRequest.mobile,mobile);
        feedbackRequest.put(FeedbackHttpRequest.platform,platform);

        setParamsMap(feedbackRequest);
    }

}
