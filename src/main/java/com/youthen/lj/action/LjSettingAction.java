// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.dto.KbnDto;

/**
 * 系统设置管理
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Namespace("/lj-setting")
@Results({
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/setting/entity.jsp"),
        @Result(name = "tolist", location = "/lj-setting/entityInit.action?dto.type=SYS_CONFIG", type = "redirect")
})
@Controller
public class LjSettingAction extends BaseAction {

    private KbnDto dto = new KbnDto();

    private List<KbnDto> systemList = new ArrayList<KbnDto>();
    @Autowired
    private KbnService kbnService;

    @Action("entityInit")
    public String entityInit() {
        this.systemList = this.kbnService.getKbnListByType("SYS_CONFIG");
        return "entityInit";
    }

    @Action("saveSystem")
    public String saveSystem() {

        final HttpServletRequest request = ServletActionContext.getRequest();

        // final String APP_VERSION = request.getParameter("APP_VERSION");
        // final KbnDto vDto = this.kbnService.getKbn("SYS_CONFIG", "APP_VERSION");
        // vDto.setNameEn(APP_VERSION);
        // this.kbnService.update(vDto);
        //
        // final String ANDRIOD_DOWNLOAD_URL = request.getParameter("ANDRIOD_DOWNLOAD_URL");
        // final KbnDto aDto = this.kbnService.getKbn("SYS_CONFIG", "ANDRIOD_DOWNLOAD_URL");
        // aDto.setNameEn(ANDRIOD_DOWNLOAD_URL);
        // this.kbnService.update(aDto);
        //
        // final String IOS_DOWNLOAD_URL = request.getParameter("IOS_DOWNLOAD_URL");
        // final KbnDto iDto = this.kbnService.getKbn("SYS_CONFIG", "IOS_DOWNLOAD_URL");
        // iDto.setNameEn(IOS_DOWNLOAD_URL);
        // this.kbnService.update(iDto);
        //
        // final String VERIFY_CODE_LENGTH = request.getParameter("VERIFY_CODE_LENGTH");
        // final KbnDto cDto = this.kbnService.getKbn("SYS_CONFIG", "VERIFY_CODE_LENGTH");
        // cDto.setNameEn(VERIFY_CODE_LENGTH);
        // this.kbnService.update(cDto);
        //
        // final String SCORE_REG = request.getParameter("SCORE_REG");
        // final KbnDto rDto = this.kbnService.getKbn("SYS_CONFIG", "SCORE_REG");
        // rDto.setNameEn(SCORE_REG);
        // this.kbnService.update(rDto);
        //
        // final String SCORE_LOGIN = request.getParameter("SCORE_LOGIN");
        // final KbnDto lDto = this.kbnService.getKbn("SYS_CONFIG", "SCORE_LOGIN");
        // lDto.setNameEn(SCORE_LOGIN);
        // this.kbnService.update(lDto);
        //
        // final String SCORE_SIGN = request.getParameter("SCORE_SIGN");
        // final KbnDto sDto = this.kbnService.getKbn("SYS_CONFIG", "SCORE_SIGN");
        // sDto.setNameEn(SCORE_SIGN);
        // this.kbnService.update(sDto);
        //
        // final String NOTIFY_URL = request.getParameter("NOTIFY_URL");
        // final KbnDto uDto = this.kbnService.getKbn("SYS_CONFIG", "NOTIFY_URL");
        // uDto.setNameEn(NOTIFY_URL);
        // this.kbnService.update(uDto);

        final String EMPTY_PARK = request.getParameter("EMPTY_PARK");
        final KbnDto eDto = this.kbnService.getKbn("SYS_CONFIG", "EMPTY_PARK");
        eDto.setNameEn(EMPTY_PARK);
        this.kbnService.update(eDto);

        return "tolist";
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public KbnDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final KbnDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for systemList.
     * 
     * @return systemList
     */
    public List<KbnDto> getSystemList() {
        return this.systemList;
    }

}
