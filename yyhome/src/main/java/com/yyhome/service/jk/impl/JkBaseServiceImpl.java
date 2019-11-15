package com.yyhome.service.jk.impl;

import com.yyhome.common.ApiResponse;
import com.yyhome.common.BeanTools;
import com.yyhome.dao.mapper.JkInfoPOMapper;
import com.yyhome.data.example.JkInfoPOExample;
import com.yyhome.data.po.JkInfoPO;
import com.yyhome.data.vo.jk.JkInfoVO;
import com.yyhome.service.jk.JkBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author miluo
 * @date 2019-11-13
 */
@Component
@Slf4j
public class JkBaseServiceImpl implements JkBaseService {

    @Resource
    private JkInfoPOMapper jkMapper;

    @Override
    public List<JkInfoVO> getJkList(JkInfoVO param) {
        var jkParam = new JkInfoPOExample();
        return BeanTools.copyCollection(jkMapper.selectByExample(jkParam),JkInfoVO.class);
    }

    @Override
    public ApiResponse saveJk(JkInfoVO jkInfo) {
        var res = jkMapper.insert(BeanTools.copy(jkInfo, JkInfoPO.class));
        return res == 1 ? ApiResponse.success() : ApiResponse.fail("save jk error");
    }
}
