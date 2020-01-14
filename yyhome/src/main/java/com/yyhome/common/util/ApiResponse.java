package com.yyhome.common.util;

import lombok.Data;

/**
 * @author miluo
 * @date 2019-09-16
 */
@Data
public class ApiResponse {

    private int code;

    private String message;

    private Object data;

    public ApiResponse(int code){
        this(code,"");
    }
    public ApiResponse(int code,String message){
       this(code,message,null);
    }

    public ApiResponse(int code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse success(){
        return new ApiResponse(200,"success");
    }

    public static ApiResponse fail(String message){
        return new ApiResponse(500,message);
    }

    public static ApiResponse success(Object data){
        return new ApiResponse(200,"success",data);
    }
}
