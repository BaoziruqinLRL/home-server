package com.yyhome.common.annotation.merge.test;

import com.yyhome.common.annotation.merge.*;
import lombok.Data;

/**
 * @author miluo
 * @date 2019-09-09
 */
@Merge
@Data
public class MergeReceiveTest {

    private String aname;

    @Merge(destName = "gname")
    private String ename;

    private String fname;

    private String gname;
}
