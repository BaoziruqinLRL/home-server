package com.yyhome.service.email.impl;

import com.yyhome.common.ApiResponse;
import com.yyhome.common.BeanTools;
import com.yyhome.dao.mapper.EmailJobRulePOMapper;
import com.yyhome.data.bo.EmailJobRuleBO;
import com.yyhome.data.po.EmailJobRulePO;
import com.yyhome.data.vo.mail.EmailJobRuleVO;
import com.yyhome.data.vo.mail.EmailJobVO;
import com.yyhome.job.EmailSendProcessor;
import com.yyhome.service.email.EmailJobRuleService;
import com.yyhome.service.email.EmailJobService;
import com.yyhome.service.email.EmailSendService;
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
    private EmailJobRulePOMapper ruleMapper;

    @Override
    public EmailJobRuleBO createJobRule(EmailJobRuleVO rule) {
        var po = BeanTools.copy(rule, EmailJobRulePO.class);
        int ruleRes;
        if (po.getId() == null) {
            ruleRes = ruleMapper.insert(po);
        }else{
            ruleRes = ruleMapper.updateByPrimaryKeySelective(po);
        }
        if (ruleRes == 1){
            sendService.reAddMailToWheel(rule.getEmailId());
            return BeanTools.copy(po,EmailJobRuleBO.class);
        }
        return null;
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
