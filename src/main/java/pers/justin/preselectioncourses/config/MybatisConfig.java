package pers.justin.preselectioncourses.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Justin on 2022-02-20 16:23
 * 做完以上操作后,需要替换下,以前默认的实现,刚好,mybatis-spring-boot上面有告诉我们怎么做,返回一个 ConfigurationCustomizer 的bean,通过匿名内部类实现覆盖默认的MapWrapper的findProperty函数
 */
@SpringBootConfiguration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MapWrapperFactory());
    }
}
