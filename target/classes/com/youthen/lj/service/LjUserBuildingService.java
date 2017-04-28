// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service;

import java.util.List;
import com.youthen.lj.app.bean.RoomInfo;
import com.youthen.lj.persistence.entity.LjUserBuilding;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjUserBuildingService {

    List<LjUserBuilding> getUserRoomList(String userName);

    List<LjUserBuilding> getRoomList(String userName);

    List<RoomInfo> getUserRoom(String userId);

    Long insertUserRoom(LjUserBuilding room);

    void delUserRoom(String userId, String roomCode);

}
