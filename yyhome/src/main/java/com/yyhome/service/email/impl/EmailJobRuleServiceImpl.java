package com.yyhome.service.email.impl;

import com.yyhome.common.BeanTools;
import com.yyhome.dao.mapper.EmailJobRulePOMapper;
import com.yyhome.data.bo.EmailJobRuleBO;
import com.yyhome.data.po.EmailJobRulePO;
import com.yyhome.data.vo.mail.EmailJobRuleVO;
import com.yyhome.service.email.EmailJobRuleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author miluo
 * @date 2019-10-08
 */
@Component
public class EmailJobRuleServiceImpl implements EmailJobRuleService {

    @Resource
    private EmailJobRulePOMapper ruleMapper;

    @Override
    public EmailJobRuleBO createJobRule(EmailJobRuleVO rule) {
        var po = BeanTools.copy(rule, EmailJobRulePO.class);
        var ruleRes = ruleMapper.insert(po);
        if (ruleRes == 1){
            return BeanTools.copy(po,EmailJobRuleBO.class);
        }
        return null;
    }
}
