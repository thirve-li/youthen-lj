// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.Kbn;

/**
 * 房屋信息。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjRoomInfo extends AbstractCommonEntity {

    private static final long serialVersionUID = -1393066067391786371L;

    /**
     * ID
     */
    private Long id;

    /**
     * kbnId
     */
    private Integer kbnId;

    /**
     * kbn对象
     */
    private Kbn kbn;

    /**
     * 单元代码
     */
    private String code;

    /**
     * 单元名称
     */
    private String name;

    /**
     * 楼号
     */
    private Integer buildingNo;

    /**
     * 单元号
     */
    private Integer unitNo;

    /**
     * 房间号
     */
    private Integer roomNo;

    /**
     * 建筑面积
     */
    private double measureOfArea;

    /**
     * 使用面积
     */
    private double measureOfLive;

    /**
     * 物业费单价
     */
    private double priceSquareMeter;

    /**
     * 应交物业费
     */
    private double totalPrice;

    /**
     * 备注
     */
    private String mark;

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
     * getter for code.
     * 
     * @return code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * setter for code.
     * 
     * @param aCode code
     */
    public void setCode(final String aCode) {
        this.code = aCode;
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
     * getter for buildingNo.
     * 
     * @return buildingNo
     */
    public Integer getBuildingNo() {
        return this.buildingNo;
    }

    /**
     * setter for buildingNo.
     * 
     * @param aBuildingNo buildingNo
     */
    public void setBuildingNo(final Integer aBuildingNo) {
        this.buildingNo = aBuildingNo;
    }

    /**
     * getter for unitNo.
     * 
     * @return unitNo
     */
    public Integer getUnitNo() {
        return this.unitNo;
    }

    /**
     * setter for unitNo.
     * 
     * @param aUnitNo unitNo
     */
    public void setUnitNo(final Integer aUnitNo) {
        this.unitNo = aUnitNo;
    }

    /**
     * getter for roomNo.
     * 
     * @return roomNo
     */
    public Integer getRoomNo() {
        return this.roomNo;
    }

    /**
     * setter for roomNo.
     * 
     * @param aRoomNo roomNo
     */
    public void setRoomNo(final Integer aRoomNo) {
        this.roomNo = aRoomNo;
    }

    /**
     * getter for measureOfArea.
     * 
     * @return measureOfArea
     */
    public double getMeasureOfArea() {
        return this.measureOfArea;
    }

    /**
     * setter for measureOfArea.
     * 
     * @param aMeasureOfArea measureOfArea
     */
    public void setMeasureOfArea(final double aMeasureOfArea) {
        this.measureOfArea = aMeasureOfArea;
    }

    /**
     * getter for measureOfLive.
     * 
     * @return measureOfLive
     */
    public double getMeasureOfLive() {
        return this.measureOfLive;
    }

    /**
     * setter for measureOfLive.
     * 
     * @param aMeasureOfLive measureOfLive
     */
    public void setMeasureOfLive(final double aMeasureOfLive) {
        this.measureOfLive = aMeasureOfLive;
    }

    /**
     * getter for priceSquareMeter.
     * 
     * @return priceSquareMeter
     */
    public double getPriceSquareMeter() {
        return this.priceSquareMeter;
    }

    /**
     * setter for priceSquareMeter.
     * 
     * @param aPriceSquareMeter priceSquareMeter
     */
    public void setPriceSquareMeter(final double aPriceSquareMeter) {
        this.priceSquareMeter = aPriceSquareMeter;
    }

    /**
     * getter for totalPrice.
     * 
     * @return totalPrice
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * setter for totalPrice.
     * 
     * @param aTotalPrice totalPrice
     */
    public void setTotalPrice(final double aTotalPrice) {
        this.totalPrice = aTotalPrice;
    }

    /**
     * getter for kbnId.
     * 
     * @return kbnId
     */
    public Integer getKbnId() {
        return this.kbnId;
    }

    /**
     * setter for kbnId.
     * 
     * @param aKbnId kbnId
     */
    public void setKbnId(final Integer aKbnId) {
        this.kbnId = aKbnId;
    }

    /**
     * getter for mark.
     * 
     * @return mark
     */
    public String getMark() {
        return this.mark;
    }

    /**
     * setter for mark.
     * 
     * @param aMark mark
     */
    public void setMark(final String aMark) {
        this.mark = aMark;
    }

    /**
     * getter for kbn.
     * 
     * @return kbn
     */
    public Kbn getKbn() {
        return this.kbn;
    }

    /**
     * setter for kbn.
     * 
     * @param aKbn kbn
     */
    public void setKbn(final Kbn aKbn) {
        this.kbn = aKbn;
    }

}
