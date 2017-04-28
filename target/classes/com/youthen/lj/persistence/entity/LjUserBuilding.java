// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.LoginUser;

/**
 * 用户、房屋关联表。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjUserBuilding extends AbstractCommonEntity {

    private static final long serialVersionUID = -1393066067391786371L;

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
