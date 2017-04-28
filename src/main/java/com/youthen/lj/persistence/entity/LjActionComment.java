// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

import java.util.Date;
import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.LoginUser;

/**
 * 社区活动/个人话题/个人活动/二手商品 的评论 。
 * 
 * @author gong
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjActionComment extends AbstractCommonEntity {

    /**
     * @see com.youthen.framework.persistence.entity.CommonEntity#getId()
     */
    private static final long serialVersionUID = -630238979630101086L;

    /**
     * 评论ID
     */
    private Long id;
    /**
     * 活动ID
     */
    private Long actionId;
    private LjNotice action;
    /**
     * 引用回复ID
     */
    private Long commentId;
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
    private Integer isgood;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 类别0、社区活动 1、二手商品
     */
    private Integer type;
    /**
     * 创建人ID
     */
    private String createId;
    private LoginUser user;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人ID
     */
    private String updateId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 排他版本号
     */
    private Long versionNo;

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
     * getter for commentId.
     * 
     * @return commentId
     */
    public Long getCommentId() {
        return this.commentId;
    }

    /**
     * setter for commentId.
     * 
     * @param aCommentId commentId
     */
    public void setCommentId(final Long aCommentId) {
        this.commentId = aCommentId;
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
     * getter for isgood.
     * 
     * @return isgood
     */
    public Integer getIsgood() {
        return this.isgood;
    }

    /**
     * setter for isgood.
     * 
     * @param aIsgood isgood
     */
    public void setIsgood(final Integer aIsgood) {
        this.isgood = aIsgood;
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
     * getter for createTime.
     * 
     * @return createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * setter for createTime.
     * 
     * @param aCreateTime createTime
     */
    public void setCreateTime(final Date aCreateTime) {
        this.createTime = aCreateTime;
    }

    /**
     * getter for updateId.
     * 
     * @return updateId
     */
    public String getUpdateId() {
        return this.updateId;
    }

    /**
     * setter for updateId.
     * 
     * @param aUpdateId updateId
     */
    public void setUpdateId(final String aUpdateId) {
        this.updateId = aUpdateId;
    }

    /**
     * getter for updateTime.
     * 
     * @return updateTime
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * setter for updateTime.
     * 
     * @param aUpdateTime updateTime
     */
    public void setUpdateTime(final Date aUpdateTime) {
        this.updateTime = aUpdateTime;
    }

    /**
     * getter for versionNo.
     * 
     * @return versionNo
     */
    @Override
    public Long getVersionNo() {
        return this.versionNo;
    }

    /**
     * setter for versionNo.
     * 
     * @param aVersionNo versionNo
     */
    @Override
    public void setVersionNo(final Long aVersionNo) {
        this.versionNo = aVersionNo;
    }

    /**
     * getter for action.
     * 
     * @return action
     */
    public LjNotice getAction() {
        return this.action;
    }

    /**
     * setter for action.
     * 
     * @param aAction action
     */
    public void setAction(final LjNotice aAction) {
        this.action = aAction;
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
