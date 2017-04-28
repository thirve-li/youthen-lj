// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.dto;

import com.youthen.framework.common.annotation.Dto;
import com.youthen.lj.persistence.entity.LjRoomInfo;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.dto.MasterEntryDto;

/**
 * 用户、房屋关联表。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Dto
public class LjUserBuildingDto extends MasterEntryDto {

    private static final long serialVersionUID = 4748886797725984236L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String userId;
    private LoginUser user;

    /**
     * 房屋信息ID
     */
    private String roomInfoId;
    private LjRoomInfo roomInfo;

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
     * getter for userId.
     * 
     * @return userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * setter for userId.
     * 
     * @param aUserId userId
     */
    public void setUserId(final String aUserId) {
        this.userId = aUserId;
    }

    /**
     * getter for roomInfoId.
     * 
     * @return roomInfoId
     */
    public String getRoomInfoId() {
        return this.roomInfoId;
    }

    /**
     * setter for roomInfoId.
     * 
     * @param aRoomInfoId roomInfoId
     */
    public void setRoomInfoId(final String aRoomInfoId) {
        this.roomInfoId = aRoomInfoId;
    }

    /**
     * getter for user.
     * 
     * @return user
     */
    public LoginUser getUser() {
        return this.user;
    }

    /**
     * setter for user.
     * 
     * @param aUser user
     */
    public void setUser(final LoginUser aUser) {
        this.user = aUser;
    }

    /**
     * getter for roomInfo.
     * 
     * @return roomInfo
     */
    public LjRoomInfo getRoomInfo() {
        return this.roomInfo;
    }

    /**
     * setter for roomInfo.
     * 
     * @param aRoomInfo roomInfo
     */
    public void setRoomInfo(final LjRoomInfo aRoomInfo) {
        this.roomInfo = aRoomInfo;
    }

}
