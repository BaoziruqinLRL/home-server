package com.yyhome.service.jk;

import com.yyhome.common.ApiResponse;
import com.yyhome.data.vo.jk.JkInfoVO;

import java.util.List;

/**
 * @author miluo
 * @date 2019-11-13
 */
public interface JkBaseService {

    List<JkInfoVO> getJkList(JkInfoVO param);

    ApiResponse saveJk(JkInfoVO jkInfo);
}
