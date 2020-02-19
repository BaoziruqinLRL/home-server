package com.yyhome.common.annotation.judge;

import com.yyhome.data.enums.JudgeType;

import java.lang.annotation.*;

/**
 * @author miluo
 * @date 2020-01-16
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Judge {

    JudgeType[] value() default JudgeType.NOT_JUDGE;
}
