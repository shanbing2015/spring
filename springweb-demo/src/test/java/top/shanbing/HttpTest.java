package top.shanbing;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.assertj.core.util.Lists;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.shanbing.util.http.GetConfCall;
import top.shanbing.util.http.HttpCallable;
import top.shanbing.util.http.HttpClientFactory;
import top.shanbing.util.http.async.HttpAsyncUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
//@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HttpTest {



    @Test
    public void send() throws Exception {
        System.out.println("send...");
        //模拟并发数
        int concurrencyNumber = 1000;

        List<String> resList = Lists.newArrayList();

        //执行线程池
        ExecutorService ex = Executors.newFixedThreadPool(100);

        String url = "www.baidu.com";
        Map<String, String> mapPatam = new HashMap<>();
        mapPatam.put("name", "测试");

        for(int i =0 ; i< concurrencyNumber; i++){
            String content=HttpAsyncUtil.get("http://120.79.223.130:8080/");
            System.out.println(i);
            resList.add(i + ">>>" + content);
        }

        for(String s : resList){
            System.out.println("------>" + s);
        }


    }
}
