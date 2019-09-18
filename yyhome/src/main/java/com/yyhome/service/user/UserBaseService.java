package com.yyhome.service.user;

import com.yyhome.data.po.MemberPO;
import com.yyhome.data.vo.menu.MenuVO;

import java.util.List;

/**
 * @author miluo
 * @date 2019-09-16
 */
public interface UserBaseService {

    List<MemberPO> getMemberList(Long userId);

    List<MenuVO> getMenus(Long userId);

}
