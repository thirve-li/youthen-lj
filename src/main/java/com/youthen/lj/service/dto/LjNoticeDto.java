// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.dto;

import java.util.Date;
import java.util.Set;
import com.youthen.framework.common.annotation.Dto;
import com.youthen.lj.persistence.entity.LjActionComment;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.dto.MasterEntryDto;

/**
 * 社区通知/社区活动/个人话题/个人活动/意见反馈/关于我们。
 * 
 * @author gong
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Dto
public class LjNoticeDto extends MasterEntryDto {

    /**
     * 版本号
     * 
     * @see com.youthen.framework.persistence.entity.CommonEntity#getId()
     */
    private static final long serialVersionUID = -5365796280807713010L;

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String theContent;

    /**
     * 简单描述
     */
    private String shortDesc;

    /**
     * 图片1
     */
    private String image1;

    /**
     * 图片2
     */
    private String image2;

    /**
     * 图片3
     */
    private String image3;

    /**
     * 图片4
     */
    private String image4;

    /**
     * 图片5
     */
    private String image5;

    /**
     * 起始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 活动地点
     */
    private String place;

    /**
     * 计划人数
     */
    private int planNum;

    /**
     * 时间人数
     */
    private int infactNum;

    /**
     * 点赞次数
     */
    private int goodNum;

    /**
     * 类别 0：社区通知 1：社区活动 2：个人话题 3：个人活动 4：意见反馈 5、关于我们 6。首页推荐
     */
    private Integer type;

    /**
     * 是否置顶 0:否 1：置顶
     */
    private Integer isTop;

    /**
     * 状态 0:失效 1：有效
     */
    private Integer status;

    /**
     * 创建人ID
     */
    private String createrId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人ID
     */
    private String UpdaterId;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 相关评论
     */
    private Set<LjActionComment> comments;

    /**
     * 创建人
     */
    private LoginUser creater;

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
     * getter for image1.
     * 
     * @return image1
     */
    public String getImage1() {
        return this.image1;
    }

    /**
     * setter for image1.
     * 
     * @param aImage1 image1
     */
    public void setImage1(final String aImage1) {
        this.image1 = aImage1;
    }

    /**
     * getter for image2.
     * 
     * @return image2
     */
    public String getImage2() {
        return this.image2;
    }

    /**
     * setter for image2.
     * 
     * @param aImage2 image2
     */
    public void setImage2(final String aImage2) {
        this.image2 = aImage2;
    }

    /**
     * getter for image3.
     * 
     * @return image3
     */
    public String getImage3() {
        return this.image3;
    }

    /**
     * setter for image3.
     * 
     * @param aImage3 image3
     */
    public void setImage3(final String aImage3) {
        this.image3 = aImage3;
    }

    /**
     * getter for image4.
     * 
     * @return image4
     */
    public String getImage4() {
        return this.image4;
    }

    /**
     * setter for image4.
     * 
     * @param aImage4 image4
     */
    public void setImage4(final String aImage4) {
        this.image4 = aImage4;
    }

    /**
     * getter for image5.
     * 
     * @return image5
     */
    public String getImage5() {
        return this.image5;
    }

    /**
     * setter for image5.
     * 
     * @param aImage5 image5
     */
    public void setImage5(final String aImage5) {
        this.image5 = aImage5;
    }

    /**
     * getter for startDate.
     * 
     * @return startDate
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * setter for startDate.
     * 
     * @param aStartDate startDate
     */
    public void setStartDate(final Date aStartDate) {
        this.startDate = aStartDate;
    }

    /**
     * getter for endDate.
     * 
     * @return endDate
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * setter for endDate.
     * 
     * @param aEndDate endDate
     */
    public void setEndDate(final Date aEndDate) {
        this.endDate = aEndDate;
    }

    /**
     * getter for place.
     * 
     * @return place
     */
    public String getPlace() {
        return this.place;
    }

    /**
     * setter for place.
     * 
     * @param aPlace place
     */
    public void setPlace(final String aPlace) {
        this.place = aPlace;
    }

    /**
     * getter for planNum.
     * 
     * @return planNum
     */
    public int getPlanNum() {
        return this.planNum;
    }

    /**
     * setter for planNum.
     * 
     * @param aPlanNum planNum
     */
    public void setPlanNum(final int aPlanNum) {
        this.planNum = aPlanNum;
    }

    /**
     * getter for infactNum.
     * 
     * @return infactNum
     */
    public int getInfactNum() {
        return this.infactNum;
    }

    /**
     * setter for infactNum.
     * 
     * @param aInfactNum infactNum
     */
    public void setInfactNum(final int aInfactNum) {
        this.infactNum = aInfactNum;
    }

    /**
     * getter for goodNum.
     * 
     * @return goodNum
     */
    public int getGoodNum() {
        return this.goodNum;
    }

    /**
     * setter for goodNum.
     * 
     * @param aGoodNum goodNum
     */
    public void setGoodNum(final int aGoodNum) {
        this.goodNum = aGoodNum;
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
     * getter for isTop.
     * 
     * @return isTop
     */
    public Integer getIsTop() {
        return this.isTop;
    }

    /**
     * setter for isTop.
     * 
     * @param aIsTop isTop
     */
    public void setIsTop(final Integer aIsTop) {
        this.isTop = aIsTop;
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
     * getter for updaterId.
     * 
     * @return updaterId
     */
    public String getUpdaterId() {
        return this.UpdaterId;
    }

    /**
     * setter for updaterId.
     * 
     * @param aUpdaterId updaterId
     */
    public void setUpdaterId(final String aUpdaterId) {
        this.UpdaterId = aUpdaterId;
    }

    /**
     * getter for updateTime.
     * 
     * @return updateTime
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * setter for updateTime.
     * 
     * @param aUpdateTime updateTime
     */
    public void setUpdateTime(final String aUpdateTime) {
        this.updateTime = aUpdateTime;
    }

    /**
     * getter for comments.
     * 
     * @return comments
     */
    public Set<LjActionComment> getComments() {
        return this.comments;
    }

    /**
     * setter for comments.
     * 
     * @param aComments comments
     */
    public void setComments(final Set<LjActionComment> aComments) {
        this.comments = aComments;
    }

    /**
     * getter for shortDesc.
     * 
     * @return shortDesc
     */
    public String getShortDesc() {
        return this.shortDesc;
    }

    /**
     * setter for shortDesc.
     * 
     * @param aShortDesc shortDesc
     */
    public void setShortDesc(final String aShortDesc) {
        this.shortDesc = aShortDesc;
    }

    /**
     * getter for creater.
     * 
     * @return creater
     */
    public LoginUser getCreater() {
        return this.creater;
    }

    /**
     * setter for creater.
     * 
     * @param aCreater creater
     */
    public void setCreater(final LoginUser aCreater) {
        this.creater = aCreater;
    }

}
