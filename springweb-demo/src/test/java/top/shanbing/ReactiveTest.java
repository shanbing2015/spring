package top.shanbing;

import jdk.nashorn.internal.ir.Block;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import top.shanbing.result.JsonResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReactiveTest {

    @Test
    public void test(){
        WebClient.create("http://localhost:8080").get().uri("user").accept().exchange().flatMap(resp -> resp.bodyToMono(JsonResult.class)).block();
    }
}
