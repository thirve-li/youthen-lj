// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class AccessToken {

    public String accessToken;
    public String refreshToken;
    public String openid;

    /**
     * getter for accessToken.
     * 
     * @return accessToken
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * setter for accessToken.
     * 
     * @param aAccessToken accessToken
     */
    public void setAccessToken(final String aAccessToken) {
        this.accessToken = aAccessToken;
    }

    /**
     * getter for refreshToken.
     * 
     * @return refreshToken
     */
    public String getRefreshToken() {
        return this.refreshToken;
    }

    /**
     * setter for refreshToken.
     * 
     * @param aRefreshToken refreshToken
     */
    public void setRefreshToken(final String aRefreshToken) {
        this.refreshToken = aRefreshToken;
    }

    /**
     * getter for openid.
     * 
     * @return openid
     */
    public String getOpenid() {
        return this.openid;
    }

    /**
     * setter for openid.
     * 
     * @param aOpenid openid
     */
    public void setOpenid(final String aOpenid) {
        this.openid = aOpenid;
    }

}
