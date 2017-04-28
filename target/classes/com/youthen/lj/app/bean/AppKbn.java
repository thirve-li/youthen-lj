// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.bean;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class AppKbn {

    /**
     * 大栏位ID
     */
    private Long id;

    /**
     * 所属类型
     */
    private String type;

    /**
     * 名称
     */
    private String nameCn;

    /**
     * 图标
     */
    private String image;

    /**
     * getter for type.
     * 
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * setter for type.
     * 
     * @param aType type
     */
    public void setType(final String aType) {
        this.type = aType;
    }

    /**
     * getter for nameCn.
     * 
     * @return nameCn
     */
    public String getNameCn() {
        return this.nameCn;
    }

    /**
     * setter for nameCn.
     * 
     * @param aNameCn nameCn
     */
    public void setNameCn(final String aNameCn) {
        this.nameCn = aNameCn;
    }

    /**
     * getter for image.
     * 
     * @return image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * setter for image.
     * 
     * @param aImage image
     */
    public void setImage(final String aImage) {
        this.image = aImage;
    }

    /**
     * getter for id.
     * 
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * setter for id.
     * 
     * @param aId id
     */
    public void setId(final Long aId) {
        this.id = aId;
    }

}
