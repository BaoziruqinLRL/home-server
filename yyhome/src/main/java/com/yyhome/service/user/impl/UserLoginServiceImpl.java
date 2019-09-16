package com.yyhome.service.user.impl;

import com.yyhome.common.ApiResponse;
import com.yyhome.dao.po.UserPO;
import com.yyhome.service.user.UserLoginService;
import org.springframework.stereotype.Component;

/**
 * @author miluo
 * @date 2019-09-16
 */
@Component
public class UserLoginServiceImpl implements UserLoginService {

    @Override
    public ApiResponse login(UserPO login) {
        login.setId(10000L);
        return ApiResponse.success(login);
    }
}
