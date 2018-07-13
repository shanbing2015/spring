package top.shanbing.configuration.enableAuto;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
//@EnableAutoConfiguration( exclude = RedisAutoConfiguration.class)   //禁用特定的自动配置类
public class baseEnableAutoConfiguration {
}
