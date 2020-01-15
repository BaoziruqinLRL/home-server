package com.yyhome.data.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author miluo
 * @date 2020-01-15
 */
@Data
public class TestExcelBO {
    @ExcelProperty(value = "date", index = 0)
    private String date;

    @ExcelProperty(value = "user_id", index = 1)
    private String userId;
}
