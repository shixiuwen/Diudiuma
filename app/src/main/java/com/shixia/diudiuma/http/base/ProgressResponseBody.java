package com.shixia.diudiuma.http.base;

import com.shixia.diudiuma.common_utils.L;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by AmosShi on 2016/10/19.
 * Description:
 */

public abstract class ProgressResponseBody extends ResponseBody {
    //实际的待包装响应体
    private final ResponseBody responseBody;
    //包装完成的BufferedSource
    private BufferedSource bufferedSource;

    /**
     * 构造函数，赋值
     * @param responseBody 待包装的响应体
     */
    public ProgressResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }


    /**
     * 重写调用实际的响应体的contentType
     * @return MediaType
     */
    @Override public MediaType contentType() {
        return responseBody.contentType();
    }

    /**
     * 重写调用实际的响应体的contentLength
     * @return contentLength
     */
    @Override public long contentLength() {
        L.i("download size",responseBody.contentLength()+ "");
        return responseBody.contentLength();
    }

    /**
     * 重写进行包装source
     * @return BufferedSource
     */
    @Override public BufferedSource source(){
        if (bufferedSource == null) {
            //包装
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    /**
     * 读取，回调进度接口
     * @param source Source
     * @return Source
     */
    private Source source(Source source) {

        return new ForwardingSource(source) {
            private long total;
            private int last = 0;
            //当前读取字节数
            long totalBytesRead = 0L;
            @Override public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                if (total == 0) {
                    total = contentLength();
                    L.i("download size",total+ "");
                }
                //增加当前读取的字节数，如果读取完成了bytesRead会返回-1
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                //回调，如果contentLength()不知道长度，会返回 -1
                int now = (int) (totalBytesRead * 100 / total);
                if (last < now) {
                    loading(last, 100, total == totalBytesRead);
                    last = now;
                }
                return bytesRead;
            }
        };
    }

    public abstract void loading(long current, long total, boolean done);
}
