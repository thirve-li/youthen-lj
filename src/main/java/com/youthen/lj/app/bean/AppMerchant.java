// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

import com.youthen.master.persistence.entity.Kbn;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class AppMerchant {

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
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String tel;

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

}
