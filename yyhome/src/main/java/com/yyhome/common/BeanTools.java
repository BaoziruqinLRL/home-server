package com.yyhome.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author miluo
 * @date 2019-09-25
 */
@Slf4j
public class BeanTools {

    @SuppressWarnings("unchecked")
    public static <S,D> D copy(S source, Class<D> destClass) {
        try {
            var result = destClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, result);
            return result;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
