package com.yyhome.data.po;

import java.math.BigDecimal;
import java.util.Date;

public class JkInfoPO {
    private Long id;

    private String name;

    private String style;

    private String color;

    private BigDecimal price;

    private Byte type;

    private String remark;

    private String previewImg;

    private String fullImg;

    private String objModel;

    private String mtlModel;

    private Date saleTime;

    private Date buyTime;

    private Long createUser;

    private Long updateUser;

    private Date createTime;

    private Date updateTime;

    public JkInfoPO(Long id, String name, String style, String color, BigDecimal price, Byte type, String remark, String previewImg, String fullImg, String objModel, String mtlModel, Date saleTime, Date buyTime, Long createUser, Long updateUser, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.color = color;
        this.price = price;
        this.type = type;
        this.remark = remark;
        this.previewImg = previewImg;
        this.fullImg = fullImg;
        this.objModel = objModel;
        this.mtlModel = mtlModel;
        this.saleTime = saleTime;
        this.buyTime = buyTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public JkInfoPO() {
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPreviewImg() {
        return previewImg;
    }

    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg == null ? null : previewImg.trim();
    }

    public String getFullImg() {
        return fullImg;
    }

    public void setFullImg(String fullImg) {
        this.fullImg = fullImg == null ? null : fullImg.trim();
    }

    public String getObjModel() {
        return objModel;
    }

    public void setObjModel(String objModel) {
        this.objModel = objModel == null ? null : objModel.trim();
    }

    public String getMtlModel() {
        return mtlModel;
    }

    public void setMtlModel(String mtlModel) {
        this.mtlModel = mtlModel == null ? null : mtlModel.trim();
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
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