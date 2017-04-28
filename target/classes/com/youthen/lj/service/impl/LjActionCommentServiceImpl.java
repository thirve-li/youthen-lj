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
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.lj.persistence.dao.LjActionCommentDao;
import com.youthen.lj.persistence.entity.LjActionComment;
import com.youthen.lj.service.LjActionCommentService;
import com.youthen.lj.service.dto.LjActionCommentDto;
import com.youthen.master.util.CommonUtil;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljActionCommentService")
public class LjActionCommentServiceImpl implements LjActionCommentService {

    @Autowired
    private LjActionCommentDao ljActionCommentDao;

    @Override
    public List<LjActionCommentDto> getActionCommentList(final LjActionCommentDto actionCommentDto) {

        String hql = "from LjActionComment where 1=1";
        final String orderBy = "order by status desc,actionId desc,createTime asc,createId desc,isgood desc";
        if (actionCommentDto.getActionId() != null) {
            hql += " and actionId='" + actionCommentDto.getActionId() + "'";
        }
        if (actionCommentDto.getActionName() != null) {
            hql += " and action.name like '%" + actionCommentDto.getActionName() + "%'";
        }
        if (actionCommentDto.getType() != null) {
            hql += " and type='" + actionCommentDto.getType() + "'";
        }
        if (actionCommentDto.getCreateTime() != null) {
            hql += "and createTime between'" + CommonUtil.dateToStrLong(actionCommentDto.getCreateTime()) + "'"
                    + "and '" + CommonUtil.dateToStr(actionCommentDto.getCreateTime()) + "23:59:59 '";
        }
        if (actionCommentDto.getStatus() != null) {
            hql += "and status='" + actionCommentDto.getStatus() + "'";
        }
        if (actionCommentDto.getIsgood() != null) {
            hql += "and isgood='" + actionCommentDto.getIsgood() + "'";
        }
        hql += orderBy;
        final List<LjActionComment> list =
                this.ljActionCommentDao.getByPage(hql, actionCommentDto.getGotoPage(), actionCommentDto.getPageSize());
        final ArrayList<LjActionCommentDto> arrayList = new ArrayList<LjActionCommentDto>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjActionComment comment : list) {
                final LjActionCommentDto dto = new LjActionCommentDto();
                BeanUtils.copyAllNullableProperties(comment, dto);
                arrayList.add(dto);
            }
        }
        return arrayList;
    }

    /**
     * @see com.youthen.lj.service.LjActionCommentService#getActionCommentCount(com.youthen.lj.service.dto.LjActionCommentDto)
     */

    @Override
    public int getActionCommentCount(final LjActionCommentDto actionCommentDto) {
        String hql = "from LjActionComment where 1=1";
        final String orderBy = "order by status desc,actionId desc,createTime asc,createId desc,isgood desc";
        if (actionCommentDto.getActionId() != null) {
            hql += " and actionId='" + actionCommentDto.getActionId() + "'";
        }
        if (actionCommentDto.getActionName() != null) {
            hql += " and action.name like '%" + actionCommentDto.getActionName() + "%'";
        }
        if (actionCommentDto.getType() != null) {
            hql += " and type='" + actionCommentDto.getType() + "'";
        }
        if (actionCommentDto.getCreateTime() != null) {
            hql += "and createTime between'" + CommonUtil.dateToStrLong(actionCommentDto.getCreateTime()) + "'"
                    + "and '" + CommonUtil.dateToStr(actionCommentDto.getCreateTime()) + "23:59:59 '";
        }
        if (actionCommentDto.getStatus() != null) {
            hql += "and status='" + actionCommentDto.getStatus() + "'";
        }
        if (actionCommentDto.getIsgood() != null) {
            hql += "and isgood='" + actionCommentDto.getIsgood() + "'";
        }
        hql += orderBy;
        return this.ljActionCommentDao.getCount(hql);
    }

    /**
     * @see com.youthen.lj.service.LjActionCommentService#insert(com.youthen.lj.service.dto.LjActionCommentDto)
     */

    @Override
    @Transactional
    public Long insert(final LjActionCommentDto actionCommentDto) throws DuplicateKeyException {
        final LjActionComment actionComment = new LjActionComment();
        BeanUtils.copyAllNullableProperties(actionCommentDto, actionComment);
        actionComment.setCreateTime(new Date());
        return (Long) this.ljActionCommentDao.insert(actionComment);
    }

    /**
     * @see com.youthen.lj.service.LjActionCommentService#getById(java.lang.Long)
     */

    @Override
    public LjActionCommentDto getById(final Long Id) {
        final LjActionCommentDto dto = new LjActionCommentDto();
        BeanUtils.copyAllProperties(this.ljActionCommentDao.getById(Id), dto);
        return dto;
    }

    /**
     * @see com.youthen.lj.service.LjActionCommentService#update(com.youthen.lj.service.dto.LjActionCommentDto)
     */

    @Override
    @Transactional
    public LjActionCommentDto update(final LjActionCommentDto commentDto) {
        final LjActionComment actionComment = this.ljActionCommentDao.getById(commentDto.getId());
        BeanUtils.copyNullableProperties(commentDto, actionComment);
        this.ljActionCommentDao.update(actionComment);
        BeanUtils.copyProperties(actionComment, commentDto);
        return commentDto;
    }
}
