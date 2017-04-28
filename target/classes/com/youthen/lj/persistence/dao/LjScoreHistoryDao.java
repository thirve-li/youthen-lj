// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.dao;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import com.youthen.framework.persistence.dao.impl.EntityDaoImpl;
import com.youthen.lj.persistence.entity.LjScoreHistory;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Repository("LjScoreHistoryDao")
public class LjScoreHistoryDao extends EntityDaoImpl<LjScoreHistory> {

    /**
     * @see com.youthen.framework.persistence.dao.impl.EntityDaoImpl#injectType()
     */

    @Override
    @PostConstruct
    public void injectType() {
        this.setType(LjScoreHistory.class);
    }

}
