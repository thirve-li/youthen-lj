// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

import java.util.List;

/**
 * 活动。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class AppActive {

    /**
     * 创建人昵称
     */
    private String createrNickName;

    /**
     * 创建人头像
     */
    private String createrImage;

    /**
     * 起始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * ID
     */
    private Long id;

    /**
     * 活动名称、标题
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
     * 创建人ID
     */
    private String createrId;

    private String status;

    // 第几页
    int gotoPage = 1;

    // 每页几条数据,默认15
    int pageSize = 15;

    /**
     * 相关评论
     */
    private List<AppComment> comments;

    /**
     * 参与活动的用户
     */
    private List<AppActionUser> actionUsers;

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
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * setter for startDate.
     * 
     * @param aStartDate startDate
     */
    public void setStartDate(final String aStartDate) {
        this.startDate = aStartDate;
    }

    /**
     * getter for endDate.
     * 
     * @return endDate
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * setter for endDate.
     * 
     * @param aEndDate endDate
     */
    public void setEndDate(final String aEndDate) {
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
     * getter for comments.
     * 
     * @return comments
     */
    public List<AppComment> getComments() {
        return this.comments;
    }

    /**
     * setter for comments.
     * 
     * @param aComments comments
     */
    public void setComments(final List<AppComment> aComments) {
        this.comments = aComments;
    }

    /**
     * getter for gotoPage.
     * 
     * @return gotoPage
     */
    public int getGotoPage() {
        return this.gotoPage;
    }

    /**
     * setter for gotoPage.
     * 
     * @param aGotoPage gotoPage
     */
    public void setGotoPage(final int aGotoPage) {
        this.gotoPage = aGotoPage;
    }

    /**
     * getter for pageSize.
     * 
     * @return pageSize
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * setter for pageSize.
     * 
     * @param aPageSize pageSize
     */
    public void setPageSize(final int aPageSize) {
        this.pageSize = aPageSize;
    }

    /**
     * getter for status.
     * 
     * @return status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * setter for status.
     * 
     * @param aStatus status
     */
    public void setStatus(final String aStatus) {
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

    /**
     * getter for actionUsers.
     * 
     * @return actionUsers
     */
    public List<AppActionUser> getActionUsers() {
        return this.actionUsers;
    }

    /**
     * setter for actionUsers.
     * 
     * @param aActionUsers actionUsers
     */
    public void setActionUsers(final List<AppActionUser> aActionUsers) {
        this.actionUsers = aActionUsers;
    }

}
