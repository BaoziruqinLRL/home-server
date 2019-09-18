package com.yyhome.data.po;

import java.util.Date;

public class FamilyPO {
    private Long id;

    private String name;

    private Long createUser;

    private Long updateUser;

    private Date createTime;

    private Date updateTime;

    public FamilyPO(Long id, String name, Long createUser, Long updateUser, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public FamilyPO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}