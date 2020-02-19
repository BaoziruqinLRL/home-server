package com.yyhome.common.annotation.judge;

import com.alibaba.excel.util.CollectionUtils;
import com.yyhome.data.exception.JudgeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author miluo
 * @date 2020-01-16
 */
@Aspect
@Component
@Slf4j
public class JudgeAspect {

    @Before("execution(public * *(..)) && @annotation(annotation)")
    public void around(JoinPoint joinPoint, Judge annotation) throws Throwable {
        // 此处进入到方法前
        log.info("start judge aspect");
        var levels = Arrays.asList(annotation.value());
        var params = joinPoint.getArgs();
        for (var p : params){
            var fields = p.getClass().getDeclaredFields();
            for (var field : fields){
                field.setAccessible(true);
                if (field.isAnnotationPresent(Judge.class)) {
                    var fieldLevel = Arrays.asList(field.getAnnotation(Judge.class).value());
                    // 若元素类型与接口类型不匹配，则不执行
                    var contains = levels.stream().anyMatch(fieldLevel::contains);
                    if (!contains) {
                        continue;
                    }
                    try {
                        var value = field.get(p);
                        if (value == null) {
                            throw new JudgeException(String.join("", field.getName(), " is null"));
                        } else if (value instanceof List && CollectionUtils.isEmpty((List) value)) {
                            throw new JudgeException(String.join("", field.getName(), " is empty (List is empty)"));
                        } else if (value instanceof Map && ((Map) value).size() == 0) {
                            throw new JudgeException(String.join("", field.getName(), " is empty (Map size is 0)"));
                        }
                    } catch (IllegalAccessException e) {
                        throw new JudgeException(e);
                    }
                }
            }
        }
    }
}
