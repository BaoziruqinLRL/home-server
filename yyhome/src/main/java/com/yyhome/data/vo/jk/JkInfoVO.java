package com.yyhome.data.vo.jk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyhome.common.annotation.judge.Judge;
import com.yyhome.data.BaseModel;
import com.yyhome.data.enums.JudgeType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class JkInfoVO extends BaseModel {

    @Judge(JudgeType.INSERT)
    private String name;

    @Judge(JudgeType.INSERT)
    private String style;

    @Judge(JudgeType.INSERT)
    private String color;

    @Judge(JudgeType.INSERT)
    private BigDecimal price;

    @Judge(JudgeType.INSERT)
    private Byte type;

    private String remark;

    private String previewImg;

    private List<String> fullImg;

    private String objModel;

    private String mtlModel;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date saleTime;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date buyTime;
}