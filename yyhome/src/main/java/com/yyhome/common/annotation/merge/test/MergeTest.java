package com.yyhome.common.annotation.merge.test;

import com.yyhome.common.annotation.merge.Merge;
import lombok.Data;

/**
 * @author miluo
 * @date 2019-09-09
 */
@Merge
@Data
public class MergeTest {

    private String aname;

    @Merge(sourceName = "ename")
    private String bname;

    @Merge(sourceName = "fname")
    private String cname;

    @Merge(sourceName = "gname")
    private String dname;
}
