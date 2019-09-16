package com.yyhome.service.user;

import com.yyhome.dao.po.MemberPO;

import java.util.List;

/**
 * @author miluo
 * @date 2019-09-16
 */
public interface UserBaseService {

    List<MemberPO> getMemberList(Long userId);

}
