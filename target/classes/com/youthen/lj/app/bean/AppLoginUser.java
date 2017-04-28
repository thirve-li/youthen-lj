// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

import java.util.List;

/**
 * APP用户信息。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class AppLoginUser {

    /** 头像 **/
    private String headimgurl;

    /** 姓名 **/
    private String name;

    /** 昵称 **/
    private String nickName;

    /** 手机 **/
    private String mobile;

    /** 业主的房屋 **/
    private List<RoomInfo> rooms;

    /** 积分 **/
    private int score;

    /** 当日获得积分 **/
    private long todayScore;

    /**
     * 未读消息条数
     */
    private int newMsgCnt;

    /**
     * 当日是否已经签到
     */
    private String hasAsigned;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 令牌
     */
    private String accessToken;

    /**
     * openId
     */
    private String openId;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 累计签到天数
     */
    private String asignDays;

    /**
     * getter for name.
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * setter for name.
     * 
     * @param aName name
     */
    public void setName(final String aName) {
        this.name = aName;
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
     * getter for score.
     * 
     * @return score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * setter for score.
     * 
     * @param aScore score
     */
    public void setScore(final int aScore) {
        this.score = aScore;
    }

    /**
     * getter for mobile.
     * 
     * @return mobile
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * setter for mobile.
     * 
     * @param aMobile mobile
     */
    public void setMobile(final String aMobile) {
        this.mobile = aMobile;
    }

    /**
     * getter for newMsgCnt.
     * 
     * @return newMsgCnt
     */
    public int getNewMsgCnt() {
        return this.newMsgCnt;
    }

    /**
     * setter for newMsgCnt.
     * 
     * @param aNewMsgCnt newMsgCnt
     */
    public void setNewMsgCnt(final int aNewMsgCnt) {
        this.newMsgCnt = aNewMsgCnt;
    }

    /**
     * getter for rooms.
     * 
     * @return rooms
     */
    public List<RoomInfo> getRooms() {
        return this.rooms;
    }

    /**
     * setter for rooms.
     * 
     * @param aRooms rooms
     */
    public void setRooms(final List<RoomInfo> aRooms) {
        this.rooms = aRooms;
    }

    /**
     * getter for hasAsigned.
     * 
     * @return hasAsigned
     */
    public String getHasAsigned() {
        return this.hasAsigned;
    }

    /**
     * setter for hasAsigned.
     * 
     * @param aHasAsigned hasAsigned
     */
    public void setHasAsigned(final String aHasAsigned) {
        this.hasAsigned = aHasAsigned;
    }

    /**
     * getter for pwd.
     * 
     * @return pwd
     */
    public String getPwd() {
        return this.pwd;
    }

    /**
     * setter for pwd.
     * 
     * @param aPwd pwd
     */
    public void setPwd(final String aPwd) {
        this.pwd = aPwd;
    }

    /**
     * getter for openId.
     * 
     * @return openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * setter for openId.
     * 
     * @param aOpenId openId
     */
    public void setOpenId(final String aOpenId) {
        this.openId = aOpenId;
    }

    /**
     * getter for verifyCode.
     * 
     * @return verifyCode
     */
    public String getVerifyCode() {
        return this.verifyCode;
    }

    /**
     * setter for verifyCode.
     * 
     * @param aVerifyCode verifyCode
     */
    public void setVerifyCode(final String aVerifyCode) {
        this.verifyCode = aVerifyCode;
    }

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
     * getter for birthday.
     * 
     * @return birthday
     */
    public String getBirthday() {
        return this.birthday;
    }

    /**
     * setter for birthday.
     * 
     * @param aBirthday birthday
     */
    public void setBirthday(final String aBirthday) {
        this.birthday = aBirthday;
    }

    /**
     * getter for asignDays.
     * 
     * @return asignDays
     */
    public String getAsignDays() {
        return this.asignDays;
    }

    /**
     * setter for asignDays.
     * 
     * @param aAsignDays asignDays
     */
    public void setAsignDays(final String aAsignDays) {
        this.asignDays = aAsignDays;
    }

    /**
     * getter for todayScore.
     * 
     * @return todayScore
     */
    public long getTodayScore() {
        return this.todayScore;
    }

    /**
     * setter for todayScore.
     * 
     * @param aTodayScore todayScore
     */
    public void setTodayScore(final long aTodayScore) {
        this.todayScore = aTodayScore;
    }

}
