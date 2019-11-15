package com.yyhome.dao.mapper;

import com.yyhome.data.po.JkInfoPO;
import com.yyhome.data.example.JkInfoPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JkInfoPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JkInfoPO record);

    int insertSelective(JkInfoPO record);

    List<JkInfoPO> selectByExample(JkInfoPOExample example);

    JkInfoPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JkInfoPO record);

    int updateByPrimaryKey(JkInfoPO record);
}