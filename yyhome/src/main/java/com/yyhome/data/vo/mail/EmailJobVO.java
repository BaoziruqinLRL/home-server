package com.yyhome.data.vo.mail;

import com.yyhome.data.BaseModel;
import lombok.Data;

/**
 * @author miluo
 * @date 2019-09-29
 */
@Data
public class EmailJobVO extends BaseModel {

    private String name;

    private String sender;

    private Integer type;
}
