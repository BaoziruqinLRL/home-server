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

    private List<String> to;
}
