package top.shanbing.util.http;

import java.util.concurrent.Callable;

public class HttpCallable implements Callable<String> {

    private String url;

    private String jsonParams;

    public HttpCallable(String url, String jsonParams){
        this.url = url;
        this.jsonParams = jsonParams;
    }

    /**
     * 执行并返回结果
     */
    @Override
    public String call() throws Exception {
        //return HttpTool.requestPost(url, jsonParams);
        return null;
    }

}
