package com.yyhome.dao.mapper;

import com.yyhome.data.po.EmailJobRulePO;
import com.yyhome.data.example.EmailJobRulePOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmailJobRulePOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmailJobRulePO record);

    int insertSelective(EmailJobRulePO record);

    List<EmailJobRulePO> selectByExample(EmailJobRulePOExample example);

    EmailJobRulePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmailJobRulePO record);

    int updateByPrimaryKey(EmailJobRulePO record);
}