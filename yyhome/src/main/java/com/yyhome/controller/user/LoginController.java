package com.yyhome.controller.user;

import com.yyhome.common.ApiResponse;
import com.yyhome.data.po.UserPO;
import com.yyhome.service.user.UserLoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author miluo
 * @date 2019-09-16
 */
@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Resource
    private UserLoginService loginService;

    @RequestMapping(value = "/login")
    public ApiResponse login(@RequestBody UserPO login){
        return loginService.login(login);
    }
}
