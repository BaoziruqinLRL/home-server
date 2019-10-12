package com.yyhome.service.email;

import com.yyhome.common.ApiResponse;
import com.yyhome.data.vo.mail.EmailJobRuleVO;

/**
 * @author miluo
 * @date 2019-10-08
 */
public interface EmailJobRuleService {

    ApiResponse createJobRule(EmailJobRuleVO rule);

    ApiResponse deleteJobRule(EmailJobRuleVO rule);
}
