// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

import java.util.Date;
import com.youthen.framework.persistence.entity.AbstractCommonEntity;

/**
 * 物业报修、投诉。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjRepairCpln extends AbstractCommonEntity {

    /**
     * @see com.youthen.framework.persistence.entity.CommonEntity#getId()
     */
    private static final long serialVersionUID = -1393066067391786371L;

    /**
     * ID
     */
    private Long id;

    /**
     * 报修物品名或报修标题
     */
    private String title;

    private String serviceTime;

    /**
     * 内容
     */
    private String theContent;

    /**
     * 类别 0:报修 1:投诉
     */
    private Integer type;

    /**
     * 修理类别
     */
    private String repairItem;

    /**
     * 状态 0:已报修/已投诉 1:处理中 2:已处理
     */
    private Integer status;

    /**
     * 报修人
     */
    private String repairMan;

    /**
     * 接收人Id
     */
    private String receiverId;

    /**
     * 报修人、投诉人
     */
    private String reporterId;

    /**
     * 报修时间、投诉时间
     */
    private Date reportTime;

    /**
     * 房间编号
     */
    private String roomCode;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 服务速度
     */
    private Integer serviceMark;

    /**
     * 处理速度
     */
    private Integer speedMark;

    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系人电话
     */
    private String contacterTel;

    /**
     * 评价内容
     */
    private String commentContent;

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
     * getter for repairItem.
     * 
     * @return repairItem
     */
    public String getRepairItem() {
        return this.repairItem;
    }

    /**
     * setter for repairItem.
     * 
     * @param aRepairItem repairItem
     */
    public void setRepairItem(final String aRepairItem) {
        this.repairItem = aRepairItem;
    }

    /**
     * getter for repairMan.
     * 
     * @return repairMan
     */
    public String getRepairMan() {
        return this.repairMan;
    }

    /**
     * setter for repairMan.
     * 
     * @param aRepairMan repairMan
     */
    public void setRepairMan(final String aRepairMan) {
        this.repairMan = aRepairMan;
    }

    /**
     * getter for receiverId.
     * 
     * @return receiverId
     */
    public String getReceiverId() {
        return this.receiverId;
    }

    /**
     * setter for receiverId.
     * 
     * @param aReceiverId receiverId
     */
    public void setReceiverId(final String aReceiverId) {
        this.receiverId = aReceiverId;
    }

    /**
     * getter for reporterId.
     * 
     * @return reporterId
     */
    public String getReporterId() {
        return this.reporterId;
    }

    /**
     * setter for reporterId.
     * 
     * @param aReporterId reporterId
     */
    public void setReporterId(final String aReporterId) {
        this.reporterId = aReporterId;
    }

    /**
     * getter for reportTime.
     * 
     * @return reportTime
     */
    public Date getReportTime() {
        return this.reportTime;
    }

    /**
     * setter for reportTime.
     * 
     * @param aReportTime reportTime
     */
    public void setReportTime(final Date aReportTime) {
        this.reportTime = aReportTime;
    }

    /**
     * getter for roomCode.
     * 
     * @return roomCode
     */
    public String getRoomCode() {
        return this.roomCode;
    }

    /**
     * setter for roomCode.
     * 
     * @param aRoomCode roomCode
     */
    public void setRoomCode(final String aRoomCode) {
        this.roomCode = aRoomCode;
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
     * getter for serviceMark.
     * 
     * @return serviceMark
     */
    public Integer getServiceMark() {
        return this.serviceMark;
    }

    /**
     * setter for serviceMark.
     * 
     * @param aServiceMark serviceMark
     */
    public void setServiceMark(final Integer aServiceMark) {
        this.serviceMark = aServiceMark;
    }

    /**
     * getter for speedMark.
     * 
     * @return speedMark
     */
    public Integer getSpeedMark() {
        return this.speedMark;
    }

    /**
     * setter for speedMark.
     * 
     * @param aSpeedMark speedMark
     */
    public void setSpeedMark(final Integer aSpeedMark) {
        this.speedMark = aSpeedMark;
    }

    /**
     * getter for finishTime.
     * 
     * @return finishTime
     */
    public Date getFinishTime() {
        return this.finishTime;
    }

    /**
     * setter for finishTime.
     * 
     * @param aFinishTime finishTime
     */
    public void setFinishTime(final Date aFinishTime) {
        this.finishTime = aFinishTime;
    }

    /**
     * getter for contacter.
     * 
     * @return contacter
     */
    public String getContacter() {
        return this.contacter;
    }

    /**
     * setter for contacter.
     * 
     * @param aContacter contacter
     */
    public void setContacter(final String aContacter) {
        this.contacter = aContacter;
    }

    /**
     * getter for contacterTel.
     * 
     * @return contacterTel
     */
    public String getContacterTel() {
        return this.contacterTel;
    }

    /**
     * setter for contacterTel.
     * 
     * @param aContacterTel contacterTel
     */
    public void setContacterTel(final String aContacterTel) {
        this.contacterTel = aContacterTel;
    }

    /**
     * getter for commentContent.
     * 
     * @return commentContent
     */
    public String getCommentContent() {
        return this.commentContent;
    }

    /**
     * setter for commentContent.
     * 
     * @param aCommentContent commentContent
     */
    public void setCommentContent(final String aCommentContent) {
        this.commentContent = aCommentContent;
    }

    /**
     * getter for serviceTime.
     * 
     * @return serviceTime
     */
    public String getServiceTime() {
        return this.serviceTime;
    }

    /**
     * setter for serviceTime.
     * 
     * @param aServiceTime serviceTime
     */
    public void setServiceTime(final String aServiceTime) {
        this.serviceTime = aServiceTime;
    }

}
