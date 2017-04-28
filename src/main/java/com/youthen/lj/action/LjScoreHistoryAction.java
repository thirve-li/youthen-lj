// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.annotation.ExecAuthority;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.BeanUtils;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjScoreHistoryService;
import com.youthen.lj.service.dto.LjScoreHistoryDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/lj-score")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/score/list.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/score/entity.jsp"),
        @Result(name = "toList", location = "/lj-score/list.action", type = "redirect"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/score/view.jsp")
})
@Controller
@ExecAuthority(functioncd = "MST-ADMIN-001")
public class LjScoreHistoryAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private LjScoreHistoryDto scoreHistoryDto = new LjScoreHistoryDto();

    private List<LjScoreHistoryDto> scoreHistoryList;

    @Autowired
    private LjScoreHistoryService historyService;

    @Action("list")
    public String list() {
        this.scoreHistoryList = this.historyService.getScoreHistoryList(this.scoreHistoryDto);
        final int listSize = this.historyService.getScoreHistoryCount(this.scoreHistoryDto);
        final int pages = CommonUtils.countPages(listSize, this.scoreHistoryDto.getPageSize());
        this.scoreHistoryDto.setPages(pages);
        this.scoreHistoryDto.setListSize(listSize);
        return "list";
    }

    @Action("entityInit")
    public String entityInit() {
        if (this.scoreHistoryDto != null && this.scoreHistoryDto.getId() != null) {
            this.scoreHistoryDto = this.historyService.getById(this.scoreHistoryDto.getId());
        }
        return "entityInit";
    }

    @Action("saveUpdate")
    public String saveUpdate() {
        try {
            if (this.scoreHistoryDto != null && this.scoreHistoryDto.getId() != null) {
                this.historyService.update(this.scoreHistoryDto);
            } else {
                this.historyService.insert(this.scoreHistoryDto);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "toList";
    }

    @Action("view")
    public String view() {
        this.scoreHistoryList = this.historyService.getScoreHistoryList(this.scoreHistoryDto);
        this.scoreHistoryDto = this.historyService.getById(this.scoreHistoryDto.getId());
        BeanUtils.setNAProperty(this.scoreHistoryDto);
        return "view";
    }

    @Action("delete")
    public String delete() {
        this.historyService.update(this.scoreHistoryDto);
        return list();
    }

    /**
     * getter for scoreHistoryDto.
     * 
     * @return scoreHistoryDto
     */
    public LjScoreHistoryDto getScoreHistoryDto() {
        return this.scoreHistoryDto;
    }

    /**
     * setter for scoreHistoryDto.
     * 
     * @param aScoreHistoryDto scoreHistoryDto
     */
    public void setScoreHistoryDto(final LjScoreHistoryDto aScoreHistoryDto) {
        this.scoreHistoryDto = aScoreHistoryDto;
    }

    /**
     * getter for scoreHistoryList.
     * 
     * @return scoreHistoryList
     */
    public List<LjScoreHistoryDto> getScoreHistoryList() {
        return this.scoreHistoryList;
    }

    /**
     * setter for scoreHistoryList.
     * 
     * @param aScoreHistoryList scoreHistoryList
     */
    public void setScoreHistoryList(final List<LjScoreHistoryDto> aScoreHistoryList) {
        this.scoreHistoryList = aScoreHistoryList;
    }

    /**
     * getter for historyService.
     * 
     * @return historyService
     */
    public LjScoreHistoryService getHistoryService() {
        return this.historyService;
    }

    /**
     * setter for historyService.
     * 
     * @param aHistoryService historyService
     */
    public void setHistoryService(final LjScoreHistoryService aHistoryService) {
        this.historyService = aHistoryService;
    }
}
