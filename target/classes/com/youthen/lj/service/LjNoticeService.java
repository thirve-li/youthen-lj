// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.service.dto.LjNoticeDto;

/**
 * 。
 * 
 * @author gong
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjNoticeService {

    /**
     * 所查东西 活动名 活动内容 活动开始时间 活动结束时间 活动地点。
     * 
     * @param noticeDto
     * @return
     */
    List<LjNoticeDto> getLjNoticeList(LjNoticeDto noticeDto);

    /**
     * 一共多少条数据。
     * 
     * @param noticeDto
     * @return
     */

    int getNoticeCount(LjNoticeDto noticeDto);

    /**
     * 添加。
     * 
     * @param noticeDto
     * @return
     * @throws DuplicateKeyException
     */
    public Long insert(LjNoticeDto noticeDto)
            throws DuplicateKeyException;

    /**
     * 根据ID。
     * 
     * @param id
     * @return
     */
    LjNoticeDto getById(Long id);

    /**
     * 修改 。
     * 
     * @param noticeDto
     * @return
     */
    LjNoticeDto update(LjNoticeDto noticeDto);

    /**
     * 删除。
     * 
     * @param noticeDto
     */
    // void delete(LjNoticeDto noticeDto);

    public Long push(LjNoticeDto noticeDto) throws PushClientException, PushServerException;

}
