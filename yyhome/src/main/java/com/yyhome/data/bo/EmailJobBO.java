package com.yyhome.data.bo;

import com.yyhome.common.timewheel.TimerWheelBase;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author miluo
 * @date 2019-09-27
 */
@Data
public class EmailJobBO extends TimerWheelBase {

    private String name;

    private String sender;

    private String authCode;

    private Long templateId;

    private Integer type;

    private String typeName;

    private Date lastRunTime;

    private String subject;

    private String context;

    private List<EmailJobRuleBO> rules;
}
