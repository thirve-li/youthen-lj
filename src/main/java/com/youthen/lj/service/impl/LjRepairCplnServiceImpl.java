// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.impl;

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
import com.youthen.lj.persistence.dao.LjRepairCplnDao;
import com.youthen.lj.persistence.entity.LjRepairCpln;
import com.youthen.lj.service.LjRepairCplnService;
import com.youthen.lj.service.dto.LjRepairCplnDto;
import com.youthen.master.persistence.dao.LoginUserDao;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.util.CommonUtil;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljRepairCplnService")
public class LjRepairCplnServiceImpl implements LjRepairCplnService {

    @Autowired
    private LjRepairCplnDao ljRepairCplnDao;

    @Autowired
    LoginUserDao loginUserDao;

    @Autowired
    LoginUserService loginUserService;

    /**
     * @see com.youthen.lj.service.LjRepairCplnService#getRepairCplnList(com.youthen.lj.service.dto.LjRepairCplnDto)
     */

    @SuppressWarnings("static-access")
    @Override
    public List<LjRepairCplnDto> getRepairCplnList(final LjRepairCplnDto aRepairCplnDto) {
        final CommonUtil cu = new CommonUtil();
        String hql = "from LjRepairCpln where 1=1 ";

        final String orderByString = " order by reportTime desc , status asc ";

        if (StringUtils.isNotEmpty(aRepairCplnDto.getYear())) {
            hql +=
                    " and DATEPART(year,reportTime)= " + aRepairCplnDto.getYear();
        }

        if (StringUtils.isNotEmpty(aRepairCplnDto.getMonth())) {
            hql += " and DATEPART(month,reportTime)= " + aRepairCplnDto.getMonth();
        }

        if (aRepairCplnDto.getRoomCode() != null) {
            hql += " and roomCode like '%" + aRepairCplnDto.getRoomCode() + "%'";
        }

        if (aRepairCplnDto.getStatus() != null) {
            hql += " and status = '" + aRepairCplnDto.getStatus() + "'";
        }

        if (aRepairCplnDto.getType() != null) {
            hql += " and type = '" + aRepairCplnDto.getType() + "'";
        }

        if (aRepairCplnDto.getServiceMark() != null) {
            hql += " and serviceMark = '" + aRepairCplnDto.getServiceMark() + "'";
        }

        if (aRepairCplnDto.getSpeedMark() != null) {
            hql += " and speedMark = '" + aRepairCplnDto.getSpeedMark() + "'";
        }

        if (StringUtils.isNotEmpty(aRepairCplnDto.getReporterId())) {
            hql += " and reporterId = '" + aRepairCplnDto.getReporterId() + "'";
        }

        hql += orderByString;

        final List<LjRepairCpln> list =
                this.ljRepairCplnDao.getByPage(hql, aRepairCplnDto.getGotoPage(), aRepairCplnDto.getPageSize());
        final ArrayList<LjRepairCplnDto> result = new ArrayList<LjRepairCplnDto>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjRepairCpln entity : list) {
                final LjRepairCplnDto dto = new LjRepairCplnDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }

        return result;
    }

    /**
     * @see com.youthen.lj.service.LjRepairCplnService#getRepairCplnCount(com.youthen.lj.service.dto.LjRepairCplnDto)
     */

    @SuppressWarnings("static-access")
    @Override
    public int getRepairCplnCount(final LjRepairCplnDto aRepairCplnDto) {
        final CommonUtil cu = new CommonUtil();
        String hql = "from LjRepairCpln where 1=1 ";

        final String orderByString = " order by status asc,reportTime desc";
        if (aRepairCplnDto.getReportTime() != null) {
            hql += " and reportTime between '" + cu.dateToStrLong(aRepairCplnDto.getReportTime()) + "'"
                    + " and '" + cu.dateToStr(aRepairCplnDto.getReportTime()) + " 23:59:59'  ";
        }

        if (aRepairCplnDto.getRoomCode() != null) {
            hql += " and roomCode like '%" + aRepairCplnDto.getRoomCode() + "%'";
        }

        if (aRepairCplnDto.getStatus() != null) {
            hql += " and status ='" + aRepairCplnDto.getStatus() + "'";
        }

        if (aRepairCplnDto.getType() != null) {
            hql += " and type = '" + aRepairCplnDto.getType() + "'";
        }

        if (aRepairCplnDto.getServiceMark() != null) {
            hql += " and serviceMark = '" + aRepairCplnDto.getServiceMark() + "'";
        }

        if (aRepairCplnDto.getSpeedMark() != null) {
            hql += " and speedMark = '" + aRepairCplnDto.getSpeedMark() + "'";
        }
        hql += orderByString;
        return this.ljRepairCplnDao.getCount(hql);
    }

    /**
     * @see com.youthen.lj.service.LjRepairCplnService#insert(com.youthen.lj.service.dto.LjRepairCplnDto)
     */

    @Override
    @Transactional
    public Long insert(final LjRepairCplnDto aRepairCplnDto) throws DuplicateKeyException {
        try {
            final LjRepairCpln repairCpln = new LjRepairCpln();
            BeanUtils.copyAllNullableProperties(aRepairCplnDto, repairCpln);
            repairCpln.setReportTime(new Date());
            return (Long) this.ljRepairCplnDao.insert(repairCpln);

        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @see com.youthen.lj.service.LjRepairCplnService#getById(java.lang.Long)
     */

    @Override
    public LjRepairCplnDto getById(final Long aId) {
        final LjRepairCplnDto dto = new LjRepairCplnDto();
        BeanUtils.copyAllProperties(this.ljRepairCplnDao.getById(aId), dto);
        return dto;
    }

    /**
     * @see com.youthen.lj.service.LjRepairCplnService#update(com.youthen.lj.service.dto.LjRepairCplnDto)
     */

    @Override
    @Transactional
    public LjRepairCplnDto update(final LjRepairCplnDto aRepairCplnDto) {
        final LjRepairCpln e = this.ljRepairCplnDao.getById(aRepairCplnDto.getId());
        if (aRepairCplnDto.getStatus() != null) {
            if (aRepairCplnDto.getStatus() == 2) {
                aRepairCplnDto.setFinishTime(new Date());
            }
        }
        BeanUtils.copyNullableProperties(aRepairCplnDto, e);
        this.ljRepairCplnDao.update(e);
        BeanUtils.copyProperties(e, aRepairCplnDto);
        return aRepairCplnDto;
    }

    /**
     * @see com.youthen.lj.service.LjRepairCplnService#delete(com.youthen.lj.service.dto.LjRepairCplnDto)
     */

    @Override
    @Transactional
    public Result delete(final Long id) {
        final LjRepairCpln e = this.ljRepairCplnDao.getById(id);
        try {
            this.ljRepairCplnDao.delete(e);
        } catch (final ObjectNotFoundException e1) {
            e1.printStackTrace();
        } catch (final OptimisticLockStolenException e1) {
            e1.printStackTrace();
        }
        return null;

    }

}
