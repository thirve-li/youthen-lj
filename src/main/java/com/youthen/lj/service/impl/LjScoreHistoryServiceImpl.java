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
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.lj.persistence.dao.LjScoreHistoryDao;
import com.youthen.lj.persistence.entity.LjScoreHistory;
import com.youthen.lj.service.LjScoreHistoryService;
import com.youthen.lj.service.dto.LjScoreHistoryDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljScoreHistoryService")
public class LjScoreHistoryServiceImpl implements LjScoreHistoryService {

    @Autowired
    LjScoreHistoryDao scoreHistoryDao;

    /**
     * @see com.youthen.lj.service.LjScoreHistoryService#getScoreHistoryList(com.youthen.lj.service.dto.LjScoreHistoryDto)
     */

    @Override
    public List<LjScoreHistoryDto> getScoreHistoryList(final LjScoreHistoryDto scoreHistoryDto) {
        String hql = "from LjScoreHistory where 1=1 ";
        final String orderBy = " order by createTime desc ";

        if (StringUtils.isNotEmpty(scoreHistoryDto.getUserId())) {
            hql += " and userId='" + scoreHistoryDto.getUserId() + "'";
        }

        if (scoreHistoryDto.getGotFrom() != null) {
            hql += " and gotFrom like'%" + scoreHistoryDto.getGotFrom() + "%'";
        }

        hql += orderBy;

        final List<LjScoreHistory> list =
                this.scoreHistoryDao.getByPage(hql, scoreHistoryDto.getGotoPage(), scoreHistoryDto.getPageSize());

        final ArrayList<LjScoreHistoryDto> arrayList = new ArrayList<LjScoreHistoryDto>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjScoreHistory dto : list) {
                final LjScoreHistoryDto historyDto = new LjScoreHistoryDto();
                BeanUtils.copyAllNullableProperties(dto, historyDto);
                arrayList.add(historyDto);
            }
        }
        return arrayList;
    }

    /**
     * @see com.youthen.lj.service.LjScoreHistoryService#getScoreHistoryCount(com.youthen.lj.service.dto.LjScoreHistoryDto)
     */

    @Override
    public int getScoreHistoryCount(final LjScoreHistoryDto scoreHistoryDto) {
        String hql = " from LjScoreHistory where 1=1 ";
        if (StringUtils.isNotEmpty(scoreHistoryDto.getUserId())) {
            hql += " and userId='" + scoreHistoryDto.getUserId() + "'";
        }
        if (scoreHistoryDto.getScore() != null) {
            hql += " and score='" + scoreHistoryDto.getScore() + "'";
        }
        if (scoreHistoryDto.getGotFrom() != null) {
            hql += " and gotFrom='" + scoreHistoryDto.getGotFrom() + "'";
        }

        return this.scoreHistoryDao.getCount(hql);
    }

    /**
     * @see com.youthen.lj.service.LjScoreHistoryService#insert(com.youthen.lj.service.dto.LjScoreHistoryDto)
     */

    @Override
    public Long insert(final LjScoreHistoryDto scoreHistoryDto) throws DuplicateKeyException {
        final LjScoreHistory history = new LjScoreHistory();
        BeanUtils.copyAllNullableProperties(scoreHistoryDto, history);
        return (Long) this.scoreHistoryDao.insert(history);

    }

    /**
     * @see com.youthen.lj.service.LjScoreHistoryService#getById(java.lang.Long)
     */

    @Override
    public LjScoreHistoryDto getById(final Long aId) {
        final LjScoreHistoryDto historyDto = new LjScoreHistoryDto();
        BeanUtils.copyAllProperties(this.scoreHistoryDao.getById(aId), historyDto);
        return historyDto;
    }

    /**
     * @see com.youthen.lj.service.LjScoreHistoryService#update(com.youthen.lj.service.dto.LjScoreHistoryDto)
     */

    @Override
    public LjScoreHistoryDto update(final LjScoreHistoryDto scoreHistoryDto) {
        final LjScoreHistory history = this.scoreHistoryDao.getById(scoreHistoryDto.getId());
        BeanUtils.copyNullableProperties(scoreHistoryDto, history);
        this.scoreHistoryDao.update(history);
        BeanUtils.copyProperties(history, scoreHistoryDto);
        return null;
    }

    /**
     * @see com.youthen.lj.service.LjScoreHistoryService#delete(com.youthen.lj.service.dto.LjScoreHistoryDto)
     */

    @Override
    public void delete(final LjScoreHistoryDto scoreHistoryDto) {
        final LjScoreHistory history = this.scoreHistoryDao.getById(scoreHistoryDto.getId());
        BeanUtils.copyNullableProperties(scoreHistoryDto, history);
        try {
            this.scoreHistoryDao.delete(history);
        } catch (final ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (final OptimisticLockStolenException e) {
            e.printStackTrace();
        }
    }

}
