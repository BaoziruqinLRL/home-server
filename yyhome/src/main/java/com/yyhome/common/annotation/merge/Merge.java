package com.yyhome.common.annotation.merge;

import java.lang.annotation.*;

/**
 * @author miluo
 * @date 2019-09-09
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Merge {

    String sourceName() default "";

    String destName() default "";

    boolean noMerge() default false;
}
