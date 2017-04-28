// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.lj.app.logic.LjAppLogic;
import com.youthen.lj.utils.UploadUtils;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.dto.KbnDto;

/**
 * 。
 * 快递管理Action
 * 
 * @author Mazj
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Namespace("/lj-kuaidi")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/kuaidi/list.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/kuaidi/entity.jsp"),
        @Result(name = "tolist", location = "/lj-kuaidi/kuaiDiList.action?dto.type=EXPRESS_COMPANY", type = "redirect")
})
@Controller
public class LjKuaiDiAction extends BaseAction {

    private KbnDto dto = new KbnDto();

    private List<KbnDto> kbnList = new ArrayList<KbnDto>();

    private File[] img;

    private List<String> fileNameList;

    private String[] imgFileName;

    @Autowired
    private KbnService kbnService;

    static LjAppLogic ljal;

    @Action("kuaiDiList")
    public String list() {
        this.kbnList = this.kbnService.getKbnListBy(this.dto);

        return "list";
    }

    @Action("entityInit")
    public String entityInit() {
        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.kbnService.getById(this.dto.getId());
        }
        return "entityInit";
    }

    @Action("saveKuaidi")
    public String saveKuaidi() {
        if (this.img != null) {
            final String imageType = "EXPRESS_COMPANY";
            try {
                this.fileNameList = UploadUtils.uploadImages(this.img, this.imgFileName, imageType);

                this.dto.setImage(this.fileNameList.get(0));

            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        if (this.dto != null && this.dto.getId() != null) {
            final long s = 1;
            this.dto.setStatus(s);
            this.kbnService.update(this.dto);
        } else {
            this.dto.setType("EXPRESS_COMPANY");
            final long s = 1;
            this.dto.setStatus(s);
            this.kbnService.insert(this.dto);
        }
        return "tolist";
    }

    @Action("deletekuaidi")
    public String deletekuaidi() {
        final long s1 = 0;
        this.dto.setStatus(s1);
        this.kbnService.update(this.dto);
        return "tolist";
    }

    @Action("applist")
    public String applist() {
        this.kbnList = this.kbnService.getKbnListBy(this.dto);

        return "list";
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
    public KbnDto getDto() {
        return this.dto;
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

}
