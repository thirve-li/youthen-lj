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
public class WxUserInfo {

    private String openid;
    private String nickname;
    private String sex;
    private String headimgurl;
    private String city;
    private String country;

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

    /**
     * getter for nickname.
     * 
     * @return nickname
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * setter for nickname.
     * 
     * @param aNickname nickname
     */
    public void setNickname(final String aNickname) {
        this.nickname = aNickname;
    }

    /**
     * getter for sex.
     * 
     * @return sex
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * setter for sex.
     * 
     * @param aSex sex
     */
    public void setSex(final String aSex) {
        this.sex = aSex;
    }

    /**
     * getter for headimgurl.
     * 
     * @return headimgurl
     */
    public String getHeadimgurl() {
        return this.headimgurl;
    }

    /**
     * setter for headimgurl.
     * 
     * @param aHeadimgurl headimgurl
     */
    public void setHeadimgurl(final String aHeadimgurl) {
        this.headimgurl = aHeadimgurl;
    }

    /**
     * getter for city.
     * 
     * @return city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * setter for city.
     * 
     * @param aCity city
     */
    public void setCity(final String aCity) {
        this.city = aCity;
    }

    /**
     * getter for country.
     * 
     * @return country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * setter for country.
     * 
     * @param aCountry country
     */
    public void setCountry(final String aCountry) {
        this.country = aCountry;
    }
}
