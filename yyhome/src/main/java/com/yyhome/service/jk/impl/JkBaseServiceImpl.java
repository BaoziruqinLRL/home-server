package com.yyhome.service.jk.impl;

import com.yyhome.common.util.ApiResponse;
import com.yyhome.common.util.BeanTools;
import com.yyhome.dao.mapper.JkInfoPOMapper;
import com.yyhome.data.example.JkInfoPOExample;
import com.yyhome.data.po.JkInfoPO;
import com.yyhome.data.vo.jk.JkInfoVO;
import com.yyhome.service.jk.JkBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
        var cri = jkParam.createCriteria();
        if (StringUtils.isNotEmpty(param.getColor())){
            cri.andColorEqualTo(param.getColor());
        }
        if (param.getName() != null){
            cri.andNameLike(String.join("","%",param.getName(),"%"));
        }
        if (StringUtils.isNotEmpty(param.getStyle())){
            cri.andStyleEqualTo(param.getStyle());
        }
        return BeanTools.copyCollection(jkMapper.selectByExample(jkParam),JkInfoVO.class);
    }

    @Override
    public ApiResponse saveJk(JkInfoVO jkInfo) {
        int res;
        if (jkInfo.getId() == null) {
            res = jkMapper.insert(BeanTools.copy(jkInfo, JkInfoPO.class));
        }else{
            var po = BeanTools.copy(jkInfo,JkInfoPO.class);
            res = jkMapper.updateByPrimaryKey(po);
        }
        return res == 1 ? ApiResponse.success() : ApiResponse.fail("save jk error");
    }
}
