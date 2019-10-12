package com.yyhome.service.email;

import com.yyhome.common.ApiResponse;
import com.yyhome.data.bo.EmailJobBO;
import com.yyhome.data.vo.mail.EmailJobVO;

import java.util.List;

/**
 * @author miluo
 * @date 2019-09-27
 */
public interface EmailJobService {

    /**
     * 获取邮件任务列表
     * @param param 查询参数
     * @return 列表结果
     */
    List<EmailJobBO> getEmailJobList(EmailJobVO param);

    /**
     * 创建邮件任务
     * @param email 邮件
     * @return 创建结果
     */
    ApiResponse createEmailJob(EmailJobVO email);

    /**
     * 删除邮件任务
     * @param emailId 邮件任务id
     * @return 删除结果
     */
    ApiResponse deleteEmailJob(Long emailId);
}
