package com.yyhome.common;

/**
 * @author miluo
 * @date 2019-09-29
 */
@FunctionalInterface
public interface MultiFunction<T,R> {

    public R apply(T... ts);
}
