// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.opensymphony.xwork2.ActionContext;
import com.youthen.framework.common.annotation.ExecAuthority;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.BeanUtils;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjMsgTipService;
import com.youthen.lj.service.LjNoticeService;
import com.youthen.lj.service.dto.LjMsgTipDto;
import com.youthen.lj.service.dto.LjNoticeDto;
import com.youthen.lj.utils.UploadUtils;
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
/**
 * 页面的跳转
 * 1.列表
 * 2.增加/修改
 * 3.查看
 */
@Namespace("/lj-notice")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/lj/admin/index.jsp"),
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/notice/list.jsp"),
        @Result(name = "toList", location = "/lj-notice/list.action?dto.type=${dto.type}", type = "redirect"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/notice/entity.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/notice/view.jsp"),

})
@Controller
@SuppressWarnings("javadoc")
@ExecAuthority(functioncd = "MST-ADMIN-001")
public class LjNoticeAction extends BaseAction {

    /**
     * 。
     */
    private static final long serialVersionUID = 1L;

    private LoginUser loginUser;

    private LjNoticeDto dto = new LjNoticeDto();

    private List<LjNoticeDto> dtoList;

    // 报修或投诉图片
    private List<String> noticeNameList;

    private File[] img;

    private String[] imgFileName;

    private List<String> fileNameList;

    @Autowired
    private LjNoticeService noticeService;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private LjMsgTipService msgTipService;

    /**
     * 列表
     */

    @Action("list")
    public String list() {
        this.dtoList = this.noticeService.getLjNoticeList(this.dto);
        final int listSize = this.noticeService.getNoticeCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);
        return "list";
    }

    /**
     * 新增/修改页面跳转
     */

    @Action("entityInit")
    public String entityInit() {
        try {
            if (this.dto != null && this.dto.getId() != null) {
                this.dto = this.noticeService.getById(this.dto.getId());
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "entityInit";

    }

    @Action("pushMessage")
    public String pushMessage() {
        try {
            if (this.dto != null && this.dto.getId() != null) {
                this.dto = this.noticeService.getById(this.dto.getId());
            }
            this.noticeService.push(this.dto);
        } catch (final PushClientException e) {
            e.printStackTrace();
        } catch (final PushServerException e) {
            e.printStackTrace();
        }

        return "toList";
    }

    /**
     * 增加/修改
     */
    @SuppressWarnings("boxing")
    @Action("saveUpdate")
    public String saveupdate() {
        try {

            if (this.img != null) {
                String imageType = "";
                if (this.dto.getType() == 0) {
                    imageType = "notice";
                } else if (this.dto.getType() == 1) {
                    imageType = "activity";
                } else if (this.dto.getType() == 2) {
                    imageType = "topic";
                } else if (this.dto.getType() == 3) {
                    imageType = "myActivity";
                } else {
                    imageType = "feedback";
                }

                // 图片上传的方法的调用
                // 图片上传的方法
                this.fileNameList = UploadUtils.uploadImages(this.img, this.imgFileName, imageType);
                if (this.fileNameList.size() == 1) {
                    this.dto.setImage1(this.fileNameList.get(0));
                } else if (this.fileNameList.size() == 2) {
                    this.dto.setImage1(this.fileNameList.get(0));
                    this.dto.setImage2(this.fileNameList.get(1));
                } else if (this.fileNameList.size() == 3) {
                    this.dto.setImage1(this.fileNameList.get(0));
                    this.dto.setImage2(this.fileNameList.get(1));
                    this.dto.setImage3(this.fileNameList.get(2));
                } else if (this.fileNameList.size() == 4) {
                    this.dto.setImage1(this.fileNameList.get(0));
                    this.dto.setImage2(this.fileNameList.get(1));
                    this.dto.setImage3(this.fileNameList.get(2));
                    this.dto.setImage4(this.fileNameList.get(3));
                } else if (this.fileNameList.size() == 5) {
                    this.dto.setImage1(this.fileNameList.get(0));
                    this.dto.setImage2(this.fileNameList.get(1));
                    this.dto.setImage3(this.fileNameList.get(2));
                    this.dto.setImage4(this.fileNameList.get(3));
                    this.dto.setImage5(this.fileNameList.get(4));
                }

            }
            if (this.dto != null && this.dto.getId() != null) {
                this.noticeService.update(this.dto);
            } else {
                final Long parmsId = this.noticeService.insert(this.dto);
                if (this.dto.getType() == 0 || this.dto.getType() == 1 || this.dto.getType() == 6) {
                    final LoginUserDto condition = new LoginUserDto();

                    condition.setPageSize(9999);
                    final List<LoginUserDto> userlist = this.loginUserService.getUserList(condition);
                    for (final LoginUserDto userDto : userlist) {
                        final LjMsgTipDto msgDto = new LjMsgTipDto();
                        msgDto.setUserId(userDto.getUserId());
                        msgDto.setTitle(this.dto.getName());
                        msgDto.setParamId(parmsId.toString());
                        msgDto.setType(this.dto.getType().toString());
                        if (this.dto.getType() == 0) {
                            msgDto.setTypeName("1");
                        }
                        if (this.dto.getType() == 1) {
                            msgDto.setTypeName("2");
                        }
                        if (this.dto.getType() == 6) {
                            msgDto.setTypeName("3");
                        }

                        this.dto.setCreateTime(new Date());

                        msgDto.setCreateDate(this.dto.getCreateTime().toString());
                        msgDto.setOpt("getNoticeOrActiveDetail");
                        this.msgTipService.insert(msgDto);
                    }

                }

            }

        } catch (final Exception e) {
            e.printStackTrace();
        }

        return "toList";
    }

    @SuppressWarnings("boxing")
    @Action("delete")
    public String delete() {
        this.dto.setStatus(0);
        this.noticeService.update(this.dto);
        this.msgTipService.delByParamId(this.dto.getId().toString());
        return "toList";
    }

    @SuppressWarnings("boxing")
    @Action("isTop")
    public String istop() {
        if (this.dto.getIsTop() == 0) {
            this.dto.setIsTop(1);
        }
        else {
            this.dto.setIsTop(0);
        }
        this.noticeService.update(this.dto);
        return "toList";
    }

    /*
     * @Action("delete")
     * public String delete() {
     * this.noticeService.delete(this.dto);
     * return list();
     * }
     */

    /**
     * 查看
     */
    @Action("view")
    public String view() {
        this.dtoList = this.noticeService.getLjNoticeList(this.dto);
        this.dto = this.noticeService.getById(this.dto.getId());
        this.noticeNameList = new ArrayList<String>();
        if (this.dto.getImage1() != null) {
            this.noticeNameList.add(this.dto.getImage1());
        }
        if (this.dto.getImage2() != null) {
            this.noticeNameList.add(this.dto.getImage2());
        }
        if (this.dto.getImage3() != null) {
            this.noticeNameList.add(this.dto.getImage3());
        }
        if (this.dto.getImage4() != null) {
            this.noticeNameList.add(this.dto.getImage4());
        }
        if (this.dto.getImage5() != null) {
            this.noticeNameList.add(this.dto.getImage5());
        }

        ActionContext.getContext().getSession().put("noticeNameList", this.noticeNameList);
        BeanUtils.setNAProperty(this.dto);
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
     * getter for dto.
     * 
     * @return dto
     */
    public LjNoticeDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final LjNoticeDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for dtoList.
     * 
     * @return dtoList
     */
    public List<LjNoticeDto> getDtoList() {
        return this.dtoList;
    }

    /**
     * setter for dtoList.
     * 
     * @param aDtoList dtoList
     */
    public void setDtoList(final List<LjNoticeDto> aDtoList) {
        this.dtoList = aDtoList;
    }

    /**
     * getter for noticeService.
     * 
     * @return noticeService
     */
    public LjNoticeService getNoticeService() {
        return this.noticeService;
    }

    /**
     * setter for noticeService.
     * 
     * @param aNoticeService noticeService
     */
    public void setNoticeService(final LjNoticeService aNoticeService) {
        this.noticeService = aNoticeService;
    }

    /**
     * getter for loginUserService.
     * 
     * @return loginUserService
     */
    public LoginUserService getLoginUserService() {
        return this.loginUserService;
    }

    /**
     * setter for loginUserService.
     * 
     * @param aLoginUserService loginUserService
     */
    public void setLoginUserService(final LoginUserService aLoginUserService) {
        this.loginUserService = aLoginUserService;
    }

    /**
     * getter for img.
     * 
     * @return img
     */
    public File[] getImg() {
        return this.img;
    }

    /**
     * setter for img.
     * 
     * @param aImg img
     */
    public void setImg(final File[] aImg) {
        this.img = aImg;
    }

    /**
     * getter for imgFileName.
     * 
     * @return imgFileName
     */
    public String[] getImgFileName() {
        return this.imgFileName;
    }

    /**
     * setter for imgFileName.
     * 
     * @param aImgFileName imgFileName
     */
    public void setImgFileName(final String[] aImgFileName) {
        this.imgFileName = aImgFileName;
    }

    /**
     * getter for fileNameList.
     * 
     * @return fileNameList
     */
    public List<String> getFileNameList() {
        return this.fileNameList;
    }

    /**
     * setter for fileNameList.
     * 
     * @param aFileNameList fileNameList
     */
    public void setFileNameList(final List<String> aFileNameList) {
        this.fileNameList = aFileNameList;
    }

    /**
     * getter for noticeNameList.
     * 
     * @return noticeNameList
     */
    public List<String> getNoticeNameList() {
        return this.noticeNameList;
    }

    /**
     * setter for noticeNameList.
     * 
     * @param aNoticeNameList noticeNameList
     */
    public void setNoticeNameList(final List<String> aNoticeNameList) {
        this.noticeNameList = aNoticeNameList;
    }

}
