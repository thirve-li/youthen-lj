package com.youthen.lj.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.annotation.ExecAuthority;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.app.service.LjAppService;
import com.youthen.master.service.DepartmentService;

/**
 * @author DYZ
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/wx-user")
@Controller
@ExecAuthority(functioncd = "1")
public class WxUserAjaxAction extends AbstractAjaxAction {

    @Autowired
    LjAppService ljAppService;

    String accessToken;
    String openId;

    @Autowired
    private DepartmentService departmentService;

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    public Result getWxUserInfo() {
        final Result wxUserResult = this.ljAppService.getWxUserInfo(this.accessToken, this.openId);
        return wxUserResult;
    }

    /**
     * getter for accessToken.
     * 
     * @return accessToken
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * setter for accessToken.
     * 
     * @param aAccessToken accessToken
     */
    public void setAccessToken(final String aAccessToken) {
        this.accessToken = aAccessToken;
    }

    /**
     * getter for openId.
     * 
     * @return openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * setter for openId.
     * 
     * @param aOpenId openId
     */
    public void setOpenId(final String aOpenId) {
        this.openId = aOpenId;
    }

}
