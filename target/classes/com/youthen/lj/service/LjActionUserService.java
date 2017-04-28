// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.lj.service.dto.LjActionUserDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjActionUserService {

    /**
     * list显示
     */
    List<LjActionUserDto> getActionUserList(LjActionUserDto dto);

    /**
     * 新增
     */
    Long insert(LjActionUserDto dto) throws DuplicateKeyException;

    /**
     * 根据id删除
     */
    void delete(LjActionUserDto dto) throws ObjectNotFoundException, OptimisticLockStolenException;
}
