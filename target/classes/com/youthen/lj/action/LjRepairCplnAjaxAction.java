// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Namespace;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.lj.persistence.entity.LjUserBuilding;
import com.youthen.lj.service.LjUserBuildingService;
import com.youthen.lj.service.dto.LjRepairCplnDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@SuppressWarnings("serial")
@Namespace("/lj-repair")
@Controller
public class LjRepairCplnAjaxAction extends AbstractAjaxAction {

    // 楼栋号
    private Integer buildNos;

    // 单元号
    private Integer unitNos;

    // 房间号
    private Integer roomNos;

    LjRepairCplnDto dto;

    List<LjUserBuilding> userBuildingList;

    @Autowired
    LjUserBuildingService userBuildingService;

    /**
     * @see com.youthen.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    public String getUserNameList() {

        this.userBuildingList = this.userBuildingService.getUserRoomList(this.dto.getReporterId());
        final JSONArray json = new JSONArray();
        for (int i = 0; i < this.userBuildingList.size(); i++) {
            final JSONObject jo = new JSONObject();
            jo.put("title", this.userBuildingList.get(i).getUser().getName());
            json.put(jo);
        }
        final String strJson = json.toString();
        System.out.println(strJson);
        return strJson;
    }

    /**
     * 动态获取绑定房屋的方法
     * 。
     * 
     * @return
     */
    public String[] getRoomInfoList() {
        this.userBuildingList = this.userBuildingService.getRoomList(this.dto.getReporterId());

        if (this.userBuildingList.size() > 0) {
            this.buildNos = this.userBuildingList.get(0).getRoomInfo().getBuildingNo();
            this.unitNos = this.userBuildingList.get(0).getRoomInfo().getUnitNo();
            this.roomNos = this.userBuildingList.get(0).getRoomInfo().getRoomNo();

        }
        final String strUserBuildNos = String.valueOf(this.buildNos);
        final String strUserUnitNos = String.valueOf(this.unitNos);
        final String strUserRoomNos = String.valueOf(this.roomNos);
        return new String[] {strUserBuildNos/* , strUserUnitNos */, strUserRoomNos};
    }

    /**
     * getter for userBuildingList.
     * 
     * @return userBuildingList
     */
    public List<LjUserBuilding> getUserBuildingList() {
        return this.userBuildingList;
    }

    /**
     * setter for userBuildingList.
     * 
     * @param aUserBuildingList userBuildingList
     */
    public void setUserBuildingList(final List<LjUserBuilding> aUserBuildingList) {
        this.userBuildingList = aUserBuildingList;
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public LjRepairCplnDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final LjRepairCplnDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for buildNos.
     * 
     * @return buildNos
     */
    public Integer getBuildNos() {
        return this.buildNos;
    }

    /**
     * setter for buildNos.
     * 
     * @param aBuildNos buildNos
     */
    public void setBuildNos(final Integer aBuildNos) {
        this.buildNos = aBuildNos;
    }

    /**
     * getter for unitNos.
     * 
     * @return unitNos
     */
    public Integer getUnitNos() {
        return this.unitNos;
    }

    /**
     * setter for unitNos.
     * 
     * @param aUnitNos unitNos
     */
    public void setUnitNos(final Integer aUnitNos) {
        this.unitNos = aUnitNos;
    }

    /**
     * getter for roomNos.
     * 
     * @return roomNos
     */
    public Integer getRoomNos() {
        return this.roomNos;
    }

    /**
     * setter for roomNos.
     * 
     * @param aRoomNos roomNos
     */
    public void setRoomNos(final Integer aRoomNos) {
        this.roomNos = aRoomNos;
    }

}
