// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.youthen.framework.common.annotation.ExecAuthority;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.CommonUtils;
import com.youthen.lj.service.LjGoodsService;
import com.youthen.lj.service.LjMerchantService;
import com.youthen.lj.service.dto.LjGoodsDto;
import com.youthen.lj.service.dto.LjMerchantDto;
import com.youthen.lj.utils.UploadUtils;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.MstKbnService;
import com.youthen.master.service.dto.KbnDto;
import com.youthen.master.util.CommonUtil;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/lj-goods")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/lj/admin/goods/list.jsp"),
        @Result(name = "goodsTypeList", location = "/WEB-INF/jsp/lj/admin/goods/goodsTypeList.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/lj/admin/goods/entity.jsp"),
        @Result(name = "goodsTypeEntityInit", location = "/WEB-INF/jsp/lj/admin/goods/goodsTypeEntity.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/lj/admin/goods/view.jsp"),
        @Result(name = "tolist", location = "/lj-goods/list.action?dto.isSecond=${dto.isSecond}", type = "redirect"),
        @Result(name = "toGoodsTypeList", location = "/lj-goods/goodsTypeList.action", type = "redirect"),
})
@Controller
@ExecAuthority(functioncd = "MST-ADMIN-001")
@SuppressWarnings("javadoc")
public class LjGoodsAction extends BaseAction {

    private LjGoodsDto dto = new LjGoodsDto();

    private KbnDto kbnDto = new KbnDto();

    private List<LjGoodsDto> goodsList;

    private List<KbnDto> kbnList;

    private List<KbnDto> bigKbnList;

    private List<KbnDto> smallGoodsTypeList;

    private List<LjMerchantDto> shopList;

    private List<String> GoodsName;

    private File[] img;

    private String[] imgFileName;

    private List<String> fileNameList;

    @Autowired
    private KbnService kbnService;

    @Autowired
    private LjMerchantService merchantService;

    @Autowired
    private MstKbnService mstKbnService;

    @Autowired
    private LjGoodsService goodsService;

    @Action("list")
    public String list() {

        if (this.dto.getBigGoodsTypeId() != null) {
            this.smallGoodsTypeList = this.kbnService.getByParentTypeId(this.dto.getBigGoodsTypeId());
        }
        this.kbnList = this.kbnService.getKbnListByType("LJ_BIG_GOODTYPE");
        this.goodsList = this.goodsService.getGoodsList(this.dto);
        final int listSize = this.goodsService.getGoodsCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);

        return "list";
    }

    @Action("goodsTypeList")
    public String goodsTypeList() {

        this.bigKbnList = this.kbnService.getKbnListByType("LJ_BIG_GOODTYPE");
        this.kbnList = this.kbnService.getKbnListByType("LJ_SMALL_GOODTYPE");
        for (int i = 0; i < this.bigKbnList.size(); i++) {
            this.kbnList.add(this.bigKbnList.get(i));
        }

        return "goodsTypeList";
    }

    @Action("entityInit")
    public String entityInit() {

        if (this.dto.getIsSecond() != 1) {

            this.kbnList = this.kbnService.getKbnListByType("LJ_BIG_BLD");
            final LjMerchantDto merchantDto = new LjMerchantDto();
            merchantDto.setBigTypeId(this.kbnList.get(0).getId());
            this.shopList = this.merchantService.getMerchantList(merchantDto);
        }

        this.kbnList = this.kbnService.getKbnListByType("LJ_BIG_GOODTYPE");
        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.goodsService.getById(this.dto.getId());
            this.smallGoodsTypeList = this.kbnService.getByParentTypeId(this.dto.getBigGoodsTypeId());
        }

        return "entityInit";
    }

    @Action("goodsTypeEntityInit")
    public String goodsTypeEntityInit() {

        this.bigKbnList = this.kbnService.getKbnListByType("LJ_BIG_GOODTYPE");
        if (this.kbnDto != null & this.kbnDto.getId() != null) {
            this.kbnDto = this.kbnService.getById(this.kbnDto.getId());
        }

        return "goodsTypeEntityInit";
    }

    @SuppressWarnings("boxing")
    @Action("saveGoods")
    public String saveGoods() {
        try {
            if (this.img != null) {
                String imageType = "";
                if (this.dto.getIsSecond() == 0) {
                    imageType = "goods";
                } else {
                    imageType = "secondGoods";
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

        this.dto.setStatus(1);
        if (this.dto.getIsSecond() != 0) {

            this.dto.setCreaterId(getSessionUser().getUserId());
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final long l = System.currentTimeMillis();
            this.dto.setCreateTime(CommonUtil.strToDateLong(dateFormat.format(new Date(l))));
        }

        if (this.dto != null && this.dto.getId() != null) {
            this.goodsService.update(this.dto);
        } else {
            try {
                this.goodsService.insert(this.dto);
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
            }
        }

        return "tolist";
    }

    @Action("saveGoodsType")
    public String saveGoodsType() {

        if (this.kbnDto.getParentTypeId() != null) {
            this.kbnDto.setType("LJ_SMALL_GOODTYPE");
        } else {
            this.kbnDto.setType("LJ_BIG_GOODTYPE");
        }
        this.kbnDto.setStatus(1L);

        if (this.kbnDto != null && this.kbnDto.getId() != null) {
            this.mstKbnService.editKbn(this.kbnDto);
        } else {
            this.mstKbnService.addKbn(this.kbnDto);
        }

        return "toGoodsTypeList";
    }

    @Action("view")
    public String view() {
        this.dto = this.goodsService.getById(this.dto.getId());
        this.GoodsName = new ArrayList<String>();
        if (this.dto.getImage1() != null) {
            this.GoodsName.add(this.dto.getImage1());
        }
        if (this.dto.getImage2() != null) {
            this.GoodsName.add(this.dto.getImage2());
        }
        if (this.dto.getImage3() != null) {
            this.GoodsName.add(this.dto.getImage3());
        }
        if (this.dto.getImage4() != null) {
            this.GoodsName.add(this.dto.getImage4());
        }
        if (this.dto.getImage5() != null) {
            this.GoodsName.add(this.dto.getImage5());
        }

        ActionContext.getContext().getSession().put("GoodsName", this.GoodsName);
        return "view";
    }

    @Action("deleteGoods")
    public String deleteGoods() {
        this.dto.setStatus(0);
        this.goodsService.update(this.dto);
        return "tolist";
    }

    @Action("deleteGoodsType")
    public String deleteGoodsType() {
        this.kbnDto.setStatus(0L);
        this.mstKbnService.editKbn(this.kbnDto);
        return "toGoodsTypeList";
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public LjGoodsDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final LjGoodsDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for goodsList.
     * 
     * @return goodsList
     */
    public List<LjGoodsDto> getGoodsList() {
        return this.goodsList;
    }

    /**
     * setter for goodsList.
     * 
     * @param aGoodsList goodsList
     */
    public void setGoodsList(final List<LjGoodsDto> aGoodsList) {
        this.goodsList = aGoodsList;
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
     * getter for smallGoodsTypeList.
     * 
     * @return smallGoodsTypeList
     */
    public List<KbnDto> getSmallGoodsTypeList() {
        return this.smallGoodsTypeList;
    }

    /**
     * setter for smallGoodsTypeList.
     * 
     * @param aSmallGoodsTypeList smallGoodsTypeList
     */
    public void setSmallGoodsTypeList(final List<KbnDto> aSmallGoodsTypeList) {
        this.smallGoodsTypeList = aSmallGoodsTypeList;
    }

    /**
     * getter for goodsName.
     * 
     * @return goodsName
     */
    public List<String> getGoodsName() {
        return this.GoodsName;
    }

    /**
     * setter for goodsName.
     * 
     * @param aGoodsName goodsName
     */
    public void setGoodsName(final List<String> aGoodsName) {
        this.GoodsName = aGoodsName;
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
     * getter for kbnDto.
     * 
     * @return kbnDto
     */
    public KbnDto getKbnDto() {
        return this.kbnDto;
    }

    /**
     * setter for kbnDto.
     * 
     * @param aKbnDto kbnDto
     */
    public void setKbnDto(final KbnDto aKbnDto) {
        this.kbnDto = aKbnDto;
    }

    /**
     * getter for bigKbnList.
     * 
     * @return bigKbnList
     */
    public List<KbnDto> getBigKbnList() {
        return this.bigKbnList;
    }

    /**
     * setter for bigKbnList.
     * 
     * @param aBigKbnList bigKbnList
     */
    public void setBigKbnList(final List<KbnDto> aBigKbnList) {
        this.bigKbnList = aBigKbnList;
    }

    /**
     * getter for shopList.
     * 
     * @return shopList
     */
    public List<LjMerchantDto> getShopList() {
        return this.shopList;
    }

    /**
     * setter for shopList.
     * 
     * @param aShopList shopList
     */
    public void setShopList(final List<LjMerchantDto> aShopList) {
        this.shopList = aShopList;
    }

}
