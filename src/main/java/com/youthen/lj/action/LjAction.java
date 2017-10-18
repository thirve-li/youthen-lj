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
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.LoginUserDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/lj-admin")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/lj/admin/index.jsp"),
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/notice/list.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/notice/entity.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/notice/view.jsp"),
        @Result(name = "listUser", location = "/WEB-INF/jsp/lj/admin/user/list.jsp"),
        @Result(name = "listAdmin", location = "/WEB-INF/jsp/lj/admin/user/listAdmin.jsp")
})
@Controller
public class LjAction extends BaseAction {

    private LoginUser loginUser;
    private LoginUserDto userDto;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private LjRoomInfoService roomInfoService;

    List<LoginUserDto> userList;

    /*
     * 主页显示
     */
    @Action("index")
    public String index() {
        // this.roomInfoService.createRoomInfo();
        return "index";

    }

    @Action("listUser")
    public String listUser() {
        this.userDto = new LoginUserDto();
        this.userDto.setCompanyId(1l);
        this.userDto.setDepartmentId(1l);
        this.userList = this.loginUserService.getUserList(this.userDto);

        final int listSize = this.loginUserService.getUserListCount(this.userDto);
        final int pages = CommonUtils.countPages(listSize, this.userDto.getPageSize());
        this.userDto.setPages(pages);
        this.userDto.setListSize(listSize);

        return "listUser";

    }

    @Action("list")
    public String list() {

        return "list";

    }

    @Action("entityInit")
    public String entityInit() {

        return "entityInit";

    }

    @Action("view")
    public String view() {

        return "view";

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
     * getter for userDto.
     * 
     * @return userDto
     */
    public LoginUserDto getUserDto() {
        return this.userDto;
    }

    /**
     * setter for userDto.
     * 
     * @param aUserDto userDto
     */
    public void setUserDto(final LoginUserDto aUserDto) {
        this.userDto = aUserDto;
    }

}
