package com.yyhome;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.yyhome.dao.mapper.*")
@EnableTransactionManagement
@EnableApolloConfig
public class YyhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyhomeApplication.class, args);
    }

}
