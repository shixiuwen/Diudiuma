package com.shixia.diudiuma.http.base;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.http.ServletHttpConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by AmosShi on 2016/10/10.
 * Description:发送Http请求的基类，其中泛型R为预留参数，供GET请求参数的封装
 */

public abstract class HttpApiBase<R, T> {

    private HttpBaseRequest baseRequest;

    private static final MediaType MEDIA_TYPE_PNG
            = MediaType.parse("image/png");

    private T t;

    public abstract String getApiName();

    protected abstract Class<T> getClazz();

    //初始化HttpClient
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .writeTimeout(5000, TimeUnit.MILLISECONDS).build();

    /**
     * 采用POST请求时设置请求体
     *
     * @param request request
     */
    public void setRequest(HttpBaseRequest request) {
        baseRequest = request;
    }

    /**
     * 发送携带简单参数的Http POST请求
     */
    public void post() {
        FormBody requestBody = getSimpleRequestBody(baseRequest);
        Request request = new Request.Builder()
                .url(ServletHttpConstants.baseUrl + getApiName())
                .post(requestBody)
                .build();

        L.i("url",ServletHttpConstants.baseUrl + getApiName());

        client.newCall(request).enqueue(new JsonCallback());
    }

    /**
     * 实现文件上传
     */
    public void multFilePost() {

        MultipartBody uploadRequestBody = getUploadRequestBody(baseRequest);

        //构建请求
        Request request = new Request.Builder()
                .url(ServletHttpConstants.baseUrl + getApiName())//地址
                .post(uploadRequestBody)//添加请求体
                .build();

        L.i("url",ServletHttpConstants.baseUrl + getApiName());

        client.newCall(request).enqueue(new UploadFileCallback());
    }

    /**
     * 得到简单http请求参数表单（POST）
     *
     * @param baseRequest 请求列表
     * @return 参数表单
     */
    private FormBody getSimpleRequestBody(HttpBaseRequest baseRequest) {
        //传输表单
        FormBody.Builder builder = new FormBody.Builder();

        ArrayMap<String, String> paramsMap = baseRequest.getParamsMap();
        if (null != paramsMap) {
            List<String> orderList = new ArrayList<>();
            List<String> codeList = new ArrayList<>();

            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                orderList.add(entry.getKey());
                codeList.add(entry.getKey());
                codeList.add(String.valueOf(entry.getValue()));
                builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }

//            L.i("http -> url:" + HttpConstants.baseUrl + getApiName() + "\nparams:", codeTmp);

//            builder.add("order", order);
//            builder.add("code", code);
        } else {
//            builder.add("order", "");
//            builder.add("code", code);
        }

        return builder.build();
    }

    private MultipartBody getUploadRequestBody(HttpBaseRequest baseRequest){
        List<String> mImgUrls = baseRequest.getParamsList();
        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < mImgUrls.size(); i++) {
            File f = new File(mImgUrls.get(i));
            if (f.exists()) {
                //        可添加其它信息
                //        builder.addFormDataPart("time",takePicTime);
                builder.addFormDataPart("img", f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));
            }
        }

        return builder.build();
    }

    /**
     * #################################  自定义请求Callback  ###############################
     */

    /**
     * 简单http请求回调,将请求结果转化为对象
     */
    private class JsonCallback implements Callback {

        @Override
        public void onFailure(Call call, final IOException e) {
            L.i(" http -> onFailure exception:", e.toString());
            if (jsonResponseListener != null) {
                //主线程执行回调操作
                handler.post(() -> jsonResponseListener.onFailure(e, null));
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final Gson json = new Gson();
            final String result = response.body().string();
            L.i(" http -> onResponse result:", result);
            //主线程执行回调操作
            handler.post(() -> {
                if (jsonResponseListener != null) {
                    try {
                        t = json.fromJson(result, getClazz());
                        jsonResponseListener.onSuccessful(t, result);
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                        jsonResponseListener.onFailure(e, null);
                        L.i("http -> ", "请求成功，但是结果解析异常");
                    }
                }
            });

        }
    }

    /**
     * 上传文件请求回调，返回请求成功或者失败的信息
     */
    private class UploadFileCallback implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {
            handler.post(()->{
                L.i("upload","上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
                jsonResponseListener.onFailure(e,"上传图片失败");
            });
        }

        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            handler.post(()->{
                try {
                    L.i("upload","上传照片成功：response = " + response.body().string());
                    jsonResponseListener.onSuccessful(t,"上传图片成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * #################################  网络请求自定义回调接口  ###############################
     *
     * 回调的实现在网络请求处
     */

    private JsonHttpResponseListener<T> jsonResponseListener;

    public void setOnJsonHttpResponseListener(JsonHttpResponseListener<T> jsonResponseListener) {
        this.jsonResponseListener = jsonResponseListener;
    }

    public interface JsonHttpResponseListener<T> {
        void onFailure(Exception e, String rawJsonString);

        void onSuccessful(T t, String rawJsonString);
    }

    //将请求的回调放在主线程中执行，通常是有关界面的操作
    private Handler handler = new Handler(Looper.getMainLooper());

}
