// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.lj.service.dto.LjRoomInfoDto;

/**
 * 。
 * 
 * @author wdc
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@SuppressWarnings("serial")
@Namespace("/lj-room")
@Controller
public class LjRoomInfoAjaxAction extends AbstractAjaxAction {

    private LjRoomInfoDto dto;

    @Autowired
    LjRoomInfoService ljRoomInfoService;
    /**
     * 建筑面积
     */
    private double measureOfArea;

    /**
     * 单价
     */
    private double priceSquareMeter;

    /**
     * 总价
     */
    private float totalPrice;

    /**
     * @see com.youthen.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    public float getTotalPrice() {
        this.measureOfArea = this.dto.getMeasureOfArea();
        this.priceSquareMeter = this.dto.getPriceSquareMeter();
        this.totalPrice = (float) (this.measureOfArea * this.priceSquareMeter);
        return this.totalPrice;
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
     * setter for totalPrice.
     * 
     * @param aTotalPrice totalPrice
     */
    public void setTotalPrice(final float aTotalPrice) {
        this.totalPrice = aTotalPrice;
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public LjRoomInfoDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final LjRoomInfoDto aDto) {
        this.dto = aDto;
    }

}
