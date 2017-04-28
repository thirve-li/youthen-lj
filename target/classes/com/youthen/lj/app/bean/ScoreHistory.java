// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

import java.util.Map;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class ScoreHistory {

    /**
     * 积分来源
     */
    private String gotFrom;
    /**
     * 获得积分
     */
    private String score;

    /**
     * 获得积分时间
     */
    private String time;

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
     * getter for todayScore.
     * 
     * @return todayScore
     */
    public String getTodayScore() {
        return this.todayScore;
    }

    /**
     * getter for time.
     * 
     * @return time
     */
    public String getTime() {
        return this.time;
    }

    /**
     * setter for time.
     * 
     * @param aTime time
     */
    public void setTime(final String aTime) {
        this.time = aTime;
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
    public String getScore() {
        return this.score;
    }

    /**
     * setter for score.
     * 
     * @param aScore score
     */
    public void setScore(final String aScore) {
        this.score = aScore;
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

    /**
     * getter for totalScore.
     * 
     * @return totalScore
     */
    public String getTotalScore() {
        return this.totalScore;
    }

    /**
     * setter for totalScore.
     * 
     * @param aTotalScore totalScore
     */
    public void setTotalScore(final String aTotalScore) {
        this.totalScore = aTotalScore;
    }

    /**
     * setter for todayScore.
     * 
     * @param aTodayScore todayScore
     */
    public void setTodayScore(final String aTodayScore) {
        this.todayScore = aTodayScore;
    }

}
