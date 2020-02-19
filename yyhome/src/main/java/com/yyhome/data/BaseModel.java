package com.yyhome.data;

import com.yyhome.common.annotation.judge.Judge;
import com.yyhome.data.enums.JudgeType;
import lombok.Data;

/**
 * @author miluo
 * @date 2019-09-29
 */
@Data
public class BaseModel{

    private Long userId;

    @Judge({JudgeType.UPDATE})
    private Long id;
}
