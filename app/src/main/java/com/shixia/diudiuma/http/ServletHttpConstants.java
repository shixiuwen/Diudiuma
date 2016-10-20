package com.shixia.diudiuma.http;

/**
 * Created by AmosShi on 2016/10/13.
 *
 * Description: 该类用于提供本地Tomcat服务器自测接口，在后台接口未准备好的情况下提供后台测试数据
 *
 * @link 后台项目地址：https://github.com/shixiuwen/DiuDiumaServer
 */

public class ServletHttpConstants {

    //内网测试环境
    public static final String baseUrl = "http://172.30.228.1:8080/DiuDiumaServer";

    //意见反馈
    public static final String commitFeedback = "/servlet/TestServlet";

    //上传图片
    public static final String uploadPic = "/servlet/UploadServlet";

    //下载apk更新包
    public static final String updataApk = "/servlet/DownloadApkServlet";
}
