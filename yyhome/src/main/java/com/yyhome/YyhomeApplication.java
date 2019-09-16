package com.yyhome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yyhome.dao.mapper.*")
public class YyhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyhomeApplication.class, args);
    }

}
