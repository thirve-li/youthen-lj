// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.lj.app.bean.RoomInfo;
import com.youthen.lj.app.logic.LjAppLogic;
import com.youthen.lj.persistence.dao.LjUserBuildingDao;
import com.youthen.lj.persistence.entity.LjUserBuilding;
import com.youthen.lj.service.LjUserBuildingService;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljUserBuildingService")
public class LjUserBuildingServiceImpl implements LjUserBuildingService {

    @Autowired
    LjUserBuildingDao dao;

    @Autowired
    LjAppLogic ljAppLogic;

    @Override
    public List<LjUserBuilding> getUserRoomList(final String aUserName) {
        final String hql =
                " from LjUserBuilding where user.name like '%" + aUserName + "%' or user.userId like '%" + aUserName
                        + "%'";
        final List<LjUserBuilding> list = this.dao.getByHql(hql);
        return list;
    }

    @Override
    public List<LjUserBuilding> getRoomList(final String aUserName) {
        final String hql = "from LjUserBuilding where user.name = '" + aUserName + "'";
        return this.dao.getByHql(hql);
    }

    /**
     * @see com.youthen.lj.service.LjUserBuildingService#getUserRoom(java.lang.String)
     */

    @Override
    public List<RoomInfo> getUserRoom(final String aUserId) {
        final String hql = " from LjUserBuilding where userId = '" + aUserId + "'";
        final List<LjUserBuilding> list = this.dao.getByHql(hql);

        final List<RoomInfo> roomInfoList = new ArrayList<RoomInfo>();
        if (list != null) {
            for (final LjUserBuilding item : list) {

                final RoomInfo roomInfo = new RoomInfo();
                roomInfo.setId(item.getId().toString());
                roomInfo.setPrice(String.valueOf(item.getRoomInfo().getTotalPrice()));
                roomInfo.setLastPeriod(item.getRoomInfo().getLastPeriod());
                final String roomCode = item.getRoomInfo().getCode();

                if (StringUtils.isNotEmpty(roomCode)) {
                    roomInfo.setRoomCode(roomCode);
                }

                roomInfoList.add(roomInfo);
            }
        }
        return roomInfoList;

    }

    /**
     * @see com.youthen.lj.service.LjUserBuildingService#insertUserRoom(com.youthen.lj.persistence.entity.LjUserBuilding)
     */

    @Override
    public Long insertUserRoom(final LjUserBuilding aRoom) {
        try {
            return (Long) this.dao.insert(aRoom);
        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @see com.youthen.lj.service.LjUserBuildingService#delUserRoom(java.lang.String)
     */

    @Override
    public void delUserRoom(final String aUserId, final String roomCode) {

        try {

            String hql = "delete from LjUserBuilding where userId=? ";

            if (StringUtils.isNotEmpty(roomCode)) {
                hql += " and roomInfo.code=? ";
                final Object[] values = {aUserId, roomCode};
                this.dao.excuteByHql(hql, values);
            } else {
                final Object[] values = {aUserId};
                this.dao.excuteByHql(hql, values);
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
