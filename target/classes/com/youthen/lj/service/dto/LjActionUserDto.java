// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.dto;

import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.dto.MasterEntryDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjActionUserDto extends MasterEntryDto {

    private static final long serialVersionUID = -5365796280807713010L;

    /**
     * ID
     */
    private Long id;

    /**
     * 个人活动id
     */
    private Long actionId;

    /**
     * 参与人id
     */
    private String userId;
    private LoginUser user;

    /**
     * 类型 0:点赞,1:参与
     */
    private Integer type;

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
     * getter for actionId.
     * 
     * @return actionId
     */
    public Long getActionId() {
        return this.actionId;
    }

    /**
     * setter for actionId.
     * 
     * @param aActionId actionId
     */
    public void setActionId(final Long aActionId) {
        this.actionId = aActionId;
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
     * getter for type.
     * 
     * @return type
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * setter for type.
     * 
     * @param aType type
     */
    public void setType(final Integer aType) {
        this.type = aType;
    }
}
