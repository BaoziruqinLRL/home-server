package com.yyhome.common.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * @author miluo
 * @date 2019-09-29
 */
public class JudgeUtil {

    public static <T> void notNullSet(T value, Consumer<T> consumer){
        if (value != null){
            consumer.accept(value);
        }
    }

    public static <T extends Collection> void notEmptySet(T value, Consumer<T> consumer){
        if (CollectionUtils.isNotEmpty(value)){
            consumer.accept(value);
        }
    }
}
