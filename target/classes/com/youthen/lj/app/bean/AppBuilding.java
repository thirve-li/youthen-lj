// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

/**
 * 活动。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class AppBuilding {

    String buildingNum;
    String roomCode;

    /**
     * getter for buildingNum.
     * 
     * @return buildingNum
     */
    public String getBuildingNum() {
        return this.buildingNum;
    }

    /**
     * setter for buildingNum.
     * 
     * @param aBuildingNum buildingNum
     */
    public void setBuildingNum(final String aBuildingNum) {
        this.buildingNum = aBuildingNum;
    }

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
}
