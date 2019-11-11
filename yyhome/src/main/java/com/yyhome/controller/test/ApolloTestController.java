package com.yyhome.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miluo
 * @date 2019-11-11
 */
@RestController
@RequestMapping(value = "/apollo/test")
@Slf4j
public class ApolloTestController {

    @RequestMapping(value = "/logger-lever")
    public void printLog(){
        log.info("this is an info log");
        log.warn("this is a warning log");
        log.debug("this is a debug log");
        log.error("this is an error log");
    }
}
