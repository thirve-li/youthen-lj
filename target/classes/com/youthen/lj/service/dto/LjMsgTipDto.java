// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.dto;

import com.youthen.framework.common.annotation.Dto;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.dto.MasterEntryDto;

/**
 * 消息提示,文章被回复时写这表,点开消息提示时清空。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Dto
public class LjMsgTipDto extends MasterEntryDto {

    private static final long serialVersionUID = -5365796280807713010L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;
    private LoginUser user;

    /**
     * 消息链接
     */
    private String url;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 参数id
     */
    private String paramId;

    /**
     * 小类型
     */
    private String type;

    /**
     * 大类型
     */
    private String opt;

    /**
     * 1：社区通知 2：社区活动 3: 推荐活动4：个人话题 5：个人活动6：二手商品
     */
    private String typeName;

    /**
     * 创建日期
     */
    private String createDate;

    private Integer status;

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
     * getter for url.
     * 
     * @return url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * setter for url.
     * 
     * @param aUrl url
     */
    public void setUrl(final String aUrl) {
        this.url = aUrl;
    }

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

    /**
     * getter for paramId.
     * 
     * @return paramId
     */
    public String getParamId() {
        return this.paramId;
    }

    /**
     * setter for paramId.
     * 
     * @param aParamId paramId
     */
    public void setParamId(final String aParamId) {
        this.paramId = aParamId;
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
     * getter for typeName.
     * 
     * @return typeName
     */
    public String getTypeName() {
        return this.typeName;
    }

    /**
     * setter for typeName.
     * 
     * @param aTypeName typeName
     */
    public void setTypeName(final String aTypeName) {
        this.typeName = aTypeName;
    }

    /**
     * getter for createDate.
     * 
     * @return createDate
     */
    public String getCreateDate() {
        return this.createDate;
    }

    /**
     * setter for createDate.
     * 
     * @param aCreateDate createDate
     */
    public void setCreateDate(final String aCreateDate) {
        this.createDate = aCreateDate;
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

}
