package com.yyhome.dao.mapper;

import com.yyhome.data.po.UserPO;
import com.yyhome.data.example.UserPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    List<UserPO> selectByExample(UserPOExample example);

    UserPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKey(UserPO record);
}