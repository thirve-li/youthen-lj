// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class AppActionUser {

    /**
     * 用户id
     */
    private String userId;

    /** 昵称 **/
    private String nickName;

    /** 头像 **/
    private String image;

    /**
     * 类型 0:点赞,1:参与
     */
    private Integer type;

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
     * getter for nickName.
     * 
     * @return nickName
     */
    public String getNickName() {
        return this.nickName;
    }

    /**
     * setter for nickName.
     * 
     * @param aNickName nickName
     */
    public void setNickName(final String aNickName) {
        this.nickName = aNickName;
    }

    /**
     * getter for image.
     * 
     * @return image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * setter for image.
     * 
     * @param aImage image
     */
    public void setImage(final String aImage) {
        this.image = aImage;
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
