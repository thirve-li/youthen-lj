// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.dto.KbnDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@SuppressWarnings("serial")
@Namespace("/lj-goods")
@Controller
public class LjGoodsAjaxAction extends AbstractAjaxAction {

    Long bigGoodsTypeId;

    @Autowired
    KbnService kbnService;

    /**
     * @see com.youthen.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    public List<KbnDto> getSmallGoodsTypeList() {

        final List<KbnDto> smallGoodsTypeList = this.kbnService.getByParentTypeId(this.bigGoodsTypeId);

        return smallGoodsTypeList;

    }

    /**
     * getter for bigGoodsTypeId.
     * 
     * @return bigGoodsTypeId
     */
    public Long getBigGoodsTypeId() {
        return this.bigGoodsTypeId;
    }

    /**
     * setter for bigGoodsTypeId.
     * 
     * @param aBigGoodsTypeId bigGoodsTypeId
     */
    public void setBigGoodsTypeId(final Long aBigGoodsTypeId) {
        this.bigGoodsTypeId = aBigGoodsTypeId;
    }

}
