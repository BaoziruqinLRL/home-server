package com.yyhome.data.exception;

import lombok.Data;

/**
 * @author miluo
 * @date 2020-01-16
 */
@Data
public class JudgeException extends Exception{

    public JudgeException(String message){
        super(message);
    }

    public JudgeException(Throwable t){
        super(t);
    }

}
