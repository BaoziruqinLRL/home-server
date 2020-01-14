package com.yyhome.common.util;

/**
 * @author miluo
 * @date 2019-09-29
 */
@FunctionalInterface
public interface MultiFunction<T,R> {

    public R apply(T... ts);
}
