package com.yyhome.common.annotation.merge;

import java.util.HashMap;

/**
 * @author miluo
 * @date 2019-09-09
 */
public class MergeUtil {

    private static final Class MERGE_ANNOTATION = Merge.class;

    public static <S,D> void merge(S source, D dest) throws IllegalAccessException {
        var sourceClass = source.getClass();
        if (!sourceClass.isAnnotationPresent(MERGE_ANNOTATION)){
            throw new RuntimeException("Source is not Merge class");
        }
        var destClass = dest.getClass();
        if (!destClass.isAnnotationPresent(MERGE_ANNOTATION)){
            throw new RuntimeException("Dest is not Merge class");
        }
        var sourceMap = new HashMap<String, Object>();
        var sourceFields = sourceClass.getDeclaredFields();
        for (var field : sourceFields){
            if (field.isAnnotationPresent(MERGE_ANNOTATION)){
                var fieldMerge = (Merge) field.getAnnotation(MERGE_ANNOTATION);
                if (!fieldMerge.noMerge()){
                    field.setAccessible(true);
                    sourceMap.put(fieldMerge.sourceName(),field.get(source));
                }
            }else{
                field.setAccessible(true);
                sourceMap.put(field.getName(), field.get(source));
            }
        }
        var destFields = destClass.getDeclaredFields();
        for (var field : destFields){
            if (field.isAnnotationPresent(MERGE_ANNOTATION)){
                var fieldMerge = (Merge) field.getAnnotation(MERGE_ANNOTATION);
                if (!fieldMerge.noMerge()){
                    field.setAccessible(true);
                    field.set(dest,sourceMap.get(fieldMerge.destName()));
                }
            }else{
                field.setAccessible(true);
                field.set(dest,sourceMap.get(field.getName()));
            }
        }
    }
}
