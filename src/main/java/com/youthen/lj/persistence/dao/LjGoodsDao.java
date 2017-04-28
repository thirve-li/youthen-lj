// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.persistence.dao;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import com.youthen.framework.persistence.dao.impl.EntityDaoImpl;
import com.youthen.lj.persistence.entity.LjGoods;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Repository("ljGoodsDao")
public class LjGoodsDao extends EntityDaoImpl<LjGoods> {

    /**
     * @see com.youthen.framework.persistence.dao.impl.EntityDaoImpl#injectType()
     */

    @Override
    @PostConstruct
    public void injectType() {
        this.setType(LjGoods.class);
    }

}
