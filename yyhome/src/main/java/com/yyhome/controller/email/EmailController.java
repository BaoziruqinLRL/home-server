package com.yyhome.controller.email;

import com.yyhome.common.util.ApiResponse;
import com.yyhome.common.util.BeanTools;
import com.yyhome.data.bo.EmailJobBO;
import com.yyhome.data.vo.mail.MailVO;
import com.yyhome.service.email.EmailSendService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author miluo
 * @date 2019-09-25
 */
@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Resource
    private EmailSendService emailSendService;

    @RequestMapping(value = "/send-simple-context")
    public ApiResponse sendSimpleContext(@RequestBody MailVO mailVO){
        return emailSendService.sendTextMail(BeanTools.copy(mailVO, EmailJobBO.class));
    }
}
