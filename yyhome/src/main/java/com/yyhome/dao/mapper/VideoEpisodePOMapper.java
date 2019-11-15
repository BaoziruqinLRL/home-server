package com.yyhome.dao.mapper;

import com.yyhome.data.po.VideoEpisodePO;
import com.yyhome.data.example.VideoEpisodePOExample;
import java.util.List;

public interface VideoEpisodePOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VideoEpisodePO record);

    int insertSelective(VideoEpisodePO record);

    List<VideoEpisodePO> selectByExample(VideoEpisodePOExample example);

    VideoEpisodePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoEpisodePO record);

    int updateByPrimaryKey(VideoEpisodePO record);
}