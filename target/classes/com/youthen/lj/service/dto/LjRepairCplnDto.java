// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.dto;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Locale;
import com.youthen.framework.common.annotation.Dto;
import com.youthen.master.service.dto.MasterEntryDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Dto
public class LjRepairCplnDto extends MasterEntryDto {

    /**
     * @see com.youthen.framework.service.dto.BaseDto#getId()
     */
    private static final long serialVersionUID = 4748886797725984236L;

    /**
     * ID
     */
    private Long id;

    /**
     * 报修物品名或报修标题
     */
    private String title;

    /**
     * 内容
     */
    private String theContent;

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
     * 类别 0:报修 1:投诉
     */
    private Integer type;

    /**
     * 修理类别
     */
    private String repairItem;
    private Integer status;

    /**
     * 报修人
     */
    private String repairMan;

    /**
     * 接收人Id
     */
    private String receiverId;

    private String serviceTime;

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
     * 查询条件 年
     */

    String year;

    /**
     * 查询条件 月
     */
    String month;

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
     * getter for year.
     * 
     * @return year
     */
    public String getYear() {
        return this.year;
    }

    /**
     * setter for year.
     * 
     * @param aYear year
     */
    public void setYear(final String aYear) {
        this.year = aYear;
    }

    /**
     * getter for month.
     * 
     * @return month
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * setter for month.
     * 
     * @param aMonth month
     */
    public void setMonth(final String aMonth) {
        this.month = aMonth;
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
     * @param aArg0
     * @return
     * @see java.lang.String#charAt(int)
     */
    public char charAt(final int aArg0) {
        return this.serviceTime.charAt(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#codePointAt(int)
     */
    public int codePointAt(final int aArg0) {
        return this.serviceTime.codePointAt(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#codePointBefore(int)
     */
    public int codePointBefore(final int aArg0) {
        return this.serviceTime.codePointBefore(aArg0);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#codePointCount(int, int)
     */
    public int codePointCount(final int aArg0, final int aArg1) {
        return this.serviceTime.codePointCount(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#compareTo(java.lang.String)
     */
    public int compareTo(final String aArg0) {
        return this.serviceTime.compareTo(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#compareToIgnoreCase(java.lang.String)
     */
    public int compareToIgnoreCase(final String aArg0) {
        return this.serviceTime.compareToIgnoreCase(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#concat(java.lang.String)
     */
    public String concat(final String aArg0) {
        return this.serviceTime.concat(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#contains(java.lang.CharSequence)
     */
    public boolean contains(final CharSequence aArg0) {
        return this.serviceTime.contains(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#contentEquals(java.lang.CharSequence)
     */
    public boolean contentEquals(final CharSequence aArg0) {
        return this.serviceTime.contentEquals(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#contentEquals(java.lang.StringBuffer)
     */
    public boolean contentEquals(final StringBuffer aArg0) {
        return this.serviceTime.contentEquals(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#endsWith(java.lang.String)
     */
    public boolean endsWith(final String aArg0) {
        return this.serviceTime.endsWith(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object aArg0) {
        return this.serviceTime.equals(aArg0);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#equalsIgnoreCase(java.lang.String)
     */
    public boolean equalsIgnoreCase(final String aArg0) {
        return this.serviceTime.equalsIgnoreCase(aArg0);
    }

    /**
     * @return
     * @see java.lang.String#getBytes()
     */
    public byte[] getBytes() {
        return this.serviceTime.getBytes();
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#getBytes(java.nio.charset.Charset)
     */
    public byte[] getBytes(final Charset aArg0) {
        return this.serviceTime.getBytes(aArg0);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @param aArg2
     * @param aArg3
     * @deprecated
     * @see java.lang.String#getBytes(int, int, byte[], int)
     */
    @Deprecated
    public void getBytes(final int aArg0, final int aArg1, final byte[] aArg2, final int aArg3) {
        this.serviceTime.getBytes(aArg0, aArg1, aArg2, aArg3);
    }

    /**
     * @param aArg0
     * @return
     * @throws UnsupportedEncodingException
     * @see java.lang.String#getBytes(java.lang.String)
     */
    public byte[] getBytes(final String aArg0) throws UnsupportedEncodingException {
        return this.serviceTime.getBytes(aArg0);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @param aArg2
     * @param aArg3
     * @see java.lang.String#getChars(int, int, char[], int)
     */
    public void getChars(final int aArg0, final int aArg1, final char[] aArg2, final int aArg3) {
        this.serviceTime.getChars(aArg0, aArg1, aArg2, aArg3);
    }

    /**
     * @return
     * @see java.lang.String#hashCode()
     */
    @Override
    public int hashCode() {
        return this.serviceTime.hashCode();
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#indexOf(int, int)
     */
    public int indexOf(final int aArg0, final int aArg1) {
        return this.serviceTime.indexOf(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#indexOf(int)
     */
    public int indexOf(final int aArg0) {
        return this.serviceTime.indexOf(aArg0);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#indexOf(java.lang.String, int)
     */
    public int indexOf(final String aArg0, final int aArg1) {
        return this.serviceTime.indexOf(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#indexOf(java.lang.String)
     */
    public int indexOf(final String aArg0) {
        return this.serviceTime.indexOf(aArg0);
    }

    /**
     * @return
     * @see java.lang.String#intern()
     */
    public String intern() {
        return this.serviceTime.intern();
    }

    /**
     * @return
     * @see java.lang.String#isEmpty()
     */
    public boolean isEmpty() {
        return this.serviceTime.isEmpty();
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#lastIndexOf(int, int)
     */
    public int lastIndexOf(final int aArg0, final int aArg1) {
        return this.serviceTime.lastIndexOf(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#lastIndexOf(int)
     */
    public int lastIndexOf(final int aArg0) {
        return this.serviceTime.lastIndexOf(aArg0);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#lastIndexOf(java.lang.String, int)
     */
    public int lastIndexOf(final String aArg0, final int aArg1) {
        return this.serviceTime.lastIndexOf(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#lastIndexOf(java.lang.String)
     */
    public int lastIndexOf(final String aArg0) {
        return this.serviceTime.lastIndexOf(aArg0);
    }

    /**
     * @return
     * @see java.lang.String#length()
     */
    public int length() {
        return this.serviceTime.length();
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#matches(java.lang.String)
     */
    public boolean matches(final String aArg0) {
        return this.serviceTime.matches(aArg0);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#offsetByCodePoints(int, int)
     */
    public int offsetByCodePoints(final int aArg0, final int aArg1) {
        return this.serviceTime.offsetByCodePoints(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @param aArg2
     * @param aArg3
     * @param aArg4
     * @return
     * @see java.lang.String#regionMatches(boolean, int, java.lang.String, int, int)
     */
    public boolean regionMatches(final boolean aArg0, final int aArg1, final String aArg2, final int aArg3,
            final int aArg4) {
        return this.serviceTime.regionMatches(aArg0, aArg1, aArg2, aArg3, aArg4);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @param aArg2
     * @param aArg3
     * @return
     * @see java.lang.String#regionMatches(int, java.lang.String, int, int)
     */
    public boolean regionMatches(final int aArg0, final String aArg1, final int aArg2, final int aArg3) {
        return this.serviceTime.regionMatches(aArg0, aArg1, aArg2, aArg3);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#replace(char, char)
     */
    public String replace(final char aArg0, final char aArg1) {
        return this.serviceTime.replace(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#replace(java.lang.CharSequence, java.lang.CharSequence)
     */
    public String replace(final CharSequence aArg0, final CharSequence aArg1) {
        return this.serviceTime.replace(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#replaceAll(java.lang.String, java.lang.String)
     */
    public String replaceAll(final String aArg0, final String aArg1) {
        return this.serviceTime.replaceAll(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#replaceFirst(java.lang.String, java.lang.String)
     */
    public String replaceFirst(final String aArg0, final String aArg1) {
        return this.serviceTime.replaceFirst(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#split(java.lang.String, int)
     */
    public String[] split(final String aArg0, final int aArg1) {
        return this.serviceTime.split(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#split(java.lang.String)
     */
    public String[] split(final String aArg0) {
        return this.serviceTime.split(aArg0);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#startsWith(java.lang.String, int)
     */
    public boolean startsWith(final String aArg0, final int aArg1) {
        return this.serviceTime.startsWith(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#startsWith(java.lang.String)
     */
    public boolean startsWith(final String aArg0) {
        return this.serviceTime.startsWith(aArg0);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#subSequence(int, int)
     */
    public CharSequence subSequence(final int aArg0, final int aArg1) {
        return this.serviceTime.subSequence(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @param aArg1
     * @return
     * @see java.lang.String#substring(int, int)
     */
    public String substring(final int aArg0, final int aArg1) {
        return this.serviceTime.substring(aArg0, aArg1);
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#substring(int)
     */
    public String substring(final int aArg0) {
        return this.serviceTime.substring(aArg0);
    }

    /**
     * @return
     * @see java.lang.String#toCharArray()
     */
    public char[] toCharArray() {
        return this.serviceTime.toCharArray();
    }

    /**
     * @return
     * @see java.lang.String#toLowerCase()
     */
    public String toLowerCase() {
        return this.serviceTime.toLowerCase();
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#toLowerCase(java.util.Locale)
     */
    public String toLowerCase(final Locale aArg0) {
        return this.serviceTime.toLowerCase(aArg0);
    }

    /**
     * @return
     * @see java.lang.String#toString()
     */
    @Override
    public String toString() {
        return this.serviceTime.toString();
    }

    /**
     * @return
     * @see java.lang.String#toUpperCase()
     */
    public String toUpperCase() {
        return this.serviceTime.toUpperCase();
    }

    /**
     * @param aArg0
     * @return
     * @see java.lang.String#toUpperCase(java.util.Locale)
     */
    public String toUpperCase(final Locale aArg0) {
        return this.serviceTime.toUpperCase(aArg0);
    }

    /**
     * @return
     * @see java.lang.String#trim()
     */
    public String trim() {
        return this.serviceTime.trim();
    }

}
