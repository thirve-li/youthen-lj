// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.entity;

import java.util.Date;
import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.LoginUser;

/**
 * 缴费历史表。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjFeeHistory extends AbstractCommonEntity {

    /**
     * 。
     */
    private static final long serialVersionUID = 6470689788662052856L;

    /**
     * ID
     */
    private Long id;

    /**
     * 缴费人用户ID
     */
    private String userId;
    private LoginUser user;

    /**
     * 业主姓名
     */
    private String userName;

    /**
     * 房屋id
     */
    private Long roomId;

    /**
     * 单元代码
     */
    private String roomCode;

    /**
     * 房屋信息
     */
    private LjRoomInfo room;

    /**
     * 车位号
     */
    private String parkNo;

    /**
     * 最后的缴费期别
     */
    private Integer lastPeriod;

    /**
     * 付费状态 0未付费 1已付费
     * 订单状态 0未支付 1支付成功 2订单取消 3、订单失效
     */
    private Integer status;

    /**
     * 缴费月数
     */
    private Integer feeMonth;

    /**
     * 缴费金额
     */
    private double fee;

    /**
     * 缴费日期
     */
    private Date payDate;

    /**
     * 微信支付单号
     */
    private String payNo;

    /**
     * 费用类别 0：物业 1：车位费
     */
    private Integer type;

    /**
     * 微信支付 0：
     */
    private Integer payType;

    /**
     * 使用折扣计划ID
     */
    private Long cuponId;

    /**
     * 使用积分
     */
    private Long usedScore;

    /**
     * 备注
     */
    private String remark;

    /**
     * 微信支付ID
     */
    private String prepayId;

    /**
     * 交易号
     */
    private String transactionId;

    /**
     * 送货地址Id
     */
    private String addressId;

    /**
     * 送货地址
     */
    private String address;

    /**
     * 收货人
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverMobile;

    /**
     * 购买的商品ID和数量
     */
    private String goodsIds;

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
     * getter for userName.
     * 
     * @return userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * setter for userName.
     * 
     * @param aUserName userName
     */
    public void setUserName(final String aUserName) {
        this.userName = aUserName;
    }

    /**
     * getter for roomId.
     * 
     * @return roomId
     */
    public Long getRoomId() {
        return this.roomId;
    }

    /**
     * setter for roomId.
     * 
     * @param aRoomId roomId
     */
    public void setRoomId(final Long aRoomId) {
        this.roomId = aRoomId;
    }

    /**
     * getter for parkNo.
     * 
     * @return parkNo
     */
    public String getParkNo() {
        return this.parkNo;
    }

    /**
     * setter for parkNo.
     * 
     * @param aParkNo parkNo
     */
    public void setParkNo(final String aParkNo) {
        this.parkNo = aParkNo;
    }

    /**
     * getter for lastPeriod.
     * 
     * @return lastPeriod
     */
    public Integer getLastPeriod() {
        return this.lastPeriod;
    }

    /**
     * setter for lastPeriod.
     * 
     * @param aLastPeriod lastPeriod
     */
    public void setLastPeriod(final Integer aLastPeriod) {
        this.lastPeriod = aLastPeriod;
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
     * getter for feeMonth.
     * 
     * @return feeMonth
     */
    public Integer getFeeMonth() {
        return this.feeMonth;
    }

    /**
     * setter for feeMonth.
     * 
     * @param aFeeMonth feeMonth
     */
    public void setFeeMonth(final Integer aFeeMonth) {
        this.feeMonth = aFeeMonth;
    }

    /**
     * getter for fee.
     * 
     * @return fee
     */
    public double getFee() {
        return this.fee;
    }

    /**
     * setter for fee.
     * 
     * @param aFee fee
     */
    public void setFee(final double aFee) {
        this.fee = aFee;
    }

    /**
     * getter for payDate.
     * 
     * @return payDate
     */
    public Date getPayDate() {
        return this.payDate;
    }

    /**
     * setter for payDate.
     * 
     * @param aPayDate payDate
     */
    public void setPayDate(final Date aPayDate) {
        this.payDate = aPayDate;
    }

    /**
     * getter for payNo.
     * 
     * @return payNo
     */
    public String getPayNo() {
        return this.payNo;
    }

    /**
     * setter for payNo.
     * 
     * @param aPayNo payNo
     */
    public void setPayNo(final String aPayNo) {
        this.payNo = aPayNo;
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
     * getter for remark.
     * 
     * @return remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * setter for remark.
     * 
     * @param aRemark remark
     */
    public void setRemark(final String aRemark) {
        this.remark = aRemark;
    }

    /**
     * getter for cuponId.
     * 
     * @return cuponId
     */
    public Long getCuponId() {
        return this.cuponId;
    }

    /**
     * setter for cuponId.
     * 
     * @param aCuponId cuponId
     */
    public void setCuponId(final Long aCuponId) {
        this.cuponId = aCuponId;
    }

    /**
     * getter for usedScore.
     * 
     * @return usedScore
     */
    public Long getUsedScore() {
        return this.usedScore;
    }

    /**
     * setter for usedScore.
     * 
     * @param aUsedScore usedScore
     */
    public void setUsedScore(final Long aUsedScore) {
        this.usedScore = aUsedScore;
    }

    /**
     * getter for room.
     * 
     * @return room
     */
    public LjRoomInfo getRoom() {
        return this.room;
    }

    /**
     * setter for room.
     * 
     * @param aRoom room
     */
    public void setRoom(final LjRoomInfo aRoom) {
        this.room = aRoom;
    }

    /**
     * getter for rooCode.
     * 
     * @return rooCode
     */
    public String getRoomCode() {
        return this.roomCode;
    }

    /**
     * setter for aRoomCode.
     * 
     * @param aRoomCode aRoomCode
     */
    public void setRoomCode(final String aRoomCode) {
        this.roomCode = aRoomCode;
    }

    /**
     * getter for payType.
     * 
     * @return payType
     */
    public Integer getPayType() {
        return this.payType;
    }

    /**
     * setter for payType.
     * 
     * @param aPayType payType
     */
    public void setPayType(final Integer aPayType) {
        this.payType = aPayType;
    }

    /**
     * getter for prepayId.
     * 
     * @return prepayId
     */
    public String getPrepayId() {
        return this.prepayId;
    }

    /**
     * setter for prepayId.
     * 
     * @param aPrepayId prepayId
     */
    public void setPrepayId(final String aPrepayId) {
        this.prepayId = aPrepayId;
    }

    /**
     * getter for transactionId.
     * 
     * @return transactionId
     */
    public String getTransactionId() {
        return this.transactionId;
    }

    /**
     * setter for transactionId.
     * 
     * @param aTransactionId transactionId
     */
    public void setTransactionId(final String aTransactionId) {
        this.transactionId = aTransactionId;
    }

    /**
     * getter for address.
     * 
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * setter for address.
     * 
     * @param aAddress address
     */
    public void setAddress(final String aAddress) {
        this.address = aAddress;
    }

    /**
     * getter for receiverName.
     * 
     * @return receiverName
     */
    public String getReceiverName() {
        return this.receiverName;
    }

    /**
     * setter for receiverName.
     * 
     * @param aReceiverName receiverName
     */
    public void setReceiverName(final String aReceiverName) {
        this.receiverName = aReceiverName;
    }

    /**
     * getter for receiverMobile.
     * 
     * @return receiverMobile
     */
    public String getReceiverMobile() {
        return this.receiverMobile;
    }

    /**
     * setter for receiverMobile.
     * 
     * @param aReceiverMobile receiverMobile
     */
    public void setReceiverMobile(final String aReceiverMobile) {
        this.receiverMobile = aReceiverMobile;
    }

    /**
     * getter for goodsIds.
     * 
     * @return goodsIds
     */
    public String getGoodsIds() {
        return this.goodsIds;
    }

    /**
     * setter for goodsIds.
     * 
     * @param aGoodsIds goodsIds
     */
    public void setGoodsIds(final String aGoodsIds) {
        this.goodsIds = aGoodsIds;
    }

    /**
     * getter for addressId.
     * 
     * @return addressId
     */
    public String getAddressId() {
        return this.addressId;
    }

    /**
     * setter for addressId.
     * 
     * @param aAddressId addressId
     */
    public void setAddressId(final String aAddressId) {
        this.addressId = aAddressId;
    }

}
