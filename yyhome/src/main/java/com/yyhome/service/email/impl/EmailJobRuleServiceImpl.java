package com.yyhome.service.email.impl;

import com.yyhome.common.ApiResponse;
import com.yyhome.common.BeanTools;
import com.yyhome.dao.mapper.EmailJobRulePOMapper;
import com.yyhome.data.bo.EmailJobRuleBO;
import com.yyhome.data.po.EmailJobRulePO;
import com.yyhome.data.vo.mail.EmailJobRuleVO;
import com.yyhome.data.vo.mail.EmailJobVO;
import com.yyhome.service.email.EmailJobRuleService;
import com.yyhome.service.email.EmailJobService;
import com.yyhome.service.email.EmailSendService;
import com.yyhome.service.email.EmailValidateUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author miluo
 * @date 2019-10-08
 */
@Component
public class EmailJobRuleServiceImpl implements EmailJobRuleService {

    @Resource
    private EmailSendService sendService;

    @Resource
    private EmailJobService jobService;

    @Resource
    private EmailJobRulePOMapper ruleMapper;

    @Override
    public ApiResponse createJobRule(EmailJobRuleVO rule) {
        var param = new EmailJobVO();
        param.setId(rule.getEmailId());
        var job = jobService.getEmailJobList(param).get(0);
        var jobVO = BeanTools.copy(job,EmailJobVO.class);
        jobVO.setRules(BeanTools.copyCollection(job.getRules(),EmailJobRuleVO.class));
        jobVO.getRules().add(rule);
        var validateRes = EmailValidateUtil.validateRule(jobVO);
        if (validateRes != null){
            return ApiResponse.fail(validateRes);
        }
        var po = BeanTools.copy(rule, EmailJobRulePO.class);
        int ruleRes;
        if (po.getId() == null) {
            ruleRes = ruleMapper.insert(po);
        }else{
            ruleRes = ruleMapper.updateByPrimaryKeySelective(po);
        }
        if (ruleRes == 1){
            sendService.reAddMailToWheel(rule.getEmailId());
            return ApiResponse.success(BeanTools.copy(po,EmailJobRuleBO.class));
        }
        return ApiResponse.fail(null);
    }

    @Override
    public ApiResponse deleteJobRule(EmailJobRuleVO rule) {
        if (ruleMapper.deleteByPrimaryKey(rule.getId()) == 1){
            return ApiResponse.success();
        }else{
            return ApiResponse.fail("delete error");
        }
    }
}
