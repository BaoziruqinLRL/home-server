package com.yyhome.dao.mapper;

import com.yyhome.dao.po.UserAuthRelationPO;
import com.yyhome.dao.example.UserAuthRelationPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAuthRelationPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAuthRelationPO record);

    int insertSelective(UserAuthRelationPO record);

    List<UserAuthRelationPO> selectByExample(UserAuthRelationPOExample example);

    UserAuthRelationPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAuthRelationPO record);

    int updateByPrimaryKey(UserAuthRelationPO record);
}