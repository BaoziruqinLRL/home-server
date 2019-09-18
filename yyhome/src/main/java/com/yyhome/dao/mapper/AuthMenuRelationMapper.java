package com.yyhome.dao.mapper;

import com.yyhome.data.po.AuthMenuRelation;
import com.yyhome.data.example.AuthMenuRelationExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMenuRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthMenuRelation record);

    int insertSelective(AuthMenuRelation record);

    List<AuthMenuRelation> selectByExample(AuthMenuRelationExample example);

    AuthMenuRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthMenuRelation record);

    int updateByPrimaryKey(AuthMenuRelation record);
}