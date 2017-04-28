// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

/**
 * 活动的评论。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class AppComment {

    /**
     * 评论ID
     */
    private Long id;

    /**
     * 活动ID
     */
    private Long actionId;

    /**
     * 回复楼号
     */
    private Long levelId;

    /**
     * 内容
     */
    private String theContent;

    /**
     * 是否点赞
     */
    private Integer isGood;

    /**
     * 创建人ID
     */
    private String createId;

    /**
     * 创建人ID
     */
    private String createrNickName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 状态 0:失效 1：有效
     */
    private Integer status;

    private String createrImage;

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
     * getter for levelId.
     * 
     * @return levelId
     */
    public Long getLevelId() {
        return this.levelId;
    }

    /**
     * setter for levelId.
     * 
     * @param aLevelId levelId
     */
    public void setLevelId(final Long aLevelId) {
        this.levelId = aLevelId;
    }

    /**
     * getter for theContent.
     * 
     * @return theContent
     */
    public String getTheContent() {
        return this.theContent;
    }

    /**
     * setter for theContent.
     * 
     * @param aTheContent theContent
     */
    public void setTheContent(final String aTheContent) {
        this.theContent = aTheContent;
    }

    /**
     * getter for isGood.
     * 
     * @return isGood
     */
    public Integer getIsGood() {
        return this.isGood;
    }

    /**
     * setter for isGood.
     * 
     * @param aIsGood isGood
     */
    public void setIsGood(final Integer aIsGood) {
        this.isGood = aIsGood;
    }

    /**
     * getter for createId.
     * 
     * @return createId
     */
    public String getCreateId() {
        return this.createId;
    }

    /**
     * setter for createId.
     * 
     * @param aCreateId createId
     */
    public void setCreateId(final String aCreateId) {
        this.createId = aCreateId;
    }

    /**
     * getter for createrNickName.
     * 
     * @return createrNickName
     */
    public String getCreaterNickName() {
        return this.createrNickName;
    }

    /**
     * setter for createrNickName.
     * 
     * @param aCreaterNickName createrNickName
     */
    public void setCreaterNickName(final String aCreaterNickName) {
        this.createrNickName = aCreaterNickName;
    }

    /**
     * getter for createTime.
     * 
     * @return createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * setter for createTime.
     * 
     * @param aCreateTime createTime
     */
    public void setCreateTime(final String aCreateTime) {
        this.createTime = aCreateTime;
    }

    /**
     * getter for status.
     * 
     * @return status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * setter for status.
     * 
     * @param aStatus status
     */
    public void setStatus(final Integer aStatus) {
        this.status = aStatus;
    }

    /**
     * getter for createrImage.
     * 
     * @return createrImage
     */
    public String getCreaterImage() {
        return this.createrImage;
    }

    /**
     * setter for createrImage.
     * 
     * @param aCreaterImage createrImage
     */
    public void setCreaterImage(final String aCreaterImage) {
        this.createrImage = aCreaterImage;
    }

}
