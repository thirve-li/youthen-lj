// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.Kbn;

/**
 * 商品管理。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjGoods extends AbstractCommonEntity {

    private static final long serialVersionUID = -1393066067391786371L;

    /**
     * ID
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品大类
     */
    private Long bigGoodsTypeId;
    private Kbn bigGoodsType;

    /**
     * 商品小类
     */
    private Long smallGoodsTypeId;
    private Kbn smallGoodsType;

    /**
     * 商家ID
     */
    private Long shopId;
    private LjMerchant shop;

    /**
     * 价格
     */
    private Double price;

    /**
     * 库存数量
     */
    private Long leftCnt;

    /**
     * 商品描述 富文本
     */
    private String remark;

    /**
     * 是否二手商品 0、商品 1、二手商品
     */
    private int isSecond;

    /**
     * 二手商品标题
     */
    private String title;

    /**
     * 有效期（月）
     */
    private int validMonth;

    /**
     * 有效期至
     */
    private Date validDate;

    /**
     * 品牌
     */
    private String brandName;

    /**
     * 生产厂家
     */
    private String factory;

    /**
     * 生产日期
     */
    private String madeDate;

    /**
     * 图片1
     */
    private String image1;

    /**
     * 图片2
     */
    private String image2;

    /**
     * 图片3
     */
    private String image3;

    /**
     * 图片4
     */
    private String image4;

    /**
     * 图片5
     */
    private String image5;

    /**
     * 状态 0:失效 1：有效
     */
    private Integer status;

    /**
     * 创建人id
     */
    private String createrId;

    /**
     * 创建人头像
     */
    private String CreaterImage;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人id
     */
    private String updaterId;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 新旧程度 0:全新 , 1:95成新 , 2:9成新, 3:8成新, 4:8成新以下
     */
    private String faceMark;

    /**
     * 相关评论
     */
    private Set<LjActionComment> comments = new HashSet();

    /**
     * 排他版本号
     */
    private Long versionNo;

    /**
     * getter for id.
     * 
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * setter for id.
     * 
     * @param aId id
     */
    public void setId(final Long aId) {
        this.id = aId;
    }

    /**
     * getter for name.
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * setter for name.
     * 
     * @param aName name
     */
    public void setName(final String aName) {
        this.name = aName;
    }

    /**
     * getter for shopId.
     * 
     * @return shopId
     */
    public Long getShopId() {
        return this.shopId;
    }

    /**
     * setter for shopId.
     * 
     * @param aShopId shopId
     */
    public void setShopId(final Long aShopId) {
        this.shopId = aShopId;
    }

    /**
     * getter for price.
     * 
     * @return price
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * setter for price.
     * 
     * @param aPrice price
     */
    public void setPrice(final Double aPrice) {
        this.price = aPrice;
    }

    /**
     * getter for leftCnt.
     * 
     * @return leftCnt
     */
    public Long getLeftCnt() {
        return this.leftCnt;
    }

    /**
     * setter for leftCnt.
     * 
     * @param aLeftCnt leftCnt
     */
    public void setLeftCnt(final Long aLeftCnt) {
        this.leftCnt = aLeftCnt;
    }

    /**
     * getter for remark.
     * 
     * @return remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * setter for remark.
     * 
     * @param aRemark remark
     */
    public void setRemark(final String aRemark) {
        this.remark = aRemark;
    }

    /**
     * getter for isSecond.
     * 
     * @return isSecond
     */
    public int getIsSecond() {
        return this.isSecond;
    }

    /**
     * setter for isSecond.
     * 
     * @param aIsSecond isSecond
     */
    public void setIsSecond(final int aIsSecond) {
        this.isSecond = aIsSecond;
    }

    /**
     * getter for title.
     * 
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * setter for title.
     * 
     * @param aTitle title
     */
    public void setTitle(final String aTitle) {
        this.title = aTitle;
    }

    /**
     * getter for validMonth.
     * 
     * @return validMonth
     */
    public int getValidMonth() {
        return this.validMonth;
    }

    /**
     * setter for validMonth.
     * 
     * @param aValidMonth validMonth
     */
    public void setValidMonth(final int aValidMonth) {
        this.validMonth = aValidMonth;
    }

    /**
     * getter for validDate.
     * 
     * @return validDate
     */
    public Date getValidDate() {
        return this.validDate;
    }

    /**
     * setter for validDate.
     * 
     * @param aValidDate validDate
     */
    public void setValidDate(final Date aValidDate) {
        this.validDate = aValidDate;
    }

    /**
     * getter for brandName.
     * 
     * @return brandName
     */
    public String getBrandName() {
        return this.brandName;
    }

    /**
     * setter for brandName.
     * 
     * @param aBrandName brandName
     */
    public void setBrandName(final String aBrandName) {
        this.brandName = aBrandName;
    }

    /**
     * getter for factory.
     * 
     * @return factory
     */
    public String getFactory() {
        return this.factory;
    }

    /**
     * setter for factory.
     * 
     * @param aFactory factory
     */
    public void setFactory(final String aFactory) {
        this.factory = aFactory;
    }

    /**
     * getter for madeDate.
     * 
     * @return madeDate
     */
    public String getMadeDate() {
        return this.madeDate;
    }

    /**
     * setter for madeDate.
     * 
     * @param aMadeDate madeDate
     */
    public void setMadeDate(final String aMadeDate) {
        this.madeDate = aMadeDate;
    }

    /**
     * getter for bigGoodsTypeId.
     * 
     * @return bigGoodsTypeId
     */
    public Long getBigGoodsTypeId() {
        return this.bigGoodsTypeId;
    }

    /**
     * setter for bigGoodsTypeId.
     * 
     * @param aBigGoodsTypeId bigGoodsTypeId
     */
    public void setBigGoodsTypeId(final Long aBigGoodsTypeId) {
        this.bigGoodsTypeId = aBigGoodsTypeId;
    }

    /**
     * getter for bigGoodsType.
     * 
     * @return bigGoodsType
     */
    public Kbn getBigGoodsType() {
        return this.bigGoodsType;
    }

    /**
     * setter for bigGoodsType.
     * 
     * @param aBigGoodsType bigGoodsType
     */
    public void setBigGoodsType(final Kbn aBigGoodsType) {
        this.bigGoodsType = aBigGoodsType;
    }

    /**
     * getter for smallGoodsTypeId.
     * 
     * @return smallGoodsTypeId
     */
    public Long getSmallGoodsTypeId() {
        return this.smallGoodsTypeId;
    }

    /**
     * setter for smallGoodsTypeId.
     * 
     * @param aSmallGoodsTypeId smallGoodsTypeId
     */
    public void setSmallGoodsTypeId(final Long aSmallGoodsTypeId) {
        this.smallGoodsTypeId = aSmallGoodsTypeId;
    }

    /**
     * getter for smallGoodsType.
     * 
     * @return smallGoodsType
     */
    public Kbn getSmallGoodsType() {
        return this.smallGoodsType;
    }

    /**
     * setter for smallGoodsType.
     * 
     * @param aSmallGoodsType smallGoodsType
     */
    public void setSmallGoodsType(final Kbn aSmallGoodsType) {
        this.smallGoodsType = aSmallGoodsType;
    }

    /**
     * getter for image1.
     * 
     * @return image1
     */
    public String getImage1() {
        return this.image1;
    }

    /**
     * setter for image1.
     * 
     * @param aImage1 image1
     */
    public void setImage1(final String aImage1) {
        this.image1 = aImage1;
    }

    /**
     * getter for image2.
     * 
     * @return image2
     */
    public String getImage2() {
        return this.image2;
    }

    /**
     * setter for image2.
     * 
     * @param aImage2 image2
     */
    public void setImage2(final String aImage2) {
        this.image2 = aImage2;
    }

    /**
     * getter for image3.
     * 
     * @return image3
     */
    public String getImage3() {
        return this.image3;
    }

    /**
     * setter for image3.
     * 
     * @param aImage3 image3
     */
    public void setImage3(final String aImage3) {
        this.image3 = aImage3;
    }

    /**
     * getter for image4.
     * 
     * @return image4
     */
    public String getImage4() {
        return this.image4;
    }

    /**
     * setter for image4.
     * 
     * @param aImage4 image4
     */
    public void setImage4(final String aImage4) {
        this.image4 = aImage4;
    }

    /**
     * getter for image5.
     * 
     * @return image5
     */
    public String getImage5() {
        return this.image5;
    }

    /**
     * setter for image5.
     * 
     * @param aImage5 image5
     */
    public void setImage5(final String aImage5) {
        this.image5 = aImage5;
    }

    /**
     * getter for createrId.
     * 
     * @return createrId
     */
    public String getCreaterId() {
        return this.createrId;
    }

    /**
     * setter for createrId.
     * 
     * @param aCreaterId createrId
     */
    public void setCreaterId(final String aCreaterId) {
        this.createrId = aCreaterId;
    }

    /**
     * getter for createTime.
     * 
     * @return createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * setter for createTime.
     * 
     * @param aCreateTime createTime
     */
    public void setCreateTime(final Date aCreateTime) {
        this.createTime = aCreateTime;
    }

    /**
     * getter for updaterId.
     * 
     * @return updaterId
     */
    public String getUpdaterId() {
        return this.updaterId;
    }

    /**
     * setter for updaterId.
     * 
     * @param aUpdaterId updaterId
     */
    public void setUpdaterId(final String aUpdaterId) {
        this.updaterId = aUpdaterId;
    }

    /**
     * getter for updateTime.
     * 
     * @return updateTime
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * setter for updateTime.
     * 
     * @param aUpdateTime updateTime
     */
    public void setUpdateTime(final String aUpdateTime) {
        this.updateTime = aUpdateTime;
    }

    /**
     * getter for versionNo.
     * 
     * @return versionNo
     */
    @Override
    public Long getVersionNo() {
        return this.versionNo;
    }

    /**
     * setter for versionNo.
     * 
     * @param aVersionNo versionNo
     */
    @Override
    public void setVersionNo(final Long aVersionNo) {
        this.versionNo = aVersionNo;
    }

    /**
     * getter for shop.
     * 
     * @return shop
     */
    public LjMerchant getShop() {
        return this.shop;
    }

    /**
     * setter for shop.
     * 
     * @param aShop shop
     */
    public void setShop(final LjMerchant aShop) {
        this.shop = aShop;
    }

    /**
     * getter for status.
     * 
     * @return status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * setter for status.
     * 
     * @param aStatus status
     */
    public void setStatus(final Integer aStatus) {
        this.status = aStatus;
    }

    /**
     * getter for createrImage.
     * 
     * @return createrImage
     */
    public String getCreaterImage() {
        return this.CreaterImage;
    }

    /**
     * setter for createrImage.
     * 
     * @param aCreaterImage createrImage
     */
    public void setCreaterImage(final String aCreaterImage) {
        this.CreaterImage = aCreaterImage;
    }

    /**
     * getter for comments.
     * 
     * @return comments
     */
    public Set<LjActionComment> getComments() {
        return this.comments;
    }

    /**
     * setter for comments.
     * 
     * @param aComments comments
     */
    public void setComments(final Set<LjActionComment> aComments) {
        this.comments = aComments;
    }

    /**
     * getter for faceMark.
     * 
     * @return faceMark
     */
    public String getFaceMark() {
        return this.faceMark;
    }

    /**
     * setter for faceMark.
     * 
     * @param aFaceMark faceMark
     */
    public void setFaceMark(final String aFaceMark) {
        this.faceMark = aFaceMark;
    }

}
