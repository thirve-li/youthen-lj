// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.service.dto.LjActionCommentDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjActionCommentService {

    List<LjActionCommentDto> getActionCommentList(LjActionCommentDto actionCommentDto);

    int getActionCommentCount(LjActionCommentDto actionCommentDto);

    public Long insert(LjActionCommentDto actionCommentDto) throws DuplicateKeyException;

    LjActionCommentDto getById(Long id);

    LjActionCommentDto update(LjActionCommentDto actionCommentDto);

    /* void delete(LjActionCommentDto actionComment); */

}
