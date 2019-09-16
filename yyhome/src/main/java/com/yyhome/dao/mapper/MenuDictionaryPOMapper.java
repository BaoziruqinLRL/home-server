package com.yyhome.dao.mapper;

import com.yyhome.dao.po.MenuDictionaryPO;
import com.yyhome.dao.example.MenuDictionaryPOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDictionaryPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuDictionaryPO record);

    int insertSelective(MenuDictionaryPO record);

    List<MenuDictionaryPO> selectByExampleWithBLOBs(MenuDictionaryPOExample example);

    List<MenuDictionaryPO> selectByExample(MenuDictionaryPOExample example);

    MenuDictionaryPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuDictionaryPO record);

    int updateByPrimaryKeyWithBLOBs(MenuDictionaryPO record);

    int updateByPrimaryKey(MenuDictionaryPO record);
}