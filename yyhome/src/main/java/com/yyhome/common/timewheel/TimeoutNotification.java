package com.yyhome.common.timewheel;

/**
 * 时间片轮转执行器
 * @param <T>
 */
public interface TimeoutNotification<T> {

    /**
     * 通知对象过期.
     *
     * @param t 过期地象.
     * @return 如果大于0,表示将此目标重新以返回值时间增加到环中.否则就真的进行过期.
     */
    long notice(T t);
}
