package com.yyhome.data.po;

import lombok.Data;

import java.util.Date;

@Data
public class EmailJobRulePO {
    private Long id;

    private Long emailId;

    private Date startTime;

    private Date endTime;

    private Date startDate;

    private Date endDate;

    private Integer interval;

    private Integer ruleSort;

    private Long createUser;

    private Long updateUser;

    private Date createTime;

    private Date updateTime;

    public EmailJobRulePO() {
    }

    public EmailJobRulePO(Long id, Long emailId, Date startTime, Date endTime, Date startDate, Date endDate, Integer interval, Integer ruleSort, Long createUser, Long updateUser, Date createTime, Date updateTime) {
        this.id = id;
        this.emailId = emailId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interval = interval;
        this.ruleSort = ruleSort;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}