// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.service.dto.LjRepairCplnDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjRepairCplnService {

    List<LjRepairCplnDto> getRepairCplnList(LjRepairCplnDto repairCplnDto);

    int getRepairCplnCount(LjRepairCplnDto repairCplnDto);

    public Long insert(LjRepairCplnDto repairCplnDto) throws DuplicateKeyException;

    LjRepairCplnDto getById(Long id);

    LjRepairCplnDto update(LjRepairCplnDto repairCplnDto);

    Result delete(Long id);

}
