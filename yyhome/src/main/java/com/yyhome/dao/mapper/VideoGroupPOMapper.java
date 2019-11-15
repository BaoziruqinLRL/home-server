package com.yyhome.dao.mapper;

import com.yyhome.data.po.VideoGroupPO;
import com.yyhome.data.example.VideoGroupPOExample;
import java.util.List;

public interface VideoGroupPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VideoGroupPO record);

    int insertSelective(VideoGroupPO record);

    List<VideoGroupPO> selectByExample(VideoGroupPOExample example);

    VideoGroupPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoGroupPO record);

    int updateByPrimaryKey(VideoGroupPO record);
}