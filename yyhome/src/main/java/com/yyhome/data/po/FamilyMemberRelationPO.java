package com.yyhome.data.po;

import java.util.Date;

public class FamilyMemberRelationPO {
    private Long id;

    private Long familyId;

    private Long memberId;

    private Long createUser;

    private Long updateUser;

    private Date createTime;

    private Date updateTime;

    public FamilyMemberRelationPO(Long id, Long familyId, Long memberId, Long createUser, Long updateUser, Date createTime, Date updateTime) {
        this.id = id;
        this.familyId = familyId;
        this.memberId = memberId;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public FamilyMemberRelationPO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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