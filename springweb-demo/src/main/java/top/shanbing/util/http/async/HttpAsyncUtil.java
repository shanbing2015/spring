package top.shanbing.util.http.async;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

public class HttpAsyncUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static int socketTimeout = 1000;// 设置等待数据超时时间

    private static int connectTimeout = 2000;// 连接超时
    private static int connectRequestTimeout = 2000;

    private static int poolSize = 3000;// 连接池最大连接数

    private static int maxPerRoute = 1500;// 每个主机的并发最多只有1500

    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectRequestTimeout).build();

    public static String get(String url) throws Exception {
        return asyncRequest("get",url,null,null);
    }
    public static String syncGet(String url) throws Exception {
        return request("get",url,null,null);
    }

    public static String get(String url, Map<String, String> params) throws Exception {
        return asyncRequest("get",url,params,null);
    }

    public static String post(String url) throws Exception {
        return asyncRequest("post",url,null,null);
    }

    public static String post(String url, Map<String, String> params) throws Exception {
        return asyncRequest("post",url,params,null);
    }

    public static String asyncRequest(String method,String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();
        try {
            httpclient.start();

            RequestBuilder builder=RequestBuilder.create(method.toUpperCase()).setUri(url);
            builder.setCharset(Consts.UTF_8);
            builder.setConfig(requestConfig);
            if (params != null && !params.isEmpty()) {
                Set<String> keys = params.keySet();
                for (String key : keys) {
                    builder.addParameter(key, params.get(key));
                }
            }
            if (headers != null && !headers.isEmpty()) {
                Set<String> keys = headers.keySet();
                for (String key : keys) {
                    builder.addHeader(key, headers.get(key));
                }
            }

            HttpUriRequest request = builder.build();
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            return EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } finally {
            httpclient.close();
        }
    }

    public static String request(String method,String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        try {
            RequestBuilder builder=RequestBuilder.create(method.toUpperCase()).setUri(url);
            builder.setCharset(Consts.UTF_8);
            builder.setConfig(requestConfig);
            if (params != null && !params.isEmpty()) {
                Set<String> keys = params.keySet();
                for (String key : keys) {
                    builder.addParameter(key, params.get(key));
                }
            }
            if (headers != null && !headers.isEmpty()) {
                Set<String> keys = headers.keySet();
                for (String key : keys) {
                    builder.addHeader(key, headers.get(key));
                }
            }

            HttpUriRequest request = builder.build();
            CloseableHttpResponse response = httpclient.execute(request);
            return EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } finally {
            httpclient.close();
        }
    }
}
