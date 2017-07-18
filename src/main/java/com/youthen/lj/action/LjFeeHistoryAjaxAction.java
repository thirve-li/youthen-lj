// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.lj.persistence.entity.LjUserBuilding;
import com.youthen.lj.service.LjFeeHistoryService;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.lj.service.LjUserBuildingService;
import com.youthen.lj.service.dto.LjFeeHistoryDto;
import com.youthen.lj.service.dto.LjRoomInfoDto;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.LoginUserDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@SuppressWarnings("serial")
@Namespace("/lj-feeHistory")
@Controller
public class LjFeeHistoryAjaxAction extends AbstractAjaxAction {

    LjFeeHistoryDto dto;

    List<LjUserBuilding> userBuildingList;

    List<LjFeeHistoryDto> feeHistoryList;

    List<LoginUserDto> userList;

    int month;

    int userBuildingNo;
    int userUnitNo;
    int userRoomNo;
    String userParkNo;
    double unitPrice;

    @Autowired
    LjUserBuildingService userBuildingService;

    @Autowired
    LoginUserService loginUserService;

    @Autowired
    LjRoomInfoService roomInfoService;

    @Autowired
    LjFeeHistoryService feeHistoryService;

    /**
     * @see com.youthen.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    public String worthOfToAll() {
        return this.feeHistoryService.worthOfToAll();
    }

    public String getUserNameList() {
        try {
            this.userList = this.loginUserService.selectAll();
        } catch (final ObjectNotFoundException e) {
            e.printStackTrace();
        }
        final JSONArray json = new JSONArray();
        for (int i = 0; i < this.userList.size(); i++) {
            final JSONObject jo = new JSONObject();
            jo.put("title", this.userList.get(i).getName());
            jo.put("result", "{'userId':'" + this.userList.get(i).getUserId() + "'}");
            json.put(jo);
        }
        final String strJson = json.toString();
        System.out.println(strJson);
        return strJson;
    }

    public String getMsg() {
        String Msg = null;
        try {
            if (this.dto.getUserId() != null) {
                if (this.loginUserService.getUserByUserId(this.dto.getUserId()) != null) {

                } else {
                    Msg = "没有此用户";
                    return Msg;
                }
            }
            if (StringUtils.isEmpty(this.dto.getFee())) {
                Msg = "缴费金额不得为0";
                return Msg;
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return Msg;
    }

    /*
     * public String getAllUserName() {
     * this.dto.setUserId(" ");
     * this.userBuildingList = this.userBuildingService.getUserList(this.dto.getUserId());
     * return "";
     * }
     */

    public String[] getRoomOrCar() {
        String strUserBuildingNo = "0";
        /* String strUserUnitNo = "0"; */
        String strUserRoomNo = "0";
        this.userParkNo = "0";
        String lastPeriod = null;
        String Msg = null;
        try {
            if (this.loginUserService.getUserByUserId(this.dto.getUserId()) != null) {

                final String userName = this.loginUserService.getById(this.dto.getUserId()).getName();
                this.userBuildingList = this.userBuildingService.getRoomList(userName);
                final LjFeeHistoryDto feeDto = new LjFeeHistoryDto();
                if (this.dto.getType() != null) {
                    if (this.dto.getType() != 1) {
                        if (this.userBuildingList.size() > 0) {
                            this.userBuildingNo = this.userBuildingList.get(0).getRoomInfo().getBuildingNo();
                            /* this.userUnitNo = this.userBuildingList.get(0).getRoomInfo().getUnitNo(); */
                            this.userRoomNo = this.userBuildingList.get(0).getRoomInfo().getRoomNo();
                            strUserBuildingNo = this.userBuildingNo + "";
                            /* strUserUnitNo = this.userUnitNo + ""; */
                            strUserRoomNo = this.userRoomNo + "";
                            final String roomCode = "MLY-" + this.userBuildingNo + "-" + this.userRoomNo;
                            feeDto.setRoomCode(roomCode);
                            this.feeHistoryList = this.feeHistoryService.getFeeHistoryList(feeDto);
                            if (this.feeHistoryList.size() > 0) {
                                lastPeriod = this.feeHistoryList.get(0).getLastPeriod() + "";
                            }
                        }
                    } else {
                        /*
                         * if (userCarList.size() > 0) {
                         * this.userParkNo = userCarList.get(0).getParkNo();
                         * feeDto.setParkNo(this.userParkNo);
                         * this.feeHistoryList = this.feeHistoryService.getFeeHistoryList(feeDto);
                         * if (this.feeHistoryList.size() > 0) {
                         * lastPeriod = this.feeHistoryList.get(0).getLastPeriod() + "";
                         * }
                         * }
                         */
                    }
                }
            } else {
                Msg = "没有此用户";
            }
        } catch (final ObjectNotFoundException e) {
            e.printStackTrace();
        }

        return new String[] {Msg, strUserBuildingNo,/* strUserUnitNo, */strUserRoomNo, this.userParkNo, lastPeriod};
    }

    public double[] getRoomCarMoney() {
        final LjRoomInfoDto roomInfoDto = new LjRoomInfoDto();
        double money = 0.0;
        double sumMoney = 0.0;
        if (this.dto.getType() != null) {
            if (this.dto.getType() != 1) {
                final String roomCode = "MLY-" + this.dto.getBuildingNo() + "-" + this.dto.getRoomNo();
                if (StringUtils.isNotEmpty(roomCode)) {

                    roomInfoDto.setCode(roomCode);
                    final List<LjRoomInfoDto> list = this.roomInfoService.getRoomInfoList(roomInfoDto);
                    if (list.size() > 0) {
                        money = list.get(0).getTotalPrice();
                    }

                    if (this.month != 0) {
                        sumMoney = money * this.month;
                    }
                }
            } else {
                money = this.unitPrice;
                if (this.month != 0) {
                    sumMoney = money * this.month;
                }
            }
        }

        return new double[] {money, sumMoney};
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public LjFeeHistoryDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final LjFeeHistoryDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for userBuildingList.
     * 
     * @return userBuildingList
     */
    public List<LjUserBuilding> getUserBuildingList() {
        return this.userBuildingList;
    }

    /**
     * setter for userBuildingList.
     * 
     * @param aUserBuildingList userBuildingList
     */
    public void setUserBuildingList(final List<LjUserBuilding> aUserBuildingList) {
        this.userBuildingList = aUserBuildingList;
    }

    /**
     * getter for userBuildingNo.
     * 
     * @return userBuildingNo
     */
    public int getUserBuildingNo() {
        return this.userBuildingNo;
    }

    /**
     * setter for userBuildingNo.
     * 
     * @param aUserBuildingNo userBuildingNo
     */
    public void setUserBuildingNo(final int aUserBuildingNo) {
        this.userBuildingNo = aUserBuildingNo;
    }

    /**
     * getter for userUnitNo.
     * 
     * @return userUnitNo
     */
    public int getUserUnitNo() {
        return this.userUnitNo;
    }

    /**
     * setter for userUnitNo.
     * 
     * @param aUserUnitNo userUnitNo
     */
    public void setUserUnitNo(final int aUserUnitNo) {
        this.userUnitNo = aUserUnitNo;
    }

    /**
     * getter for userRoomNo.
     * 
     * @return userRoomNo
     */
    public int getUserRoomNo() {
        return this.userRoomNo;
    }

    /**
     * setter for userRoomNo.
     * 
     * @param aUserRoomNo userRoomNo
     */
    public void setUserRoomNo(final int aUserRoomNo) {
        this.userRoomNo = aUserRoomNo;
    }

    /**
     * getter for feeHistoryList.
     * 
     * @return feeHistoryList
     */
    public List<LjFeeHistoryDto> getFeeHistoryList() {
        return this.feeHistoryList;
    }

    /**
     * setter for feeHistoryList.
     * 
     * @param aFeeHistoryList feeHistoryList
     */
    public void setFeeHistoryList(final List<LjFeeHistoryDto> aFeeHistoryList) {
        this.feeHistoryList = aFeeHistoryList;
    }

    /**
     * getter for month.
     * 
     * @return month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * setter for month.
     * 
     * @param aMonth month
     */
    public void setMonth(final int aMonth) {
        this.month = aMonth;
    }

    /**
     * getter for userParkNo.
     * 
     * @return userParkNo
     */
    public String getUserParkNo() {
        return this.userParkNo;
    }

    /**
     * setter for userParkNo.
     * 
     * @param aUserParkNo userParkNo
     */
    public void setUserParkNo(final String aUserParkNo) {
        this.userParkNo = aUserParkNo;
    }

    /**
     * getter for userList.
     * 
     * @return userList
     */
    public List<LoginUserDto> getUserList() {
        return this.userList;
    }

    /**
     * setter for userList.
     * 
     * @param aUserList userList
     */
    public void setUserList(final List<LoginUserDto> aUserList) {
        this.userList = aUserList;
    }

    /**
     * getter for unitPrice.
     * 
     * @return unitPrice
     */
    public double getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * setter for unitPrice.
     * 
     * @param aUnitPrice unitPrice
     */
    public void setUnitPrice(final double aUnitPrice) {
        this.unitPrice = aUnitPrice;
    }

}
