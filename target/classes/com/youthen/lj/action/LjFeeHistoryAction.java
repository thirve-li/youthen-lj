// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjFeeHistoryService;
import com.youthen.lj.service.dto.LjFeeHistoryDto;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.KbnDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Namespace("/lj-feeHistory")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/fee-history/list.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/fee-history/entity.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/fee-history/view.jsp"),
        @Result(name = "urgedPayMoneyList", location = "/WEB-INF/jsp/lj/admin/fee-history/urgedPayMoney.jsp"),
        @Result(name = "tolist", location = "/lj-feeHistory/list.action?dto.status=1", type = "redirect")
})
@Controller
public class LjFeeHistoryAction extends BaseAction {

    private LjFeeHistoryDto dto = new LjFeeHistoryDto();

    private List<LjFeeHistoryDto> feeHistoryList = new ArrayList<LjFeeHistoryDto>();

    private List<Integer> buildingNoList;
    private List<Integer> unitNoList;
    private List<Integer> roomNoList;
    private List<Integer> carCodeList;
    private List<Integer> monthList;

    private int month;

    private LoginUser loginUser;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private KbnService kbnService;

    @Autowired
    private LjFeeHistoryService feeHistoryService;

    @Action("list")
    public String list() {
        this.feeHistoryList = this.feeHistoryService.getFeeHistoryList(this.dto);
        final int listSize = this.feeHistoryService.getFeeHistoryCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);

        return "list";

    }

    @Action("entityInit")
    public String entityInit() {
        KbnDto kDto = new KbnDto();
        // 获得楼号
        kDto = this.kbnService.getById(149L);
        int num = Integer.parseInt(kDto.getCode());
        this.buildingNoList = new ArrayList<Integer>();
        for (int i = 1; i <= num; i++) {
            this.buildingNoList.add(i);
        }

        // 获得房间号
        kDto = this.kbnService.getById(151L);
        num = Integer.parseInt(kDto.getCode());
        this.roomNoList = new ArrayList<Integer>();
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= 5; j++) {
                final int roomNo = i * 100 + j;
                this.roomNoList.add(roomNo);
            }
        }

        // 获得停车位号
        kDto = this.kbnService.getById(152L);
        num = Integer.parseInt(kDto.getCode());
        this.carCodeList = new ArrayList<Integer>();
        for (int i = 1; i <= num; i++) {
            this.carCodeList.add(i);
        }

        // 获得月份数
        this.monthList = new ArrayList<Integer>();
        for (int i = 1; i <= 12; i++) {
            this.monthList.add(i);
        }

        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.feeHistoryService.getById(this.dto.getId());
        }

        return "entityInit";

    }

    @Action("saveFeeHistory")
    public String saveFeeHistory() {

        if (this.dto != null && this.dto.getId() != null) {
            this.feeHistoryService.update(this.dto);
        } else {
            /*
             * final List<LoginUser> loginUserList = this.loginUserService.getByName(this.dto.getUserId());
             * this.dto.setUserName(this.dto.getUserId());
             * this.dto.setUserId(loginUserList.get(0).getUserId());
             */
            final Date payDate = new Date();
            this.dto.setPayDate(payDate);
            String roomCode = null;
            if (this.dto.getType() == 0) {
                roomCode = "MLY-" + this.dto.getBuildingNo() + "-" + this.dto.getRoomNo();
            }
            this.dto.setRoomCode(roomCode);
            String lastPeriod = this.dto.getLastPeriod() + "";
            int beforeFour = Integer.parseInt(lastPeriod.substring(0, 4));
            int afterTwo = Integer.parseInt(lastPeriod.substring(4, lastPeriod.length()));
            afterTwo = afterTwo + this.month;
            beforeFour = beforeFour + (afterTwo / 12);
            String strAfterTwo = "";
            if (afterTwo % 12 < 10) {
                if (afterTwo % 12 == 0) {
                    strAfterTwo = "12";
                } else {
                    strAfterTwo = "0" + afterTwo % 12;
                }
            } else {
                strAfterTwo = "" + afterTwo % 12;
            }
            lastPeriod = beforeFour + strAfterTwo;
            this.dto.setLastPeriod(Integer.parseInt(lastPeriod));
            this.dto.setFeeMonth(this.month);
            try {
                this.feeHistoryService.insert(this.dto);
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
            }
        }

        return "tolist";
    }

    @Action("view")
    public String view() {

        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.feeHistoryService.getById(this.dto.getId());
        }

        return "view";

    }

    @Action("deleteFeeHistory")
    public String delete() {

        this.feeHistoryService.delete(this.dto.getId());

        return "tolist";
    }

    @Action("urgedPayMoneyList")
    public String urgedPayMoneyList() {

        return "urgedPayMoneyList";
    }

    /**
     * getter for loginUser.
     * 
     * @return loginUser
     */
    public LoginUser getLoginUser() {
        return this.loginUser;
    }

    /**
     * setter for loginUser.
     * 
     * @param aLoginUser loginUser
     */
    public void setLoginUser(final LoginUser aLoginUser) {
        this.loginUser = aLoginUser;
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
     * getter for buildingNoList.
     * 
     * @return buildingNoList
     */
    public List<Integer> getBuildingNoList() {
        return this.buildingNoList;
    }

    /**
     * setter for buildingNoList.
     * 
     * @param aBuildingNoList buildingNoList
     */
    public void setBuildingNoList(final List<Integer> aBuildingNoList) {
        this.buildingNoList = aBuildingNoList;
    }

    /**
     * getter for unitNoList.
     * 
     * @return unitNoList
     */
    public List<Integer> getUnitNoList() {
        return this.unitNoList;
    }

    /**
     * setter for unitNoList.
     * 
     * @param aUnitNoList unitNoList
     */
    public void setUnitNoList(final List<Integer> aUnitNoList) {
        this.unitNoList = aUnitNoList;
    }

    /**
     * getter for roomNoList.
     * 
     * @return roomNoList
     */
    public List<Integer> getRoomNoList() {
        return this.roomNoList;
    }

    /**
     * setter for roomNoList.
     * 
     * @param aRoomNoList roomNoList
     */
    public void setRoomNoList(final List<Integer> aRoomNoList) {
        this.roomNoList = aRoomNoList;
    }

    /**
     * getter for carCodeList.
     * 
     * @return carCodeList
     */
    public List<Integer> getCarCodeList() {
        return this.carCodeList;
    }

    /**
     * setter for carCodeList.
     * 
     * @param aCarCodeList carCodeList
     */
    public void setCarCodeList(final List<Integer> aCarCodeList) {
        this.carCodeList = aCarCodeList;
    }

    /**
     * getter for monthList.
     * 
     * @return monthList
     */
    public List<Integer> getMonthList() {
        return this.monthList;
    }

    /**
     * setter for monthList.
     * 
     * @param aMonthList monthList
     */
    public void setMonthList(final List<Integer> aMonthList) {
        this.monthList = aMonthList;
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

}
