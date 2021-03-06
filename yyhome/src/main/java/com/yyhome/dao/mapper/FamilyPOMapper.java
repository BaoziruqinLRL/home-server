package com.yyhome.dao.mapper;

import com.yyhome.data.po.FamilyPO;
import com.yyhome.data.example.FamilyPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FamilyPO record);

    int insertSelective(FamilyPO record);

    List<FamilyPO> selectByExample(FamilyPOExample example);

    FamilyPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FamilyPO record);

    int updateByPrimaryKey(FamilyPO record);
}