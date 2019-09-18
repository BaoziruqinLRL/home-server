package com.yyhome.service.user;

import com.yyhome.common.ApiResponse;
import com.yyhome.data.po.UserPO;

/**
 * @author miluo
 * @date 2019-09-16
 */
public interface UserLoginService {

    ApiResponse login(UserPO login);
}
