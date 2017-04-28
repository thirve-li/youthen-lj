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
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjActionCommentService;
import com.youthen.lj.service.LjNoticeService;
import com.youthen.lj.service.dto.LjActionCommentDto;
import com.youthen.lj.service.dto.LjNoticeDto;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.LoginUserDto;

/**
 * 。
 * 
 * @author gong
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/lj-comment")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/comment/list.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/comment/entity.jsp"),
        @Result(name = "toList", location = "/lj-comment/list.action?dto.type=${dto.type}", type = "redirect"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/comment/view.jsp"),
        @Result(name = "toViewComments", location = "/WEB-INF/jsp/lj/admin/comment/toViewComments.jsp")

})
@Controller
@ExecAuthority(functioncd = "MST-ADMIN-001")
public class LjActionCommentAction extends BaseAction {

    private static final long serialVersionUID = 3434234123426578L;

    private LoginUser loginUser;

    private LjActionCommentDto dto = new LjActionCommentDto();

    private List<LjActionCommentDto> commentList;

    private List<LjNoticeDto> noticeList;

    private List<LoginUserDto> userList;

    private String actionName;

    private String url;

    private String name;

    private LjNoticeDto noticeDto;

    @Autowired
    private LjActionCommentService actionCommentService;

    @Autowired
    private LjNoticeService ljNoticeService;

    @Autowired
    private LoginUserService loginUserService;

    @Action("list")
    public String list() {

        this.commentList = this.actionCommentService.getActionCommentList(this.dto);
        final int listSize = this.actionCommentService.getActionCommentCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);
        return "list";
    }

    @Action("toViewComments")
    public String toViewComments() {
        this.dto.setStatus(1);
        this.commentList = this.actionCommentService.getActionCommentList(this.dto);
        final int listSize = this.actionCommentService.getActionCommentCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);
        this.actionName = this.ljNoticeService.getById(this.dto.getActionId()).getName();
        return "toViewComments";
    }

    @Action("entityInit")
    public String entityInit() {
        final LjNoticeDto ljNoticeDto = new LjNoticeDto();
        ljNoticeDto.setStatus(1);
        ljNoticeDto.setType(1);
        this.noticeList = this.ljNoticeService.getLjNoticeList(ljNoticeDto);
        ljNoticeDto.setType(2);
        for (int i = 0; i < this.ljNoticeService.getLjNoticeList(ljNoticeDto).size(); i++) {
            this.noticeList.add(this.ljNoticeService.getLjNoticeList(ljNoticeDto).get(i));
        }
        ljNoticeDto.setType(3);
        for (int i = 0; i < this.ljNoticeService.getLjNoticeList(ljNoticeDto).size(); i++) {
            this.noticeList.add(this.ljNoticeService.getLjNoticeList(ljNoticeDto).get(i));
        }
        final LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setPageSize(100000);
        this.userList = this.loginUserService.getUserList(loginUserDto);
        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.actionCommentService.getById(this.dto.getId());
        }
        return "entityInit";
    }

    @Action("saveUpdate")
    public String saveUpdate() {

        this.dto.setStatus(1);
        if (this.dto != null && this.dto.getId() != null) {
            this.actionCommentService.update(this.dto);
        } else {
            try {
                this.actionCommentService.insert(this.dto);
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
            }
        }
        return "toList";
    }

    @Action("view")
    public String view() {
        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.actionCommentService.getById(this.dto.getId());
        }
        return "view";
    }

    @SuppressWarnings("boxing")
    @Action("delete")
    public String delete() {
        this.dto.setStatus(0);
        this.actionCommentService.update(this.dto);
        return "toList";
    }

    /**
     * getter for loginUser.
     * 
     * @return loginUser
     */
    public LoginUser getLoginUser() {
        return this.loginUser;
    }

    /**
     * setter for loginUser.
     * 
     * @param aLoginUser loginUser
     */
    public void setLoginUser(final LoginUser aLoginUser) {
        this.loginUser = aLoginUser;
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public LjActionCommentDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final LjActionCommentDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for commentList.
     * 
     * @return commentList
     */
    public List<LjActionCommentDto> getCommentList() {
        return this.commentList;
    }

    /**
     * setter for commentList.
     * 
     * @param aCommentList commentList
     */
    public void setCommentList(final List<LjActionCommentDto> aCommentList) {
        this.commentList = aCommentList;
    }

    /**
     * getter for actionCommentService.
     * 
     * @return actionCommentService
     */
    public LjActionCommentService getActionCommentService() {
        return this.actionCommentService;
    }

    /**
     * setter for actionCommentService.
     * 
     * @param aActionCommentService actionCommentService
     */
    public void setActionCommentService(final LjActionCommentService aActionCommentService) {
        this.actionCommentService = aActionCommentService;
    }

    /**
     * getter for noticeList.
     * 
     * @return noticeList
     */
    public List<LjNoticeDto> getNoticeList() {
        return this.noticeList;
    }

    /**
     * setter for noticeList.
     * 
     * @param aNoticeList noticeList
     */
    public void setNoticeList(final List<LjNoticeDto> aNoticeList) {
        this.noticeList = aNoticeList;
    }

    /**
     * getter for userList.
     * 
     * @return userList
     */
    public List<LoginUserDto> getUserList() {
        return this.userList;
    }

    /**
     * setter for userList.
     * 
     * @param aUserList userList
     */
    public void setUserList(final List<LoginUserDto> aUserList) {
        this.userList = aUserList;
    }

    /**
     * getter for actionName.
     * 
     * @return actionName
     */
    public String getActionName() {
        return this.actionName;
    }

    /**
     * setter for actionName.
     * 
     * @param aActionName actionName
     */
    public void setActionName(final String aActionName) {
        this.actionName = aActionName;
    }

    /**
     * getter for url.
     * 
     * @return url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * setter for url.
     * 
     * @param aUrl url
     */
    public void setUrl(final String aUrl) {
        this.url = aUrl;
    }

    /**
     * getter for name.
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * setter for name.
     * 
     * @param aName name
     */
    public void setName(final String aName) {
        this.name = aName;
    }

    /**
     * getter for noticeDto.
     * 
     * @return noticeDto
     */
    public LjNoticeDto getNoticeDto() {
        return this.noticeDto;
    }

    /**
     * setter for noticeDto.
     * 
     * @param aNoticeDto noticeDto
     */
    public void setNoticeDto(final LjNoticeDto aNoticeDto) {
        this.noticeDto = aNoticeDto;
    }

}
