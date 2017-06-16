// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.dto;

import java.util.Date;
import com.youthen.framework.common.annotation.Dto;
import com.youthen.master.persistence.entity.Kbn;
import com.youthen.master.service.dto.MasterEntryDto;

/**
 * 商家表。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Dto
public class LjMerchantDto extends MasterEntryDto {

    private static final long serialVersionUID = -5365796280807713010L;

    /**
     * ID
     */
    private Long id;

    /**
     * 所属大类型
     */
    private Long bigTypeId;
    private Kbn bigType;

    /**
     * 所属小类型
     */
    private Long smallTypeId;
    private Kbn smallType;

    /**
     * 公司名称
     */
    private String shopName;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 服务特色
     */
    private String goodAt;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 产品说明
     */
    private String productSpec;

    /**
     * 店铺说明
     */
    private String shopSpec;

    /**
     * 价格范围
     */
    private String priceRange;

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
     * 创建人ID
     */
    private String createrId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人ID
     */
    private String updaterId;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 排他版本号
     */
    private Long versionNo;

    /**
     * 是否置顶 0:否 1：置顶
     */
    private Integer isTop;

    /**
     * getter for id.
     * 
     * @return id
     */
    @Override
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
     * getter for bigTypeId.
     * 
     * @return bigTypeId
     */
    public Long getBigTypeId() {
        return this.bigTypeId;
    }

    /**
     * setter for bigTypeId.
     * 
     * @param aBigTypeId bigTypeId
     */
    public void setBigTypeId(final Long aBigTypeId) {
        this.bigTypeId = aBigTypeId;
    }

    /**
     * getter for bigType.
     * 
     * @return bigType
     */
    public Kbn getBigType() {
        return this.bigType;
    }

    /**
     * setter for bigType.
     * 
     * @param aBigType bigType
     */
    public void setBigType(final Kbn aBigType) {
        this.bigType = aBigType;
    }

    /**
     * getter for smallTypeId.
     * 
     * @return smallTypeId
     */
    public Long getSmallTypeId() {
        return this.smallTypeId;
    }

    /**
     * setter for smallTypeId.
     * 
     * @param aSmallTypeId smallTypeId
     */
    public void setSmallTypeId(final Long aSmallTypeId) {
        this.smallTypeId = aSmallTypeId;
    }

    /**
     * getter for smallType.
     * 
     * @return smallType
     */
    public Kbn getSmallType() {
        return this.smallType;
    }

    /**
     * setter for smallType.
     * 
     * @param aSmallType smallType
     */
    public void setSmallType(final Kbn aSmallType) {
        this.smallType = aSmallType;
    }

    /**
     * getter for shopName.
     * 
     * @return shopName
     */
    public String getShopName() {
        return this.shopName;
    }

    /**
     * setter for shopName.
     * 
     * @param aShopName shopName
     */
    public void setShopName(final String aShopName) {
        this.shopName = aShopName;
    }

    /**
     * getter for companyName.
     * 
     * @return companyName
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * setter for companyName.
     * 
     * @param aCompanyName companyName
     */
    public void setCompanyName(final String aCompanyName) {
        this.companyName = aCompanyName;
    }

    /**
     * getter for address.
     * 
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * setter for address.
     * 
     * @param aAddress address
     */
    public void setAddress(final String aAddress) {
        this.address = aAddress;
    }

    /**
     * getter for tel.
     * 
     * @return tel
     */
    public String getTel() {
        return this.tel;
    }

    /**
     * setter for tel.
     * 
     * @param aTel tel
     */
    public void setTel(final String aTel) {
        this.tel = aTel;
    }

    /**
     * getter for goodAt.
     * 
     * @return goodAt
     */
    public String getGoodAt() {
        return this.goodAt;
    }

    /**
     * setter for goodAt.
     * 
     * @param aGoodAt goodAt
     */
    public void setGoodAt(final String aGoodAt) {
        this.goodAt = aGoodAt;
    }

    /**
     * getter for serviceType.
     * 
     * @return serviceType
     */
    public String getServiceType() {
        return this.serviceType;
    }

    /**
     * setter for serviceType.
     * 
     * @param aServiceType serviceType
     */
    public void setServiceType(final String aServiceType) {
        this.serviceType = aServiceType;
    }

    /**
     * getter for productSpec.
     * 
     * @return productSpec
     */
    public String getProductSpec() {
        return this.productSpec;
    }

    /**
     * setter for productSpec.
     * 
     * @param aProductSpec productSpec
     */
    public void setProductSpec(final String aProductSpec) {
        this.productSpec = aProductSpec;
    }

    /**
     * getter for shopSpec.
     * 
     * @return shopSpec
     */
    public String getShopSpec() {
        return this.shopSpec;
    }

    /**
     * setter for shopSpec.
     * 
     * @param aShopSpec shopSpec
     */
    public void setShopSpec(final String aShopSpec) {
        this.shopSpec = aShopSpec;
    }

    /**
     * getter for priceRange.
     * 
     * @return priceRange
     */
    public String getPriceRange() {
        return this.priceRange;
    }

    /**
     * setter for priceRange.
     * 
     * @param aPriceRange priceRange
     */
    public void setPriceRange(final String aPriceRange) {
        this.priceRange = aPriceRange;
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
     * setter for status.
     * 
     * @param aStatus status
     */
    public void setStatus(final Integer aStatus) {
        this.status = aStatus;
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
     * getter for isTop.
     * 
     * @return isTop
     */
    public Integer getIsTop() {
        return this.isTop;
    }

    /**
     * setter for isTop.
     * 
     * @param aIsTop isTop
     */
    public void setIsTop(final Integer aIsTop) {
        this.isTop = aIsTop;
    }

}
