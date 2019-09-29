package com.yyhome.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author miluo
 * @date 2019-09-24
 */
@ConfigurationProperties(prefix = "mail")
@Configuration
@Data
public class EmailConfig {

    @Value("${mail.from.username}")
    private String fromUser;

    @Value("${mail.from.password}")
    private String fromPass;

    private List<String> to;
}
