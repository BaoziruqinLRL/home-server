package com.yyhome.data.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class EmailJobPO {
    private Long id;

    private String name;

    private String sender;

    private String authCode;

    private Long templateId;

    private Date lastRunTime;

    private Long createUser;

    private Long updateUser;

    private Date createTime;

    private Date updateTime;

    private String subject;

    private String context;

    private Integer type;

    public EmailJobPO(){

    }

    public EmailJobPO(Long id, String name, String sender, String authCode, Long templateId, Date lastRunTime, Long createUser, Long updateUser, Date createTime, Date updateTime, String subject, String context, Integer type) {
        this.id = id;
        this.name = name;
        this.sender = sender;
        this.authCode = authCode;
        this.templateId = templateId;
        this.lastRunTime = lastRunTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.subject = subject;
        this.context = context;
        this.type = type;
    }
}