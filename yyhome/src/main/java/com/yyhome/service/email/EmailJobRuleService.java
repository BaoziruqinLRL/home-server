package com.yyhome.service.email;

import com.yyhome.data.bo.EmailJobRuleBO;
import com.yyhome.data.vo.mail.EmailJobRuleVO;

/**
 * @author miluo
 * @date 2019-10-08
 */
public interface EmailJobRuleService {

    EmailJobRuleBO createJobRule(EmailJobRuleVO rule);
}
