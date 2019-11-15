package com.yyhome.dao.mapper;

import com.yyhome.data.po.VideoSeriesPO;
import com.yyhome.data.example.VideoSeriesPOExample;
import java.util.List;

public interface VideoSeriesPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VideoSeriesPO record);

    int insertSelective(VideoSeriesPO record);

    List<VideoSeriesPO> selectByExample(VideoSeriesPOExample example);

    VideoSeriesPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoSeriesPO record);

    int updateByPrimaryKey(VideoSeriesPO record);
}