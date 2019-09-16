package com.yyhome.dao.mapper;

import com.yyhome.dao.po.FamilyMemberRelationPO;
import com.yyhome.dao.example.FamilyMemberRelationPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyMemberRelationPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FamilyMemberRelationPO record);

    int insertSelective(FamilyMemberRelationPO record);

    List<FamilyMemberRelationPO> selectByExample(FamilyMemberRelationPOExample example);

    FamilyMemberRelationPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FamilyMemberRelationPO record);

    int updateByPrimaryKey(FamilyMemberRelationPO record);
}