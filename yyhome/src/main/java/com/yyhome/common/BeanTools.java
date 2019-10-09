package com.yyhome.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        return (D) new Object();
    }

    @SuppressWarnings("unchecked")
    public static <S extends Collection,D> List<D> copyCollection(S source, Class<D> destClass){
        var list = new ArrayList<D>(source.size());
        source.forEach(x -> {
            try {
                var item = destClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(x,item);
                list.add(item);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error(e.getMessage(),e);
            }
        });
        return list;
    }
}
