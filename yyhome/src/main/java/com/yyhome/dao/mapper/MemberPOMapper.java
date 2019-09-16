package com.yyhome.dao.mapper;

import com.yyhome.dao.po.MemberPO;
import com.yyhome.dao.example.MemberPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberPO record);

    int insertSelective(MemberPO record);

    List<MemberPO> selectByExample(MemberPOExample example);

    MemberPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberPO record);

    int updateByPrimaryKey(MemberPO record);
}