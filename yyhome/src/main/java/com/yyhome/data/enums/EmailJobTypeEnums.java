package com.yyhome.data.enums;

/**
 * @author miluo
 * @date 2019-09-27
 */
public enum EmailJobTypeEnums {

    TOPIC(1,"主题邮件"),
    TEMPLATE(2,"模板邮件"),
    NULL(999,"");

    private int type;

    private String desc;

    EmailJobTypeEnums(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public static EmailJobTypeEnums convert(Integer type){
        if (type == null){
            return NULL;
        }
        for (var en : EmailJobTypeEnums.values()){
            if (en.type == type){
                return en;
            }
        }
        return NULL;
    }

    public String desc(){
        return this.desc;
    }
}
