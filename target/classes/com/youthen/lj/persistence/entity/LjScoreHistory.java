// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

import java.util.Date;
import com.youthen.framework.persistence.entity.AbstractCommonEntity;

/**
 * 获得积分。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjScoreHistory extends AbstractCommonEntity {

    /**
     * @see com.youthen.framework.persistence.entity.CommonEntity#getId()
     */
    private static final long serialVersionUID = -630238979630101086L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 来源
     */
    private String gotFrom;

    /**
     * 得分
     */
    private Long score;

    /**
     * 原因
     */
    private String reason;

    /**
     * 创建人ID
     */
    private String createrId;

    /**
     * 创建时间
     */
    private Date createTime;

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
     * getter for gotFrom.
     * 
     * @return gotFrom
     */
    public String getGotFrom() {
        return this.gotFrom;
    }

    /**
     * setter for gotFrom.
     * 
     * @param aGotFrom gotFrom
     */
    public void setGotFrom(final String aGotFrom) {
        this.gotFrom = aGotFrom;
    }

    /**
     * getter for score.
     * 
     * @return score
     */
    public Long getScore() {
        return this.score;
    }

    /**
     * setter for score.
     * 
     * @param aScore score
     */
    public void setScore(final Long aScore) {
        this.score = aScore;
    }

    /**
     * getter for reason.
     * 
     * @return reason
     */
    @Override
    public String getReason() {
        return this.reason;
    }

    /**
     * setter for reason.
     * 
     * @param aReason reason
     */
    @Override
    public void setReason(final String aReason) {
        this.reason = aReason;
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
     * getter for createrId.
     * 
     * @return createrId
     */
    public String getCreaterId() {
        return this.createrId;
    }

    /**
     * setter for createrId.
     * 
     * @param aCreaterId createrId
     */
    public void setCreaterId(final String aCreaterId) {
        this.createrId = aCreaterId;
    }

}
