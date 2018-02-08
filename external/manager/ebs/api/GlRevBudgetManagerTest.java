package com.ronglian.fssc.webapp.external.manager.ebs.api;

import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_rev_budget.ObjFssGlRevTblVo;

/**
 * @Description 冲销保留款
 * @ClassName GlRevBudgetManagerTest
 * @Date 2015年12月16日 上午10:38:33
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class GlRevBudgetManagerTest extends ISimpleEbsApiTest<ObjFssGlRevTblVo> {

    @Override
    protected ObjFssGlRevTblVo getApiRequestEntity() {
        ObjFssGlRevTblVo obj = new ObjFssGlRevTblVo();
        obj.setEcCodes("1", "2", "3");
        return obj;
    }

}
