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
public class NewMessage {

    /**
     * 标题
     */
    String title;

    /**
     * opt
     */
    String opt;

    /**
     * 帖子ID
     */
    String postId;

    /**
     * 0：社区通知 1：社区活动
     * 2：个人话题 3：个人活动
     * 4：意见反馈 5、关于我们 6: 推荐活动
     */
    String type;

    /**
     * getter for title.
     * 
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * setter for title.
     * 
     * @param aTitle title
     */
    public void setTitle(final String aTitle) {
        this.title = aTitle;
    }

    /**
     * getter for opt.
     * 
     * @return opt
     */
    public String getOpt() {
        return this.opt;
    }

    /**
     * setter for opt.
     * 
     * @param aOpt opt
     */
    public void setOpt(final String aOpt) {
        this.opt = aOpt;
    }

    /**
     * getter for postId.
     * 
     * @return postId
     */
    public String getPostId() {
        return this.postId;
    }

    /**
     * setter for postId.
     * 
     * @param aPostId postId
     */
    public void setPostId(final String aPostId) {
        this.postId = aPostId;
    }

    /**
     * getter for type.
     * 
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * setter for type.
     * 
     * @param aType type
     */
    public void setType(final String aType) {
        this.type = aType;
    }

}
