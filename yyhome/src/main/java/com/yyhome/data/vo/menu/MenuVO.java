package com.yyhome.data.vo.menu;

import com.yyhome.data.po.MenuDictionaryPO;
import lombok.Data;

import java.util.List;

/**
 * @author miluo
 * @date 2019-09-17
 */
@Data
public class MenuVO {

    private MenuDictionaryPO menu;

    private List<MenuDictionaryPO> childMenus;
}
