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
import com.youthen.lj.persistence.dao.LjMsgTipDao;
import com.youthen.lj.persistence.entity.LjMsgTip;
import com.youthen.lj.service.LjMsgTipService;
import com.youthen.lj.service.dto.LjMsgTipDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljMsgTipService")
public class LjMsgTipServiceImpl implements LjMsgTipService {

    @Autowired
    private LjMsgTipDao dao;

    /**
     * @see com.youthen.lj.service.LjMsgTipService#getMsgTipList(com.youthen.lj.service.dto.LjMsgTipDto)
     */

    @Override
    public List<LjMsgTipDto> getMsgTipList(final LjMsgTipDto aDto) {
        if (StringUtils.isEmpty(aDto.getUserId())) {
            return null;
        }
        String hql = "from LjMsgTip where 1=1";
        if (StringUtils.isNotEmpty(aDto.getUserId())) {
            hql += " and userId='" + aDto.getUserId() + "'";
        }

        if (aDto.getStatus() != null) {
            if (aDto.getStatus().intValue() == 0) {
                hql += " and (status='" + aDto.getStatus() + "' or status=null)";
            } else {
                hql += " and status='" + aDto.getStatus() + "'";
            }
        }

        hql += " order by id desc";
        final List<LjMsgTip> list = this.dao.getByHql(hql);
        final ArrayList<LjMsgTipDto> result = new ArrayList<LjMsgTipDto>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjMsgTip entity : list) {
                final LjMsgTipDto dto = new LjMsgTipDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }
        return result;
    }

    /**
     * @see com.youthen.lj.service.LjMsgTipService#insert(com.youthen.lj.service.dto.LjMsgTipDto)
     */

    @Override
    @Transactional
    public Long insert(final LjMsgTipDto aDto) throws DuplicateKeyException {
        final LjMsgTip msg = new LjMsgTip();
        msg.setStatus(0);
        BeanUtils.copyAllNullableProperties(aDto, msg);
        return (Long) this.dao.insert(msg);
    }

    /**
     * @throws OptimisticLockStolenException
     * @throws ObjectNotFoundException
     * @see com.youthen.lj.service.LjMsgTipService#delete(com.youthen.lj.service.dto.LjMsgTipDto)
     */

    @Override
    @Transactional
    public void delete(final LjMsgTipDto aDto) throws ObjectNotFoundException, OptimisticLockStolenException {
        if (aDto.getId() != null) {
            final LjMsgTip msg = this.dao.getById(aDto.getId());
            this.dao.delete(msg);
        }
    }

    /**
     * @see com.youthen.lj.service.LjMsgTipService#delByUserId(java.lang.String)
     */

    @Override
    @Transactional
    public void delByUserId(final String aUserId) {// 已读
        final String hql = "update LjMsgTip set status=1  where userId=? ";
        final Object[] values = {aUserId};
        try {
            this.dao.excuteByHql(hql, values);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see com.youthen.lj.service.LjMsgTipService#delByParamId(java.lang.String)
     */

    @Override
    @Transactional
    public void delByParamId(final String aParamId) {
        final String hql = "from LjMsgTip where paramId='" + aParamId + "' ";
        final List<LjMsgTip> list = this.dao.getByHql(hql);
        for (int i = 0; i < list.size(); i++) {
            try {
                this.dao.delete(list.get(i));
            } catch (final ObjectNotFoundException e) {
                e.printStackTrace();
            } catch (final OptimisticLockStolenException e) {
                e.printStackTrace();
            }
        }
    }

}
