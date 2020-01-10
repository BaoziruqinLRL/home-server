package com.yyhome.data.vo.mail;

import com.yyhome.data.BaseModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author miluo
 * @date 2019-09-29
 */
@Data
public class EmailJobVO extends BaseModel {

    private Long id;

    private String name;

    private String sender;

    private String authCode;

    private Long templateId;

    private Integer type;

    private String typeName;

    private Date lastRunTime;

    private String subject;

    private String context;

    private List<String> receiver;

    private List<EmailJobRuleVO> rules;
}
