package com.shixia.diudiuma.http.feedback;

import com.shixia.diudiuma.http.HttpConstants;
import com.shixia.diudiuma.http.base.HttpApiBase;

/**
 * Created by AmosShi on 2016/10/13.
 * Description:
 */

public class FeedbackApi extends HttpApiBase<FeedbackHttpRequest,FeedbackHttpResponse> {

    @Override
    public String getApiName() {
        return HttpConstants.commitFeedback;
    }

    @Override
    protected Class<FeedbackHttpResponse> getClazz() {
        return FeedbackHttpResponse.class;
    }
}
