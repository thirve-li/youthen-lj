// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class RoomInfo {

    String id;

    /**
     * 房间单元代码
     */
    private String roomCode;

    /**
     * 每月物业费
     */
    private String price;

    /**
     * 最后付费期别
     */
    private String lastPeriod;

    /**
     * 建筑面积
     */
    private String area;

    /**
     * getter for roomCode.
     * 
     * @return roomCode
     */
    public String getRoomCode() {
        return this.roomCode;
    }

    /**
     * setter for roomCode.
     * 
     * @param aRoomCode roomCode
     */
    public void setRoomCode(final String aRoomCode) {
        this.roomCode = aRoomCode;
    }

    /**
     * getter for price.
     * 
     * @return price
     */
    public String getPrice() {
        return this.price;
    }

    /**
     * setter for price.
     * 
     * @param aPrice price
     */
    public void setPrice(final String aPrice) {
        this.price = aPrice;
    }

    /**
     * getter for id.
     * 
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * setter for id.
     * 
     * @param aId id
     */
    public void setId(final String aId) {
        this.id = aId;
    }

    /**
     * getter for lastPeriod.
     * 
     * @return lastPeriod
     */
    public String getLastPeriod() {
        return this.lastPeriod;
    }

    /**
     * setter for lastPeriod.
     * 
     * @param aLastPeriod lastPeriod
     */
    public void setLastPeriod(final String aLastPeriod) {
        this.lastPeriod = aLastPeriod;
    }

    /**
     * getter for area.
     * 
     * @return area
     */
    public String getArea() {
        return this.area;
    }

    /**
     * setter for area.
     * 
     * @param aArea area
     */
    public void setArea(final String aArea) {
        this.area = aArea;
    }
}
