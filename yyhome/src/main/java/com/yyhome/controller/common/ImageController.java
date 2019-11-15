package com.yyhome.controller.common;

import com.yyhome.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author miluo
 * @date 2019-11-14
 */
@RestController
@RequestMapping(value = "/common/image")
@Slf4j
public class ImageController {

    @Value("${common.image.jkUploadPath}")
    private String jkUploadPath;

    @RequestMapping(value = "/upload")
    public ApiResponse imageUpload(@RequestParam MultipartFile file){
        var newPath = jkUploadPath + file.getOriginalFilename();
        var newFile = new File(newPath);
        try{
            file.transferTo(newFile);
        }catch (Exception e){
            log.error("upload image error",e);
            return ApiResponse.fail("upload image error");
        }
        return ApiResponse.success();
    }
}
