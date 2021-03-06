package com.yyhome.dao.mapper;

import com.yyhome.data.po.AuthDictionaryPO;
import com.yyhome.data.example.AuthDictionaryPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthDictionaryPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthDictionaryPO record);

    int insertSelective(AuthDictionaryPO record);

    List<AuthDictionaryPO> selectByExample(AuthDictionaryPOExample example);

    AuthDictionaryPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthDictionaryPO record);

    int updateByPrimaryKey(AuthDictionaryPO record);
}