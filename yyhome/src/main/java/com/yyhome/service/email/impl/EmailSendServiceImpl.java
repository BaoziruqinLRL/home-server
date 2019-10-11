package com.yyhome.service.email.impl;

import com.yyhome.common.ApiResponse;
import com.yyhome.config.EmailConfig;
import com.yyhome.data.bo.EmailJobBO;
import com.yyhome.data.bo.MailBO;
import com.yyhome.data.vo.mail.EmailJobVO;
import com.yyhome.job.EmailSendProcessor;
import com.yyhome.service.email.EmailJobService;
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

    @Resource
    private EmailSendProcessor sendProcessor;

    @Resource
    private EmailJobService jobService;

    @Override
    public ApiResponse sendTextMail(EmailJobBO mail) {
        try {
            var array = new SimpleMailMessage[emailConfig.getTo().size()];
            var index = 0;
            for (var to : emailConfig.getTo()){
                var message = new SimpleMailMessage();
                message.setFrom(mail.getSender());
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

    @Override
    public void reAddMailToWheel(Long emailId) {
        var param = new EmailJobVO();
        param.setUserId(10000L);
        param.setId(emailId);
        var job = jobService.getEmailJobList(param).get(0);
        sendProcessor.reAddToWheel(job);
    }
}
