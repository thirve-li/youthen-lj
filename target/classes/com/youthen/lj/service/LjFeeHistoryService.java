// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.service.dto.LjFeeHistoryDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjFeeHistoryService {

    /**
     * 根据单元代码，取得最后付费期数
     */
    String getLastPeriodByRoomCode(String roomCode);

    /**
     * 根据车位号，取得最后付费期数
     */
    String getLastPeriodByParkNo(String parkNo);

    /**
     * 生成所有绑定房子跟车位的用户的最后付费历史记录。
     */
    void creatBilling();

    /**
     * 获得缴费list
     */
    List<LjFeeHistoryDto> getFeeHistoryList(LjFeeHistoryDto feeHistory);

    /**
     * 获得缴费list长度
     */
    int getFeeHistoryCount(LjFeeHistoryDto feeHistory);

    /**
     * 新增缴费记录
     */
    public Long insert(LjFeeHistoryDto feeHistory) throws DuplicateKeyException;

    /**
     * 根据id获得缴费记录
     */
    LjFeeHistoryDto getById(Long id);

    /**
     * 根据名字获得缴费记录
     */
    List<LjFeeHistoryDto> getByUserName(String userName);

    /**
     * 更新缴费记录
     */
    LjFeeHistoryDto update(LjFeeHistoryDto feeHistory);

    /**
     * 一键催缴
     */
    String worthOfToAll();

    /**
     * 删除缴费记录
     * 
     * @return
     */
    Result delete(Long id);
}
