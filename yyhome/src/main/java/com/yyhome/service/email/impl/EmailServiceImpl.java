package com.yyhome.service.email.impl;

import com.yyhome.common.BeanTools;
import com.yyhome.common.JudgeUtil;
import com.yyhome.dao.mapper.EmailJobPOMapper;
import com.yyhome.dao.mapper.EmailJobRulePOMapper;
import com.yyhome.data.bo.EmailJobBO;
import com.yyhome.data.bo.EmailJobRuleBO;
import com.yyhome.data.enums.EmailJobTypeEnums;
import com.yyhome.data.example.EmailJobPOExample;
import com.yyhome.data.example.EmailJobRulePOExample;
import com.yyhome.data.po.EmailJobPO;
import com.yyhome.data.vo.mail.EmailJobVO;
import com.yyhome.service.email.EmailService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author miluo
 * @date 2019-09-27
 */
@Component
public class EmailServiceImpl implements EmailService {

    @Resource
    private EmailJobPOMapper emailMapper;

    @Resource
    private EmailJobRulePOMapper ruleMapper;
    @Override
    public List<EmailJobBO> getEmailJobList(EmailJobVO param) {
        var jobParam = new EmailJobPOExample();
        var cri = jobParam.createCriteria();
        cri.andCreateUserEqualTo(param.getUserId());
        JudgeUtil.notNullSet(param.getName(), cri::andNameLike);
        JudgeUtil.notNullSet(param.getSender(), cri::andSenderLike);
        JudgeUtil.notNullSet(param.getType(), cri::andTypeEqualTo);
        var jobList = emailMapper.selectByExample(jobParam);
        var jobIds = jobList.stream().map(EmailJobPO::getId).collect(Collectors.toList());
        var result = new ArrayList<EmailJobBO>(jobList.size());
        if (CollectionUtils.isNotEmpty(jobIds)) {
            var ruleParam = new EmailJobRulePOExample();
            var ruleCri = ruleParam.createCriteria();
            JudgeUtil.notEmptySet(jobIds, ruleCri::andEmailIdIn);
            var ruleList = ruleMapper.selectByExample(ruleParam);
            var ruleMap = ruleList.stream()
                    .map(x -> BeanTools.copy(x, EmailJobRuleBO.class))
                    .collect(Collectors.groupingBy(EmailJobRuleBO::getEmailId));
            jobList.forEach(job -> {
                var jo = BeanTools.copy(job, EmailJobBO.class);
                JudgeUtil.notNullSet(jo, email -> email.setTypeName(EmailJobTypeEnums.convert(email.getType()).desc()));
                JudgeUtil.notNullSet(jo, email -> email.setRules(ruleMap.get(job.getId())));
                result.add(jo);
            });
        }
        return result;
    }
}
