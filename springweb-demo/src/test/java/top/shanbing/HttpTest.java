package top.shanbing;

import org.apache.http.client.HttpClient;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HttpTest {

    @Test
    public void send(){
        System.out.println("send...");
       // HttpClient httpClient = new HttpClient();
    }
}
