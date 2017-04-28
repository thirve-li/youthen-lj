// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.youthen.framework.common.annotation.ExecAuthority;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.BeanUtils;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjRepairCplnService;
import com.youthen.lj.service.dto.LjRepairCplnDto;
import com.youthen.lj.service.dto.LjUserBuildingDto;
import com.youthen.lj.utils.UploadUtils;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.KbnDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/lj-repair")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/lj/admin/index.jsp"),
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/repairCpln/list.jsp"),
        @Result(name = "toList", location = "/lj-repair/list.action?dto.type=${dto.type}", type = "redirect"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/repairCpln/entity.jsp"),
        @Result(name = "toDealWith", location = "/WEB-INF/jsp/lj/admin/repairCpln/toDealWith.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/repairCpln/view.jsp")
})
@Controller
@ExecAuthority(functioncd = "MST-ADMIN-001")
@SuppressWarnings("javadoc")
public class LjRepairCplnAction extends BaseAction {

    /**
     * 。
     */
    private static final long serialVersionUID = 3434234123426578L;

    // 报修
    private LjRepairCplnDto dto = new LjRepairCplnDto();

    private LjUserBuildingDto userBuildingDto;

    // 报修集合
    private List<LjRepairCplnDto> repairCplnList;

    // 楼栋号List
    private List<Integer> buildingNoList;

    // 单元号List
    private List<Integer> unitNoList;

    // 房间号List
    private List<Integer> roomNoList;

    // 楼栋号
    private Integer buildNos;

    // 单元号
    private Integer unitNos;

    // 房间号
    private Integer roomNos;

    // 单元代码
    private String roomCode;

    private LoginUser loginUser;
    // 报修或投诉图片
    private List<String> repairCplnName;

    private File[] img;

    private String[] imgFileName;

    private List<String> fileNameList;
    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private LjRepairCplnService ljRepairCplnService;

    @Autowired
    private KbnService kbnService;

    /**
     * 报修或投诉的list列表
     */

    @Action("list")
    public String list() {

        this.repairCplnList = this.ljRepairCplnService.getRepairCplnList(this.dto);

        final int listSize = this.ljRepairCplnService.getRepairCplnCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);
        return "list";

    }

    /**
     * 报修详情或投诉详情
     */
    @Action("view")
    public String view() {
        this.repairCplnList = this.ljRepairCplnService.getRepairCplnList(this.dto);
        this.dto = this.ljRepairCplnService.getById(this.dto.getId());
        this.repairCplnName = new ArrayList<String>();
        if (this.dto.getImage1() != null) {
            this.repairCplnName.add(this.dto.getImage1());
        }
        if (this.dto.getImage2() != null) {
            this.repairCplnName.add(this.dto.getImage2());
        }
        if (this.dto.getImage3() != null) {
            this.repairCplnName.add(this.dto.getImage3());
        }
        if (this.dto.getImage4() != null) {
            this.repairCplnName.add(this.dto.getImage4());
        }
        if (this.dto.getImage5() != null) {
            this.repairCplnName.add(this.dto.getImage5());
        }

        ActionContext.getContext().getSession().put("repairCplnName", this.repairCplnName);
        BeanUtils.setNAProperty(this.dto);
        return "view";

    }

    /**
     * 新增或修改的报修或投诉
     */
    @SuppressWarnings("boxing")
    @Action("entityInit")
    public String entityInit() {
        try {
            KbnDto kDto = new KbnDto();
            // 获得楼号
            kDto = this.kbnService.getById(149L);
            int num = Integer.parseInt(kDto.getCode());
            this.buildingNoList = new ArrayList<Integer>();
            for (int i = 1; i <= num; i++) {
                this.buildingNoList.add(i);
            }

            // 获得单元号
            kDto = this.kbnService.getById(150L);
            num = Integer.parseInt(kDto.getCode());
            this.unitNoList = new ArrayList<Integer>();
            for (int i = 1; i <= num; i++) {
                this.unitNoList.add(i);
            }

            // 获得房间号
            kDto = this.kbnService.getById(151L);
            num = Integer.parseInt(kDto.getCode());
            this.roomNoList = new ArrayList<Integer>();
            for (int i = 1; i <= num; i++) {
                for (int j = 1; j <= 5; j++) {
                    final int roomNo = i * 100 + j;
                    this.roomNoList.add(roomNo);
                }
            }

            if (this.dto != null && this.dto.getId() != null) {
                this.dto = this.ljRepairCplnService.getById(this.dto.getId());
                if (this.dto.getType() == 0) {
                    this.roomCode = this.dto.getRoomCode();
                    this.buildNos = Integer.parseInt(this.roomCode.substring(4, 5));
                    this.roomNos = Integer.parseInt(this.roomCode.substring(6, this.roomCode.length()));
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "entityInit";

    }

    @Action("toDealWith")
    public String toDealWith() {
        this.dto = this.ljRepairCplnService.getById(this.dto.getId());
        return "toDealWith";
    }

    /**
     * 保存操作
     */
    @SuppressWarnings("boxing")
    @Action("saveRepairCpln")
    public String saveRepairCpln() {
        try {
            if (this.img != null) {
                String imageType = "";
                if (this.dto.getType() == 0) {
                    imageType = "repair";
                } else {
                    imageType = "complants";
                }
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
            if (this.dto.getRepairMan() == null) {
                if (this.dto.getType() == 0) {

                    this.roomCode =
                            "MLY-" + this.userBuildingDto.getRoomInfo().getBuildingNo() + "-"
                                    + this.userBuildingDto.getRoomInfo().getRoomNo();

                }

            }

            this.dto.setRoomCode(this.roomCode);
            if (this.dto != null && this.dto.getId() != null) {

                this.ljRepairCplnService.update(this.dto);
                this.dto = this.ljRepairCplnService.getById(this.dto.getId());
                if (this.dto.getStatus() == 1) {
                    this.dto.setStatus(2);
                }
                this.ljRepairCplnService.update(this.dto);
            } else {
                this.dto.setStatus(0);
                this.ljRepairCplnService.insert(this.dto);
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }

        return "toList";
    }

    /**
     * 删除操作
     */
    @Action("delete")
    public String delete() {
        this.ljRepairCplnService.delete(this.dto.getId());
        return list();
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
     * getter for repairCplnList.
     * 
     * @return repairCplnList
     */
    public List<LjRepairCplnDto> getRepairCplnList() {
        return this.repairCplnList;
    }

    /**
     * setter for repairCplnList.
     * 
     * @param aRepairCplnList repairCplnList
     */
    public void setRepairCplnList(final List<LjRepairCplnDto> aRepairCplnList) {
        this.repairCplnList = aRepairCplnList;
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
     * getter for buildingNoList.
     * 
     * @return buildingNoList
     */
    public List<Integer> getBuildingNoList() {
        return this.buildingNoList;
    }

    /**
     * setter for buildingNoList.
     * 
     * @param aBuildingNoList buildingNoList
     */
    public void setBuildingNoList(final List<Integer> aBuildingNoList) {
        this.buildingNoList = aBuildingNoList;
    }

    /**
     * getter for unitNoList.
     * 
     * @return unitNoList
     */
    public List<Integer> getUnitNoList() {
        return this.unitNoList;
    }

    /**
     * setter for unitNoList.
     * 
     * @param aUnitNoList unitNoList
     */
    public void setUnitNoList(final List<Integer> aUnitNoList) {
        this.unitNoList = aUnitNoList;
    }

    /**
     * getter for roomNoList.
     * 
     * @return roomNoList
     */
    public List<Integer> getRoomNoList() {
        return this.roomNoList;
    }

    /**
     * setter for roomNoList.
     * 
     * @param aRoomNoList roomNoList
     */
    public void setRoomNoList(final List<Integer> aRoomNoList) {
        this.roomNoList = aRoomNoList;
    }

    /**
     * getter for repairCplnName.
     * 
     * @return repairCplnName
     */
    public List<String> getRepairCplnName() {
        return this.repairCplnName;
    }

    /**
     * setter for repairCplnName.
     * 
     * @param aRepairCplnName repairCplnName
     */
    public void setRepairCplnName(final List<String> aRepairCplnName) {
        this.repairCplnName = aRepairCplnName;
    }

    /**
     * getter for userBuildingDto.
     * 
     * @return userBuildingDto
     */
    public LjUserBuildingDto getUserBuildingDto() {
        return this.userBuildingDto;
    }

    /**
     * setter for userBuildingDto.
     * 
     * @param aUserBuildingDto userBuildingDto
     */
    public void setUserBuildingDto(final LjUserBuildingDto aUserBuildingDto) {
        this.userBuildingDto = aUserBuildingDto;
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

    /**
     * getter for roomCode.
     * 
     * @return roomCode
     */
    public String getRoomCode() {
        return this.roomCode;
    }

    /**
     * setter for roomCode.
     * 
     * @param aRoomCode roomCode
     */
    public void setRoomCode(final String aRoomCode) {
        this.roomCode = aRoomCode;
    }

}
