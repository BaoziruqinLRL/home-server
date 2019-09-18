package com.yyhome.controller.user;

import com.yyhome.common.ApiResponse;
import com.yyhome.service.user.UserBaseService;
import lombok.var;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author miluo
 * @date 2019-09-16
 */
@RestController
@RequestMapping(value = "/user-base")
public class UserBaseController {

    @Resource
    private UserBaseService userBaseService;

    @RequestMapping(value = "/members")
    public ApiResponse getMembers(Long userId){
        var result = userBaseService.getMemberList(userId);
        return ApiResponse.success(result);
    }

    @RequestMapping(value = "/menus")
    public ApiResponse getMenus(Long userId){
        var result = userBaseService.getMenus(userId);
        return ApiResponse.success(result);
    }
}
