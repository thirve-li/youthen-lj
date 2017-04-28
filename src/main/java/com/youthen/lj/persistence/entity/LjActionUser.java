// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.LoginUser;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjActionUser extends AbstractCommonEntity {

    private static final long serialVersionUID = -1393066067391786371L;

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

}
