package com.yyhome.data.vo.jk;

import com.yyhome.data.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class JkInfoVO extends BaseModel {
    private Long id;

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

    private Date saleTime;

    private Date buyTime;
}