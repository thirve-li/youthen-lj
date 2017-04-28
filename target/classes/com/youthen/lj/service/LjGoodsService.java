// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.service.dto.LjGoodsDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjGoodsService {

    List<LjGoodsDto> getGoodsList(LjGoodsDto dto);

    int getGoodsCount(LjGoodsDto dto);

    LjGoodsDto getById(Long id);

    Long insert(LjGoodsDto dto) throws DuplicateKeyException;

    LjGoodsDto update(LjGoodsDto dto);

}
