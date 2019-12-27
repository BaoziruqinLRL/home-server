package com.yyhome.data.vo.jk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyhome.data.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class JkInfoVO extends BaseModel {

    private String name;

    private String style;

    private String color;

    private BigDecimal price;

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