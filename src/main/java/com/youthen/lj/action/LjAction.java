// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.LoginUserService;

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
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/notice/view.jsp")
})
@Controller
public class LjAction extends BaseAction {

    private LoginUser loginUser;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private LjRoomInfoService roomInfoService;

    /*
     * 主页显示
     */
    @Action("index")
    public String index() {
        // this.roomInfoService.createRoomInfo();
        return "index";

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

}
