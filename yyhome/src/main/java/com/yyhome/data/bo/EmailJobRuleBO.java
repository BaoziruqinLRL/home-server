package com.yyhome.data.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author miluo
 * @date 2019-09-27
 */
@Data
public class EmailJobRuleBO {

    private Long id;

    private Long emailId;

    @JsonFormat(pattern="HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern="HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="HH:mm:ss")
    private Date endTime;

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private Integer interval;

    private Integer ruleSort;
}
