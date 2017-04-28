// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.lj.service.LjNoticeService;
import com.youthen.lj.service.dto.LjNoticeDto;

/**
 * 。
 * 
 * @author gong
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@SuppressWarnings("serial")
@Namespace("/lj-notice")
@Controller
public class LjNoticeAjaxAction extends AbstractAjaxAction {

    /**
     * @see com.youthen.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */
    private final LjNoticeDto dto = new LjNoticeDto();

    private int type;

    @Autowired
    LjNoticeService noticeService;

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    /*
     * public List<LjNoticeDto> getNames() {
     * final LjNoticeDto nDto = new LjNoticeDto();
     * nDto.setType(this.type);
     * return this.noticeService.getNoticeNames(this.dto);
     * }
     */
}
