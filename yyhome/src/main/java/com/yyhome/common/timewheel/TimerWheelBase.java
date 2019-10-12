package com.yyhome.common.timewheel;

import com.yyhome.data.BaseModel;
import lombok.Data;

/**
 * @author miluo
 * @date 2019-10-11
 */
@Data
public class TimerWheelBase extends BaseModel {

    private Long targetId = System.currentTimeMillis() + System.nanoTime();
}
