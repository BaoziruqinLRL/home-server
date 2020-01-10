package com.yyhome.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    public static void main(String[] args) throws ParseException {
        var format = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        System.out.println(format.parse("2019-12-31 23:00:00"));

        format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(format.parse("2019-12-31 23:00:00"));

        format = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        System.out.println(format.parse("2019-12-29 23:00:00"));

        format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(format.parse("2019-12-29 23:00:00"));
    }
}
