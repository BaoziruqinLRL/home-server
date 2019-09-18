package com.yyhome.dao.mapper;

import com.yyhome.data.po.UserFamilyRelationPO;
import com.yyhome.data.example.UserFamilyRelationPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserFamilyRelationPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFamilyRelationPO record);

    int insertSelective(UserFamilyRelationPO record);

    List<UserFamilyRelationPO> selectByExample(UserFamilyRelationPOExample example);

    UserFamilyRelationPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFamilyRelationPO record);

    int updateByPrimaryKey(UserFamilyRelationPO record);
}