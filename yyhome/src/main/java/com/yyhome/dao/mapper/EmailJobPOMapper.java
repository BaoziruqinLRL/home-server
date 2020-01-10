package com.yyhome.dao.mapper;

import com.yyhome.data.po.EmailJobPO;
import com.yyhome.data.example.EmailJobPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmailJobPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmailJobPO record);

    int insertSelective(EmailJobPO record);

    List<EmailJobPO> selectByExample(EmailJobPOExample example);

    EmailJobPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmailJobPO record);

    int updateByPrimaryKey(EmailJobPO record);
}