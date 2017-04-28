// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.service.dto.LjMerchantDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjMerchantService {

    List<LjMerchantDto> getMerchantList(LjMerchantDto dto);

    int getMerchantCount(LjMerchantDto dto);

    LjMerchantDto getById(Long id);

    Long insert(LjMerchantDto dto) throws DuplicateKeyException;

    LjMerchantDto update(LjMerchantDto dto);

}
