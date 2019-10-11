package com.yyhome.service.email;

import com.yyhome.common.ApiResponse;
import com.yyhome.data.bo.EmailJobBO;
import com.yyhome.data.bo.MailBO;

/**
 * @author miluo
 * @date 2019-09-24
 */
public interface EmailSendService {

    ApiResponse sendTextMail(EmailJobBO mail);

    ApiResponse sendAttachmentsMail(MailBO mail);

    void reAddMailToWheel(Long emailId);
}
