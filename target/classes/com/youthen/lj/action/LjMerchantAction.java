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
import com.opensymphony.xwork2.ActionContext;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjMerchantService;
import com.youthen.lj.service.dto.LjMerchantDto;
import com.youthen.lj.utils.UploadUtils;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.MstKbnService;
import com.youthen.master.service.dto.KbnDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Namespace("/lj-merchant")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/merchant/list.jsp"),
        @Result(name = "typeList", location = "/WEB-INF/jsp/lj/admin/merchant/typeList.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/merchant/entity.jsp"),
        @Result(name = "typeEntityInit", location = "/WEB-INF/jsp/lj/admin/merchant/typeEntity.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/merchant/view.jsp"),
        @Result(name = "tolist", location = "/lj-merchant/list.action?typeCode=${typeCode}", type = "redirect"),
        @Result(name = "toTypeList", location = "/lj-merchant/typeList.action?typeCode=${typeCode}", type = "redirect")
})
@Controller
public class LjMerchantAction extends BaseAction {

    private LjMerchantDto dto = new LjMerchantDto();

    private List<LjMerchantDto> merchantList;

    private List<KbnDto> kbnList;

    private KbnDto kbnTypeDto = new KbnDto();

    private String typeCode;

    private LoginUser loginUser;

    private List<String> merchantImgList;

    private File[] img;

    private String[] imgFileName;

    private List<String> fileNameList;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private LjMerchantService merchantService;

    @Autowired
    private KbnService kbnService;

    @Autowired
    private MstKbnService mstKbnService;

    @Action("list")
    public String list() {
        final KbnDto kbnDto = new KbnDto();
        kbnDto.setCode(this.typeCode);
        final List<KbnDto> kDtoList = this.kbnService.getKbnListBy(kbnDto);

        this.kbnList = this.kbnService.getByParentTypeId(kDtoList.get(0).getId());
        this.dto.setBigTypeId(kDtoList.get(0).getId());
        this.merchantList = this.merchantService.getMerchantList(this.dto);
        final int listSize = this.merchantService.getMerchantCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);

        return "list";
    }

    @Action("typeList")
    public String typeList() {
        final KbnDto kbnDto = new KbnDto();
        kbnDto.setCode(this.typeCode);
        final List<KbnDto> kDtoList = this.kbnService.getKbnListBy(kbnDto);

        this.kbnList = this.kbnService.getByParentTypeId(kDtoList.get(0).getId());

        return "typeList";
    }

    @Action("entityInit")
    public String entityInit() {
        final KbnDto kbnDto = new KbnDto();
        kbnDto.setCode(this.typeCode);
        final List<KbnDto> kDtoList = this.kbnService.getKbnListBy(kbnDto);

        this.kbnList = this.kbnService.getByParentTypeId(kDtoList.get(0).getId());
        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.merchantService.getById(this.dto.getId());
        }

        return "entityInit";
    }

    @Action("typeEntityInit")
    public String typeEntityInit() {

        if (this.kbnTypeDto != null && this.kbnTypeDto.getId() != null) {
            this.kbnTypeDto = this.kbnService.getById(this.kbnTypeDto.getId());
        }
        return "typeEntityInit";
    }

    @Action("saveMerchant")
    public String saveMerchant() {

        try {
            if (this.img != null) {
                String imageType = "";
                if ("JZFW".equals(this.typeCode)) {
                    imageType = "JZFW";
                } else {
                    imageType = "SQHY";
                }

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
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final KbnDto kbnDto = new KbnDto();
        kbnDto.setCode(this.typeCode);
        final List<KbnDto> kDtoList = this.kbnService.getKbnListBy(kbnDto);
        this.dto.setBigTypeId(kDtoList.get(0).getId());
        this.dto.setStatus(1);
        if (this.dto != null && this.dto.getId() != null) {
            this.merchantService.update(this.dto);
        } else {
            try {
                this.merchantService.insert(this.dto);
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
            }
        }
        return "tolist";
    }

    @Action("saveMerchantType")
    public String saveMerchantType() {
        try {
            if (this.img != null) {
                String imageType = "";
                if ("JZFW".equals(this.typeCode)) {
                    imageType = "JZFW";
                } else {
                    imageType = "SQHY";
                }

                this.fileNameList = UploadUtils.uploadImages(this.img, this.imgFileName, imageType);
                this.kbnTypeDto.setImage(this.fileNameList.get(0));
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final KbnDto kbnDto = new KbnDto();
        kbnDto.setCode(this.typeCode);
        final List<KbnDto> kDtoList = this.kbnService.getKbnListBy(kbnDto);
        this.kbnTypeDto.setParentTypeId(kDtoList.get(0).getId());
        if (this.kbnTypeDto != null && this.kbnTypeDto.getId() != null) {
            this.mstKbnService.editKbn(this.kbnTypeDto);
        } else {
            this.mstKbnService.addKbn(this.kbnTypeDto);
        }

        return "toTypeList";
    }

    @Action("deleteMerchant")
    public String deleteMerchant() {

        this.dto.setStatus(0);
        this.merchantService.update(this.dto);

        return "tolist";
    }

    @Action("deleteTypeMerchant")
    public String deleteTypeMerchant() {
        this.kbnTypeDto.setStatus(0L);
        this.mstKbnService.editKbn(this.kbnTypeDto);
        return "toTypeList";
    }

    @Action("view")
    public String view() {
        this.dto = this.merchantService.getById(this.dto.getId());
        this.merchantImgList = new ArrayList<String>();
        if (this.dto.getImage1() != null) {
            this.merchantImgList.add(this.dto.getImage1());
        }
        if (this.dto.getImage2() != null) {
            this.merchantImgList.add(this.dto.getImage2());
        }
        if (this.dto.getImage3() != null) {
            this.merchantImgList.add(this.dto.getImage3());
        }
        if (this.dto.getImage4() != null) {
            this.merchantImgList.add(this.dto.getImage4());
        }
        if (this.dto.getImage5() != null) {
            this.merchantImgList.add(this.dto.getImage5());
        }

        ActionContext.getContext().getSession().put("merchantImgList", this.merchantImgList);
        return "view";
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public LjMerchantDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final LjMerchantDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for merchantList.
     * 
     * @return merchantList
     */
    public List<LjMerchantDto> getMerchantList() {
        return this.merchantList;
    }

    /**
     * setter for merchantList.
     * 
     * @param aMerchantList merchantList
     */
    public void setMerchantList(final List<LjMerchantDto> aMerchantList) {
        this.merchantList = aMerchantList;
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
     * getter for kbnTypeDto.
     * 
     * @return kbnTypeDto
     */
    public KbnDto getKbnTypeDto() {
        return this.kbnTypeDto;
    }

    /**
     * setter for kbnTypeDto.
     * 
     * @param aKbnTypeDto kbnTypeDto
     */
    public void setKbnTypeDto(final KbnDto aKbnTypeDto) {
        this.kbnTypeDto = aKbnTypeDto;
    }

    /**
     * getter for typeCode.
     * 
     * @return typeCode
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * setter for typeCode.
     * 
     * @param aTypeCode typeCode
     */
    public void setTypeCode(final String aTypeCode) {
        this.typeCode = aTypeCode;
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
     * getter for merchantImgList.
     * 
     * @return merchantImgList
     */
    public List<String> getMerchantImgList() {
        return this.merchantImgList;
    }

    /**
     * setter for merchantImgList.
     * 
     * @param aMerchantImgList merchantImgList
     */
    public void setMerchantImgList(final List<String> aMerchantImgList) {
        this.merchantImgList = aMerchantImgList;
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

}
