// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.context.SessionContext;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.persistence.dao.LjRoomInfoDao;
import com.youthen.lj.persistence.entity.LjRoomInfo;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.lj.service.dto.LjRoomInfoDto;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.LoginUserService;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljRoomInfoService")
public class LjRoomInfoServiceImpl implements LjRoomInfoService {

    @Autowired
    private LjRoomInfoDao dao;

    @Autowired
    LoginUserService loginUserService;

    @Override
    public List<LjRoomInfoDto> getRoomInfoList(final LjRoomInfoDto aDto) {
        if (SessionContext.getUser() != null && SessionContext.getUser().getUserId() != null) {
            final LoginUser loginUser = this.loginUserService.getUserByUserId(SessionContext.getUser().getUserId());
        }
        String hql = "from LjRoomInfo where 1=1";
        if (aDto.getBuildingNo() != null) {
            hql += " and buildingNo='" + aDto.getBuildingNo() + "'";
        }
        if (aDto.getKbn() != null && aDto.getKbn().getId() != null) {
            hql += " and kbnId='" + aDto.getKbn().getId() + "'";
        }
        if (aDto.getUnitNo() != null) {
            hql += " and unitNo='" + aDto.getUnitNo() + "'";
        }
        if (aDto.getRoomNo() != null) {
            hql += " and roomNo='" + aDto.getRoomNo() + "'";
        }

        if (StringUtils.isNotEmpty(aDto.getCode())) {
            hql += " and code like'%" + aDto.getCode() + "%'";
        }

        final List<LjRoomInfo> list = this.dao.getByPage(hql, aDto.getGotoPage(), aDto.getPageSize());
        final ArrayList<LjRoomInfoDto> result = new ArrayList<LjRoomInfoDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjRoomInfo entity : list) {
                final LjRoomInfoDto dto = new LjRoomInfoDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }

        return result;
    }

    /**
     * @see com.youthen.lj.service.LjRoomInfoService#getRoomInfoCount(com.youthen.lj.service.dto.LjRoomInfoDto)
     */

    @Override
    public int getRoomInfoCount(final LjRoomInfoDto aDto) {
        if (SessionContext.getUser() != null && SessionContext.getUser().getUserId() != null) {
            final LoginUser loginUser = this.loginUserService.getUserByUserId(SessionContext.getUser().getUserId());
        }
        String hql = "from LjRoomInfo where 1=1";
        if (aDto.getBuildingNo() != null) {
            hql += " and buildingNo='" + aDto.getBuildingNo() + "'";
        }
        if (aDto.getUnitNo() != null) {
            hql += " and unitNo='" + aDto.getUnitNo() + "'";
        }
        if (aDto.getKbn() != null && aDto.getKbn().getId() != null) {
            hql += " and kbnId='" + aDto.getKbn().getId() + "'";
        }
        if (aDto.getRoomNo() != null) {
            hql += " and roomNo='" + aDto.getRoomNo() + "'";
        }

        if (StringUtils.isNotEmpty(aDto.getCode())) {
            hql += " and code like'%" + aDto.getCode() + "%'";
        }
        return this.dao.getCount(hql);
    }

    /**
     * @see com.youthen.lj.service.LjRoomInfoService#insert(com.youthen.lj.service.dto.LjRoomInfoDto)
     */

    @Override
    @Transactional
    public Long insert(final LjRoomInfoDto aDto) throws DuplicateKeyException {
        final LjRoomInfo roomInfo = new LjRoomInfo();
        BeanUtils.copyAllNullableProperties(aDto, roomInfo);
        return (Long) this.dao.insert(roomInfo);
    }

    /**
     * @see com.youthen.lj.service.LjRoomInfoService#update(com.youthen.lj.service.dto.LjRoomInfoDto)
     */

    @Override
    @Transactional
    public LjRoomInfoDto update(final LjRoomInfoDto aDto) {
        final LjRoomInfo e = this.dao.getById(aDto.getId());
        BeanUtils.copyNullableProperties(aDto, e);
        aDto.setMeasureOfArea(0);
        aDto.setPriceSquareMeter(0);
        this.dao.update(e);
        BeanUtils.copyProperties(e, aDto);
        return null;
    }

    /**
     * @see com.youthen.lj.service.LjRoomInfoService#createRoomInfo()
     */

    @Override
    @Transactional
    public void createRoomInfo() {
        final LjRoomInfoDto dto = new LjRoomInfoDto();
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 15; j++) {
                for (int k = 1; k <= 6; k++) {
                    final String roomNo = j + "0" + k;
                    final String roomCode = "羽山路383弄-" + i + "-" + roomNo;
                    dto.setCode(roomCode);
                    dto.setName("美丽苑");
                    dto.setBuildingNo(i);
                    dto.setRoomNo(Integer.parseInt(roomNo));
                    dto.setMeasureOfArea(240);
                    dto.setMeasureOfLive(220);
                    dto.setPriceSquareMeter(1.8);
                    if (getRoomInfoList(dto).size() < 1) {
                        try {
                            insert(dto);
                        } catch (final DuplicateKeyException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    /**
     * @see com.youthen.lj.service.LjRoomInfoService#getRoomInfo(java.lang.String)
     */

    @Override
    public LjRoomInfoDto getRoomInfo(final String aRoomCode) {

        final LjRoomInfoDto condition = new LjRoomInfoDto();
        condition.setCode(aRoomCode);
        final List<LjRoomInfoDto> list = this.getRoomInfoList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            final LjRoomInfoDto roomInfo = list.get(0);
            return roomInfo;
        }
        return null;
    }

    /**
     * @see com.youthen.lj.service.LjRoomInfoService#getById(java.lang.Long)
     */

    @Override
    public LjRoomInfoDto getById(final Long aId) {
        final LjRoomInfoDto dto = new LjRoomInfoDto();
        BeanUtils.copyAllProperties(this.dao.getById(aId), dto);
        return dto;
    }

    @Override
    @Transactional
    public Result delete(final Long aId) {
        final LjRoomInfo roomInfo = this.dao.getById(aId);
        try {
            this.dao.delete(roomInfo);
        } catch (final ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (final OptimisticLockStolenException e) {
            e.printStackTrace();
        }
        return null;
    }
}
