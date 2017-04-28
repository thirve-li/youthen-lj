// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.dto;

import java.util.Date;
import java.util.Map;
import com.youthen.master.service.dto.MasterEntryDto;

/**
 * 获得积分。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjScoreHistoryDto extends MasterEntryDto {

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
     * 当日获得积分
     */
    private String todayScore;
    /**
     * 积分条目合计
     */
    private Map<String, String> otherScore;
    /**
     * 总积分
     */
    private String totalScore;

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
     * @param gotFrom gotFrom
     */
    public void setGotFrom(final String gotFrom) {
        this.gotFrom = gotFrom;
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
     * @param score score
     */
    public void setScore(final Long score) {
        this.score = score;
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
     * @param reason reason
     */
    @Override
    public void setReason(final String reason) {
        this.reason = reason;
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
     * @param createrId createrId
     */
    public void setCreaterId(final String createrId) {
        this.createrId = createrId;
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
     * @param createTime createTime
     */
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    /**
     * getter for todayScore.
     * 
     * @return todayScore
     */
    public String getTodayScore() {
        return this.todayScore;
    }

    /**
     * setter for todayScore.
     * 
     * @param aTodayScore todayScore
     */
    public void setTodayScore(final String aTodayScore) {
        this.todayScore = aTodayScore;
    }

    /**
     * getter for otherScore.
     * 
     * @return otherScore
     */
    public Map<String, String> getOtherScore() {
        return this.otherScore;
    }

    /**
     * setter for otherScore.
     * 
     * @param aOtherScore otherScore
     */
    public void setOtherScore(final Map<String, String> aOtherScore) {
        this.otherScore = aOtherScore;
    }

}
