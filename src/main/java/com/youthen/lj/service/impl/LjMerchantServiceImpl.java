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
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.lj.persistence.dao.LjMerchantDao;
import com.youthen.lj.persistence.entity.LjMerchant;
import com.youthen.lj.service.LjMerchantService;
import com.youthen.lj.service.dto.LjMerchantDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljMerchantService")
public class LjMerchantServiceImpl implements LjMerchantService {

    @Autowired
    private LjMerchantDao dao;

    /**
     * @see com.youthen.lj.service.LjMerchantService#getMerchantList(com.youthen.lj.service.dto.LjMerchantDto)
     */

    @Override
    public List<LjMerchantDto> getMerchantList(final LjMerchantDto aDto) {
        String hql = "from LjMerchant where 1=1";
        final String orderBy = "order by status desc, updTime desc";
        if (aDto.getStatus() != null) {
            hql += " and status='" + aDto.getStatus() + "'";
        }
        if (aDto.getBigTypeId() != null) {
            hql += " and bigTypeId='" + aDto.getBigTypeId() + "'";
        }

        if (aDto.getSmallTypeId() != null) {
            hql += " and smallTypeId='" + aDto.getSmallTypeId() + "'";
        }
        hql += orderBy;

        final List<LjMerchant> list = this.dao.getByPage(hql, aDto.getGotoPage(), aDto.getPageSize());

        final ArrayList<LjMerchantDto> result = new ArrayList<LjMerchantDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjMerchant entity : list) {
                final LjMerchantDto dto = new LjMerchantDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }

        return result;
    }

    /**
     * @see com.youthen.lj.service.LjMerchantService#getMerchantCount(com.youthen.lj.service.dto.LjMerchantDto)
     */

    @Override
    public int getMerchantCount(final LjMerchantDto aDto) {
        String hql = "from LjMerchant where 1=1";
        final String orderBy = "order by status desc, updTime desc";
        if (aDto.getStatus() != null) {
            hql += " and status='" + aDto.getStatus() + "'";
        }
        if (aDto.getBigTypeId() != null) {
            hql += " and bigTypeId='" + aDto.getBigTypeId() + "'";
        }

        if (aDto.getSmallTypeId() != null) {
            hql += " and smallTypeId='" + aDto.getSmallTypeId() + "'";
        }
        hql += orderBy;
        return this.dao.getCount(hql);
    }

    /**
     * @see com.youthen.lj.service.LjMerchantService#getById(java.lang.Long)
     */

    @Override
    public LjMerchantDto getById(final Long aId) {

        final LjMerchantDto dto = new LjMerchantDto();
        BeanUtils.copyAllProperties(this.dao.getById(aId), dto);
        return dto;
    }

    /**
     * @throws DuplicateKeyException
     * @see com.youthen.lj.service.LjMerchantService#insert(com.youthen.lj.service.dto.LjMerchantDto)
     */

    @Override
    @Transactional
    public Long insert(final LjMerchantDto aDto) throws DuplicateKeyException {

        final LjMerchant merchant = new LjMerchant();
        BeanUtils.copyAllNullableProperties(aDto, merchant);
        return (Long) this.dao.insert(merchant);
    }

    /**
     * @see com.youthen.lj.service.LjMerchantService#update(com.youthen.lj.service.dto.LjMerchantDto)
     */

    @Override
    @Transactional
    public LjMerchantDto update(final LjMerchantDto aDto) {
        final LjMerchant e = this.dao.getById(aDto.getId());
        BeanUtils.copyNullableProperties(aDto, e);
        this.dao.update(e);
        BeanUtils.copyProperties(e, aDto);
        return null;
    }

}
