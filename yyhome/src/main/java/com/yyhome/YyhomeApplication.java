package com.yyhome;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.yyhome.dao.mapper.*")
@EnableTransactionManagement
@EnableApolloConfig
@EnableAspectJAutoProxy
public class YyhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyhomeApplication.class, args);
    }

}
