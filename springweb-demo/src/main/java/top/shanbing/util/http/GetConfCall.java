package top.shanbing.util.http;

import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetConfCall implements FutureCallback<HttpResponse> {


    /**
     * 请求完成后调用该函数
     */
    @Override
    public void completed(HttpResponse response) {

        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(getHttpContent(response));

        HttpClientUtils.closeQuietly(response);

    }

    /**
     * 请求取消后调用该函数
     */
    @Override
    public void cancelled() {

    }

    /**
     * 请求失败后调用该函数
     */
    @Override
    public void failed(Exception e) {

    }

    protected String getHttpContent(HttpResponse response) {

        HttpEntity entity = response.getEntity();
        String body = null;

        if (entity == null) {
            return null;
        }

        try {

            body = EntityUtils.toString(entity, "utf-8");

        } catch (ParseException e) {

           // Log.warn("the response's content inputstream is corrupt", e);
        } catch (IOException e) {

           // LOG.warn("the response's content inputstream is corrupt", e);
        }
        return body;
    }

}
