package com.yyhome.controller.jk;

import com.yyhome.common.annotation.judge.Judge;
import com.yyhome.common.util.ApiResponse;
import com.yyhome.data.enums.JudgeType;
import com.yyhome.data.vo.jk.JkInfoVO;
import com.yyhome.service.jk.JkBaseService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author miluo
 * @date 2019-11-13
 */
@RestController
@RequestMapping(value = "/jk")
public class JkController {

    @Resource
    private JkBaseService jkBaseService;

    @RequestMapping(value = "/list")
    @Judge(JudgeType.QUERY)
    public ApiResponse getList(@RequestBody JkInfoVO param){
        return ApiResponse.success(jkBaseService.getJkList(param));
    }

    @RequestMapping(value = "/save")
    @Judge(JudgeType.INSERT)
    public ApiResponse saveJk(@RequestBody JkInfoVO jkInfo){
        return jkBaseService.saveJk(jkInfo);
    }
}
