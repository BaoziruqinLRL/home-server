package com.yyhome.controller.email;

import com.yyhome.common.ApiResponse;
import com.yyhome.data.vo.mail.EmailJobRuleVO;
import com.yyhome.data.vo.mail.EmailJobVO;
import com.yyhome.service.email.EmailJobRuleService;
import com.yyhome.service.email.EmailJobService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private EmailJobService emailJobService;

    @Resource
    private EmailJobRuleService ruleService;

    @RequestMapping(value = "/list")
    public ApiResponse findList(@RequestBody EmailJobVO param){
        param.setUserId(10000L);
        return ApiResponse.success(emailJobService.getEmailJobList(param));
    }

    @RequestMapping(value = "/create")
    public ApiResponse createJob(@RequestBody EmailJobVO email){
        email.setUserId(10000L);
        return ApiResponse.success(emailJobService.createEmailJob(email));
    }

    @RequestMapping(value = "/delete")
    public ApiResponse deleteJob(@RequestParam Long emailId){
        return ApiResponse.success(emailJobService.deleteEmailJob(emailId));
    }

    @RequestMapping(value = "/rule/create")
    public ApiResponse createJobRule(@RequestBody EmailJobRuleVO rule){
        rule.setUserId(10000L);
        return ApiResponse.success(ruleService.createJobRule(rule));
    }

    @RequestMapping(value = "/rule/delete")
    public ApiResponse deleteJobRule(@RequestBody EmailJobRuleVO rule){
        rule.setUserId(10000L);
        return ApiResponse.success(ruleService.deleteJobRule(rule));
    }
}
