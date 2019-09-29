package com.yyhome.service.email.impl;

import com.yyhome.common.ApiResponse;
import com.yyhome.config.EmailConfig;
import com.yyhome.data.bo.MailBO;
import com.yyhome.service.email.EmailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author miluo
 * @date 2019-09-24
 */
@Component
@Slf4j
public class EmailSendServiceImpl implements EmailSendService {

    @Resource
    private EmailConfig emailConfig;

    @Resource
    private JavaMailSender mailSender;

    @Override
    public ApiResponse sendTextMail(MailBO mail) {
        try {
            var array = new SimpleMailMessage[emailConfig.getTo().size()];
            var index = 0;
            for (var to : emailConfig.getTo()){
                var message = new SimpleMailMessage();
                message.setFrom("529868669@qq.com");
                message.setSubject(mail.getSubject());
                message.setText(mail.getContext());
                message.setTo(to);
                array[index++] = message;
            }
            mailSender.send(array);
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return ApiResponse.fail(e.getMessage());
        }
        return ApiResponse.success();
    }

    @Override
    public ApiResponse sendAttachmentsMail(MailBO mail) {
        return null;
    }
}
