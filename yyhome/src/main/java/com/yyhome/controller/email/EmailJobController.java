package com.yyhome.controller.email;

import com.yyhome.common.ApiResponse;
import com.yyhome.data.vo.mail.EmailJobRuleVO;
import com.yyhome.data.vo.mail.EmailJobVO;
import com.yyhome.service.email.EmailJobRuleService;
import com.yyhome.service.email.EmailService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author miluo
 * @date 2019-09-27
 */
@RestController
@RequestMapping(value = "/email-job")
public class EmailJobController {

    @Resource
    private EmailService emailService;

    @Resource
    private EmailJobRuleService ruleService;

    @RequestMapping(value = "/list")
    public ApiResponse findList(@RequestBody EmailJobVO param){
        param.setUserId(10000L);
        return ApiResponse.success(emailService.getEmailJobList(param));
    }

    @RequestMapping(value = "/create")
    public ApiResponse createJob(@RequestBody EmailJobVO email){
        email.setUserId(10000L);
        return ApiResponse.success(emailService.createEmailJob(email));
    }

    @RequestMapping(value = "/rule/create")
    public ApiResponse createJobRule(@RequestBody EmailJobRuleVO email){
        email.setUserId(10000L);
        return ApiResponse.success(ruleService.createJobRule(email));
    }

}
