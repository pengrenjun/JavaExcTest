package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_check_budget.ObjFssGlBudgetHVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_check_budget.GlCheckBudgetReqVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_check_budget.ObjFssGlBudgetLVo;

/**
 * @Description 预算控制
 * @ClassName GlCheckBudgetManagerTest
 * @Date 2015年12月12日 下午4:24:29
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class GlCheckBudgetManagerTest extends ISimpleEbsApiTest<GlCheckBudgetReqVo> {

    @Override
    protected GlCheckBudgetReqVo getApiRequestEntity() {
        List<ObjFssGlBudgetLVo> list = Lists.newArrayList();
        ObjFssGlBudgetLVo lvo = null;
        for (int i = 0; i < 5; i++) {
            lvo = new ObjFssGlBudgetLVo();
            lvo.setDrAmount(new BigDecimal("10.0"));// 对应DR_AMOUNT
            lvo.setCrAmount(new BigDecimal("10.0"));// 对应CR_AMOUNT
            list.add(lvo);
        }
        ObjFssGlBudgetHVo hvo = new ObjFssGlBudgetHVo();
        hvo.setCurrencyCode("CNY");
        hvo.setList(list);
        hvo.setGlDate(new Date());
        GlCheckBudgetReqVo vo = new GlCheckBudgetReqVo();
        vo.setVo(hvo);
        return vo;
    }

}
