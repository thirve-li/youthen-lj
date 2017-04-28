// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.service.dto.LjScoreHistoryDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjScoreHistoryService {

    List<LjScoreHistoryDto> getScoreHistoryList(LjScoreHistoryDto scoreHistoryDto);

    int getScoreHistoryCount(LjScoreHistoryDto scoreHistoryDto);

    public Long insert(LjScoreHistoryDto scoreHistoryDto) throws DuplicateKeyException;

    LjScoreHistoryDto getById(Long id);

    LjScoreHistoryDto update(LjScoreHistoryDto scoreHistoryDto);

    void delete(LjScoreHistoryDto scoreHistoryDto);

}
