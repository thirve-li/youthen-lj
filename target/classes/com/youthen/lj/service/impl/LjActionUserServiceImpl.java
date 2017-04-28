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
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.lj.persistence.dao.LjActionUserDao;
import com.youthen.lj.persistence.entity.LjActionUser;
import com.youthen.lj.service.LjActionUserService;
import com.youthen.lj.service.dto.LjActionUserDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljActionUserService")
public class LjActionUserServiceImpl implements LjActionUserService {

    @Autowired
    private LjActionUserDao dao;

    /**
     * @see com.youthen.lj.service.LjActionUserService#getActionUserList(com.youthen.lj.service.dto.LjActionUserDto)
     */

    @Override
    public List<LjActionUserDto> getActionUserList(final LjActionUserDto aDto) {

        String hql = "from LjActionUser where 1=1";
        if (StringUtils.isNotEmpty(aDto.getUserId())) {
            hql += " and userId='" + aDto.getUserId() + "'";
        }
        if (aDto.getType() != null) {
            hql += " and type='" + aDto.getType() + "'";
        }
        if (aDto.getActionId() != null) {
            hql += " and actionId='" + aDto.getActionId() + "'";
        }
        hql += " order by actionId desc";
        final List<LjActionUser> list = this.dao.getByHql(hql);
        final ArrayList<LjActionUserDto> result = new ArrayList<LjActionUserDto>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjActionUser entity : list) {
                final LjActionUserDto dto = new LjActionUserDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }
        return result;
    }

    /**
     * @see com.youthen.lj.service.LjActionUserService#insert(com.youthen.lj.service.dto.LjActionUserDto)
     */

    @Override
    @Transactional
    public Long insert(final LjActionUserDto aDto) throws DuplicateKeyException {
        final LjActionUser actionUser = new LjActionUser();
        BeanUtils.copyAllNullableProperties(aDto, actionUser);
        return (Long) this.dao.insert(actionUser);
    }

    /**
     * @see com.youthen.lj.service.LjActionUserService#delete(com.youthen.lj.service.dto.LjActionUserDto)
     */

    @Override
    @Transactional
    public void delete(final LjActionUserDto aDto) throws ObjectNotFoundException, OptimisticLockStolenException {
        if (aDto.getId() != null) {
            final LjActionUser actionUser = this.dao.getById(aDto.getId());
            this.dao.delete(actionUser);
        }
    }

}
