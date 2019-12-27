package com.yyhome.leetcode;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * @author miluo
 * @date 2019-11-18
 */
@Data
public class CrfExcel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String id;

    @ExcelProperty(index = 1)
    private String context;

    @ExcelProperty(index = 2)
    private Integer transferNum;

    @ExcelProperty(index = 3)
    private Integer commentNum;

    @ExcelProperty(index = 4)
    private Integer niceNum;

    @ExcelProperty(index = 5)
    private String comment;

    @ExcelProperty(index = 6)
    private String author;

    @ExcelProperty(index = 7)
    private String time;
}
