package com.yyhome.common.annotation.merge.test;

import com.alibaba.fastjson.JSON;
import com.yyhome.common.annotation.merge.MergeUtil;
import lombok.var;

/**
 * @author miluo
 * @date 2019-09-09
 */
public class MergeTestMain {

    public static void main(String[] args) throws IllegalAccessException {
        var sourceMerge = new MergeTest();
        sourceMerge.setAname("A");
        sourceMerge.setBname("B");
        sourceMerge.setCname("C");
        sourceMerge.setDname("D");
        var destMerge = new MergeReceiveTest();
        destMerge.setEname("E");
        MergeUtil.merge(sourceMerge,destMerge);
        System.out.println(JSON.toJSONString(destMerge));
    }
}
