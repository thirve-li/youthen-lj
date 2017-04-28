// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.service.dto.LjRoomInfoDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjRoomInfoService {

    LjRoomInfoDto getRoomInfo(String roomCode);

    int getRoomInfoCount(LjRoomInfoDto ljRoomInfoDto);

    List<LjRoomInfoDto> getRoomInfoList(LjRoomInfoDto dto);

    Long insert(LjRoomInfoDto dto) throws DuplicateKeyException;

    LjRoomInfoDto update(LjRoomInfoDto dto);

    LjRoomInfoDto getById(Long id);

    void createRoomInfo();

    Result delete(Long id);

}
