package com.yyhome.data.enums;

/**
 * @author miluo
 * @date 2020-01-16
 */
public enum JudgeType {

    INSERT("",0),
    UPDATE("",1),
    QUERY("",2),
    DELETE("",3),
    NOT_JUDGE("",9999);

    JudgeType(String desc, int type){
        this.desc = desc;
        this.type = type;
    }

    private int type;

    private String desc;

    public int type(){
        return this.type;
    }
}
