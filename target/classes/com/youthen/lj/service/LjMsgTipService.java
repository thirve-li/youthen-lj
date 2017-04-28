// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.lj.service.dto.LjMsgTipDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjMsgTipService {

    /**
     * 根据dto获得消息的list
     */
    List<LjMsgTipDto> getMsgTipList(LjMsgTipDto dto);

    /**
     * 新增
     */
    Long insert(LjMsgTipDto dto) throws DuplicateKeyException;

    /**
     * 删除
     */
    void delete(LjMsgTipDto dto) throws ObjectNotFoundException, OptimisticLockStolenException;

    void delByUserId(String userId);

    /**
     * 根据paramId删除消息
     */
    void delByParamId(String paramId);

}
