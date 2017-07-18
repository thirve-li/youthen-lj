// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.app.logic.LjAppLogic;
import com.youthen.lj.app.service.LjAppService;
import com.youthen.lj.persistence.dao.LjFeeHistoryDao;
import com.youthen.lj.persistence.entity.LjFeeHistory;
import com.youthen.lj.persistence.entity.LjUserBuilding;
import com.youthen.lj.service.LjFeeHistoryService;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.lj.service.LjUserBuildingService;
import com.youthen.lj.service.dto.LjFeeHistoryDto;
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
@Service(value = "ljFeeHistoryService")
public class LjFeeHistoryServiceImpl implements LjFeeHistoryService {

    @Autowired
    private LjFeeHistoryDao dao;

    @Autowired
    private LjUserBuildingService userBuildingService;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private LjRoomInfoService roomInfoService;

    @Autowired
    private LjAppService appService;

    @Autowired
    private LjAppLogic appLogic;

    @Override
    public List<LjFeeHistoryDto> getFeeHistoryList(final LjFeeHistoryDto aFeeHistory) {

        final String hql = this.getHql(aFeeHistory);

        final List<LjFeeHistory> list = this.dao.getByPage(hql, aFeeHistory.getGotoPage(), aFeeHistory.getPageSize());

        final ArrayList<LjFeeHistoryDto> result = new ArrayList<LjFeeHistoryDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjFeeHistory entity : list) {
                final LjFeeHistoryDto dto = new LjFeeHistoryDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public int getFeeHistoryCount(final LjFeeHistoryDto aFeeHistory) {
        final String hql = this.getHql(aFeeHistory);
        return this.dao.getCount(hql);
    }

    private String getHql(final LjFeeHistoryDto aFeeHistory) {
        String hql = "from LjFeeHistory where 1=1 ";

        if (StringUtils.isNotEmpty(aFeeHistory.getUserId())) {
            hql += " and userId = '" + aFeeHistory.getUserId() + "'";
        }
        if (StringUtils.isNotEmpty(aFeeHistory.getUserName())) {
            hql += " and user.name like '%" + aFeeHistory.getUserName() + "%'";
        }

        if (aFeeHistory.getFeeMonth() != null) {
            hql += " and feeMonth='" + aFeeHistory.getFeeMonth() + "'";
        }

        if (aFeeHistory.getLastPeriod() != null) {
            hql += " and lastPeriod='" + aFeeHistory.getLastPeriod() + "'";
        }

        if (StringUtils.isNotEmpty(aFeeHistory.getRoomCode())) {
            hql += " and roomCode='" + aFeeHistory.getRoomCode() + "'";
        }

        if (StringUtils.isNotEmpty(aFeeHistory.getParkNo())) {
            hql += " and parkNo='" + aFeeHistory.getParkNo() + "'";
        }
        if (aFeeHistory.getType() != null && aFeeHistory.getType().intValue() == 10) {
            hql += " and( type='0' or type='1')";
        } else if (aFeeHistory.getType() != null) {
            hql += " and type='" + aFeeHistory.getType() + "'";
        } else if (aFeeHistory.getType() == null) {
            hql += " and( type='0' or type='1')";
        }

        if (aFeeHistory.getStatus() != null) {
            hql += " and status='" + aFeeHistory.getStatus() + "'";
        }

        if (StringUtils.isNotEmpty(aFeeHistory.getPayNo())) {
            hql += " and payNo='" + aFeeHistory.getPayNo() + "'";
        }

        final String orderByString = " order by payDate desc, lastPeriod asc,roomCode desc,userId desc ";

        hql += orderByString;

        return hql;
    }

    @Override
    @Transactional
    public Long insert(final LjFeeHistoryDto aFeeHistory) throws DuplicateKeyException {
        final LjFeeHistory feeHistory = new LjFeeHistory();
        BeanUtils.copyAllNullableProperties(aFeeHistory, feeHistory);
        return (Long) this.dao.insert(feeHistory);
    }

    @Override
    public LjFeeHistoryDto getById(final Long aId) {
        final LjFeeHistoryDto fdto = new LjFeeHistoryDto();
        BeanUtils.copyAllProperties(this.dao.getById(aId), fdto);
        return fdto;
    }

    @Override
    @Transactional
    public LjFeeHistoryDto update(final LjFeeHistoryDto aFeeHistory) {
        final LjFeeHistory e = this.dao.getById(aFeeHistory.getId());
        BeanUtils.copyNullableProperties(aFeeHistory, e);
        this.dao.update(e);
        BeanUtils.copyProperties(e, aFeeHistory);
        return aFeeHistory;
    }

    @Override
    @Transactional
    public Result delete(final Long id) {
        final LjFeeHistory e = this.dao.getById(id);
        try {
            this.dao.delete(e);
        } catch (final ObjectNotFoundException e1) {
            e1.printStackTrace();
        } catch (final OptimisticLockStolenException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LjFeeHistoryDto> getByUserName(final String aUserName) {
        final String hql = "from LjFeeHistory where user.name='" + aUserName + "' ";
        final List<LjFeeHistory> list = this.dao.getByHql(hql);
        final ArrayList<LjFeeHistoryDto> result = new ArrayList<LjFeeHistoryDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjFeeHistory entity : list) {
                final LjFeeHistoryDto dto = new LjFeeHistoryDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }
        return result;
    }

    /**
     * @see com.youthen.lj.service.LjFeeHistoryService#createBilling()
     */

    @Override
    @Transactional
    public void creatBilling() {
        final List<LjUserBuilding> buildingList = this.userBuildingService.getUserRoomList("");

        for (int i = 0; i < buildingList.size(); i++) {
            final LjFeeHistoryDto dto = new LjFeeHistoryDto();
            dto.setUserId(buildingList.get(i).getUserId());
            dto.setRoomCode(buildingList.get(i).getRoomInfo().getCode());
            final int lastperiod =
                    Integer.parseInt(getLastPeriodByRoomCode(buildingList.get(i).getRoomInfo().getCode()));
            dto.setLastPeriod(lastperiod);
            dto.setPayDate(new Date());
            dto.setType(0);
            final double feemoney =
                    buildingList.get(i).getRoomInfo().getTotalPrice();
            dto.setFee(String.valueOf(feemoney));
            dto.setFeeMonth(1);
            try {
                insert(dto);
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
            }
        }

        /*
         * final List<LjUserCar> carList = this.userCarService.getUserList("");
         * for (int i = 0; i < carList.size(); i++) {
         * final LjFeeHistoryDto dto = new LjFeeHistoryDto();
         * dto.setUserId(carList.get(i).getUserId());
         * final int lastperiod =
         * Integer.parseInt(getLastPeriodByParkNo(carList.get(i).getParkNo()));
         * dto.setLastPeriod(lastperiod);
         * dto.setParkNo(carList.get(i).getParkNo());
         * dto.setPayDate(new Date());
         * dto.setFee(100);
         * dto.setType(1);
         * dto.setFeeMonth(1);
         * try {
         * insert(dto);
         * } catch (final DuplicateKeyException e) {
         * e.printStackTrace();
         * }
         * }
         */
    }

    /**
     * @see com.youthen.lj.service.LjFeeHistoryService#getLastPeriodByRoomCode(java.lang.String)
     */

    @Override
    public String getLastPeriodByRoomCode(final String aRoomCode) {
        final String sql = "select max(lastPeriod) from LjFeeHistory where roomCode='" + aRoomCode + "'";
        final List list = this.dao.getByHql(sql);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.get(0) != null) {
                return list.get(0).toString();
            } else {
                return "201512";
            }
        }
        return null;
    }

    /**
     * @see com.youthen.lj.service.LjFeeHistoryService#getLastPeriodByParkNo(int)
     */

    @Override
    public String getLastPeriodByParkNo(final String aParkNo) {
        final String sql = "select max(lastPeriod) from LjFeeHistory where parkNo='" + aParkNo + "'";
        final List list = this.dao.getByHql(sql);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.get(0) != null) {
                return list.get(0).toString();
            } else {
                return "201512";
            }
        }
        return null;
    }

    /**
     * @see com.youthen.lj.service.LjFeeHistoryService#worthOfToAll()
     */

    @Override
    public String worthOfToAll() {
        String Msg = null;
        String returnMsg = "";
        final List<String> msgList = new ArrayList<String>();
        final List<LjUserBuilding> userBuildingList = this.userBuildingService.getUserRoomList("");
        // final List<LjUserCar> userCarList = this.userCarService.getUserList("");
        for (int i = 0; i < userBuildingList.size(); i++) {

            if (this.appLogic.isHouseExisted(userBuildingList.get(i).getRoomInfo().getCode())) {
                final String lastPeriod =
                        this.appLogic.getHouseLastPeriod(userBuildingList.get(i).getRoomInfo().getCode());
                final SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMM");
                if (Integer.parseInt(lastPeriod) < Integer.parseInt(sdformat.format(new Date()))) {

                    Msg = userBuildingList.get(i).getUserId() +
                            "亲爱的美丽苑业主"
                            + userBuildingList.get(i).getUser().getName()
                            + "您好，您的房屋编号为"
                            + userBuildingList.get(i).getRoomInfo().getCode()
                            + "的物业费只交到"
                            + lastPeriod.substring(0, 4)
                            + "年"
                            + lastPeriod.substring(4, lastPeriod.length()) + "月，请您尽快续费";
                    msgList.add(Msg);
                }
            }
            /*
             * 删除重复房屋编号的
             * for (int j = i + 1; j < userBuildingList.size(); j++) {
             * if (userBuildingList.get(i).getRoomInfoId().equals(userBuildingList.get(j).getRoomInfoId())) {
             * userBuildingList.remove(j);
             * j = i;
             * }
             * }
             */
        }

        /*
         * for (int i = 0; i < userCarList.size(); i++) {
         * if (this.appLogic.isParkNoExisted(userCarList.get(i).getParkNo())) {
         * final String lastPeriod =
         * this.appLogic.getParkLastPeriod(userCarList.get(i).getParkNo());
         * final SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMM");
         * if (Integer.parseInt(lastPeriod) < Integer.parseInt(sdformat.format(new Date()))) {
         * Msg = userCarList.get(i).getUserId() +
         * "亲爱的美丽苑业主"
         * + userCarList.get(i).getUser().getName()
         * + "您好，您的车位编号为"
         * + userCarList.get(i).getParkNo()
         * + "的停车费只交到"
         * + lastPeriod.substring(0, 4)
         * + "年"
         * + lastPeriod.substring(4, lastPeriod.length())
         * + "月，请您尽快续费";
         * msgList.add(Msg);
         * }
         * }
         * 删除重复车位
         * for (int j = i + 1; j < userCarList.size(); j++) {
         * if (userCarList.get(i).getParkNo().equals(userCarList.get(j).getParkNo())) {
         * userCarList.remove(j);
         * j = i;
         * }
         * }
         * }
         * }
         */

        /*
         * for (int i = 0; i < userCarList.size(); i++) {
         * final LjFeeHistoryDto feeDto = new LjFeeHistoryDto();
         * feeDto.setParkNo(userCarList.get(i).getParkNo());
         * final List<LjFeeHistoryDto> feeList = getFeeHistoryList(feeDto);
         * if (feeList != null) {
         * Msg =
         * "亲爱的美丽苑业主"
         * + feeList.get(0).getUser().getName()
         * + "您好，您的车位编号为"
         * + feeList.get(0).getPayNo()
         * + "的停车费只交到"
         * + feeList.get(0).getLastPeriod().toString().substring(0, 4)
         * + "年"
         * + feeList.get(0).getLastPeriod().toString()
         * .substring(4, feeList.get(0).getLastPeriod().toString().length()) + "月，请您尽快续费";
         * msgList.add(Msg);
         * }
         * }
         */

        for (int i = 0; i < msgList.size(); i++) {
            this.appService.sendSMS(msgList.get(i).substring(0, 11),
                    msgList.get(i).substring(11, msgList.get(i).length()), "78681");
        }

        for (int i = 0; i < msgList.size(); i++) {
            for (int j = i + 1; j < msgList.size(); j++) {
                if (msgList.get(i).substring(0, 11).equals(msgList.get(j).substring(0, 11))) {
                    msgList.remove(j);
                    j = i;
                }
            }
        }
        if (msgList.size() > 0) {
            returnMsg = "催缴短信已发送给";
        }
        for (int i = 0; i < msgList.size(); i++) {
            try {
                final LoginUserDto loginUserDto = this.loginUserService.getById(msgList.get(i).substring(0, 11));
                returnMsg = returnMsg + loginUserDto.getName() + "、";
            } catch (final ObjectNotFoundException e) {
                e.printStackTrace();
            }

        }
        returnMsg = returnMsg.substring(0, returnMsg.length() - 1);

        return returnMsg;
    }
}
