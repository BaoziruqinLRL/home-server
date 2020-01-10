package com.yyhome.service.email.impl;

import com.alibaba.fastjson.JSONArray;
import com.yyhome.common.ApiResponse;
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
import com.yyhome.data.po.EmailJobRulePO;
import com.yyhome.data.vo.mail.EmailJobVO;
import com.yyhome.service.email.EmailJobService;
import com.yyhome.service.email.EmailSendService;
import com.yyhome.service.email.EmailValidateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author miluo
 * @date 2019-09-27
 */
@Component
public class EmailJobServiceImpl implements EmailJobService {

    @Resource
    private EmailJobPOMapper emailMapper;

    @Resource
    private EmailJobRulePOMapper ruleMapper;

    @Resource
    private EmailSendService sendService;

    @Override
    public List<EmailJobBO> getEmailJobList(EmailJobVO param) {
        var jobParam = new EmailJobPOExample();
        var cri = jobParam.createCriteria();
        JudgeUtil.notNullSet(param.getUserId(),cri::andCreateUserEqualTo);
        JudgeUtil.notNullSet(param.getId(),cri::andIdEqualTo);
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
                jo.setReceiver(JSONArray.parseArray(job.getReceiver(),String.class));
                JudgeUtil.notNullSet(jo, email -> email.setTypeName(EmailJobTypeEnums.convert(email.getType()).desc()));
                JudgeUtil.notNullSet(jo, email -> email.setRules(ruleMap.get(job.getId())));
                result.add(jo);
            });
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse createEmailJob(EmailJobVO email) {
        // validate rule
        var validateRes = EmailValidateUtil.validateRule(email);
        if (validateRes != null){
            return ApiResponse.fail(validateRes);
        }
        var result = new EmailJobBO();
        var job = BeanTools.copy(email,EmailJobPO.class);
        job.setCreateUser(email.getUserId());
        var ruleList = BeanTools.copyCollection(email.getRules(), EmailJobRulePO.class);
        int jobRes;
        if (job.getId() == null) {
            jobRes = emailMapper.insert(job);
        }else{
            jobRes = emailMapper.updateByPrimaryKeySelective(job);
        }
        if (jobRes == 1){
            result.setId(job.getId());
            var rules = new ArrayList<EmailJobRuleBO>();
            ruleList.forEach(rule -> {
                rule.setEmailId(job.getId());
                rule.setCreateUser(email.getUserId());
                if (rule.getId() == null) {
                    if (ruleMapper.insert(rule) != 1){
                        throw new RuntimeException("insert rule error");
                    }else{
                        var bo = new EmailJobRuleBO();
                        bo.setId(rule.getId());
                        rules.add(bo);
                    }
                }else{
                    if (ruleMapper.updateByPrimaryKeySelective(rule) != 1){
                        throw new RuntimeException("update rule error");
                    }else{
                        var bo = new EmailJobRuleBO();
                        bo.setId(rule.getId());
                        rules.add(bo);
                    }
                }
            });
            result.setRules(rules);
            sendService.reAddMailToWheel(job.getId());
        }
        return ApiResponse.success(result);
    }

    @Override
    public ApiResponse deleteEmailJob(Long emailId) {
        if (emailMapper.deleteByPrimaryKey(emailId) == 1){
            return ApiResponse.success();
        }else{
            return ApiResponse.fail("delete job error");
        }
    }

}
