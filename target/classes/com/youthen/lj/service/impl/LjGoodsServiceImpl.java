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
import com.youthen.lj.persistence.dao.LjGoodsDao;
import com.youthen.lj.persistence.entity.LjGoods;
import com.youthen.lj.service.LjGoodsService;
import com.youthen.lj.service.dto.LjGoodsDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljGoodsService")
public class LjGoodsServiceImpl implements LjGoodsService {

    @Autowired
    private LjGoodsDao dao;

    @Override
    public List<LjGoodsDto> getGoodsList(final LjGoodsDto aDto) {
        String hql = "from LjGoods where 1=1";
        final String orderBy = "order by status desc, createTime desc";
        if (aDto.getShopId() != null) {
            hql += " and shopId='" + aDto.getShopId() + "'";
        }
        if (aDto.getStatus() != null) {
            hql += " and status='" + aDto.getStatus() + "'";
        }
        if (aDto.getBigGoodsTypeId() != null) {
            hql += " and bigGoodsTypeId='" + aDto.getBigGoodsTypeId() + "'";
        }
        if (aDto.getSmallGoodsTypeId() != null) {
            hql += " and smallGoodsTypeId='" + aDto.getSmallGoodsTypeId() + "'";
        }
        if (aDto.getIsSecond() != -1) {
            hql += " and isSecond='" + aDto.getIsSecond() + "'";
        }
        if (aDto.getCreaterId() != null && aDto.getCreaterId() != "") {
            hql += " and createrId='" + aDto.getCreaterId() + "'";
        }
        hql += orderBy;

        final List<LjGoods> list = this.dao.getByPage(hql, aDto.getGotoPage(), aDto.getPageSize());
        final ArrayList<LjGoodsDto> result = new ArrayList<LjGoodsDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjGoods entity : list) {
                final LjGoodsDto dto = new LjGoodsDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public int getGoodsCount(final LjGoodsDto aDto) {
        String hql = "from LjGoods where 1=1";
        final String orderBy = "order by status desc, createTime";
        if (aDto.getStatus() != null) {
            hql += " and status='" + aDto.getStatus() + "'";
        }
        if (aDto.getBigGoodsTypeId() != null) {
            hql += " and bigGoodsTypeId='" + aDto.getBigGoodsTypeId() + "'";
        }
        if (aDto.getSmallGoodsTypeId() != null) {
            hql += " and smallGoodsTypeId='" + aDto.getSmallGoodsTypeId() + "'";
        }
        if (aDto.getIsSecond() != -1) {
            hql += " and isSecond='" + aDto.getIsSecond() + "'";
        }
        hql += orderBy;
        return this.dao.getCount(hql);
    }

    @Override
    public LjGoodsDto getById(final Long aId) {
        final LjGoodsDto dto = new LjGoodsDto();
        final LjGoods entity = this.dao.getById(aId);
        if (entity != null) {
            BeanUtils.copyAllProperties(entity, dto);
            return dto;
        }
        return null;
    }

    @Override
    @Transactional
    public Long insert(final LjGoodsDto aDto) throws DuplicateKeyException {
        final LjGoods goods = new LjGoods();
        BeanUtils.copyAllNullableProperties(aDto, goods);
        return (Long) this.dao.insert(goods);
    }

    @Override
    @Transactional
    public LjGoodsDto update(final LjGoodsDto aDto) {
        final LjGoods e = this.dao.getById(aDto.getId());
        BeanUtils.copyNullableProperties(aDto, e);
        this.dao.update(e);
        BeanUtils.copyProperties(e, aDto);
        return null;
    }

}
