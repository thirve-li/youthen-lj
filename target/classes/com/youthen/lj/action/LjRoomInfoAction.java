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
import com.youthen.framework.util.BeanUtils;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.lj.service.dto.LjRoomInfoDto;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.dto.KbnDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/lj-room")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/lj/admin/index.jsp"),
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/unit/list.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/unit/entity.jsp"),
        @Result(name = "toList", location = "/lj-room/list.action", type = "redirect"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/unit/view.jsp")
})
@Controller
@ExecAuthority(functioncd = "MST-ADMIN-001")
public class LjRoomInfoAction extends BaseAction {

    /**
     * 。
     */
    private static final long serialVersionUID = 3434234123426578L;

    private LjRoomInfoDto dto = new LjRoomInfoDto();

    private List<LjRoomInfoDto> roomList;

    /**
     * KBN
     */
    private List<KbnDto> kbnList;

    /**
     * 小区大栏位
     */
    private List<KbnDto> bigColumnList;

    /**
     * 小区小栏位
     */
    private List<KbnDto> smallColumList;

    @Autowired
    private LjRoomInfoService ljRoomInfoService;

    @Autowired
    private KbnService kbnService;

    /**
     * 小区的list列表
     */

    @Action("list")
    public String list() {

        this.bigColumnList = this.kbnService.getBigXiaoQuColum();
        this.roomList = this.ljRoomInfoService.getRoomInfoList(this.dto);
        final int listSize = this.ljRoomInfoService.getRoomInfoCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);
        return "list";

    }

    /**
     * 跳转到新增或修改房间页面
     * 。
     * 
     * @return
     */
    @Action("entityInit")
    public String entityInit() {
        this.bigColumnList = this.kbnService.getBigXiaoQuColum();
        try {
            if (this.dto != null && this.dto.getId() != null) {
                this.dto = this.ljRoomInfoService.getById(this.dto.getId());
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "entityInit";
    }

    /**
     * 新增或修改房屋
     * 。
     * 
     * @return
     */
    @Action("saveRoom")
    public String saveRoom() {
        try {
            if (this.dto != null && this.dto.getId() != null) {
                this.ljRoomInfoService.update(this.dto);
            } else {
                this.ljRoomInfoService.insert(this.dto);
            }
        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
        }
        return "toList";
    }

    /**
     * 查看房屋详细
     * 。
     * 
     * @return
     */
    @Action("view")
    public String view() {
        this.bigColumnList = this.kbnService.getBigXiaoQuColum();
        this.dto = this.ljRoomInfoService.getById(this.dto.getId());
        BeanUtils.setNAProperty(this.dto);
        return "view";
    }

    /**
     * 删除文章信息
     * 。
     * 
     * @return
     */
    @Action("deleteRoom")
    public String deleteRoom() {

        try {
            this.ljRoomInfoService.delete(this.dto.getId());
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return "toList";
    }

    /**
     * getter for roomList.
     * 
     * @return roomList
     */
    public List<LjRoomInfoDto> getRoomList() {
        return this.roomList;
    }

    /**
     * setter for roomList.
     * 
     * @param aRoomList roomList
     */
    public void setRoomList(final List<LjRoomInfoDto> aRoomList) {
        this.roomList = aRoomList;
    }

    /**
     * getter for bigColumnList.
     * 
     * @return bigColumnList
     */
    public List<KbnDto> getBigColumnList() {
        return this.bigColumnList;
    }

    /**
     * setter for bigColumnList.
     * 
     * @param aBigColumnList bigColumnList
     */
    public void setBigColumnList(final List<KbnDto> aBigColumnList) {
        this.bigColumnList = aBigColumnList;
    }

    /**
     * getter for smallColumList.
     * 
     * @return smallColumList
     */
    public List<KbnDto> getSmallColumList() {
        return this.smallColumList;
    }

    /**
     * setter for smallColumList.
     * 
     * @param aSmallColumList smallColumList
     */
    public void setSmallColumList(final List<KbnDto> aSmallColumList) {
        this.smallColumList = aSmallColumList;
    }

    /**
     * getter for kbnList.
     * 
     * @return kbnList
     */
    public List<KbnDto> getKbnList() {
        return this.kbnList;
    }

    /**
     * setter for kbnList.
     * 
     * @param aKbnList kbnList
     */
    public void setKbnList(final List<KbnDto> aKbnList) {
        this.kbnList = aKbnList;
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public LjRoomInfoDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final LjRoomInfoDto aDto) {
        this.dto = aDto;
    }

}
