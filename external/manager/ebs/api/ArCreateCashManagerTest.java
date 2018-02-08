package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ar_create_cash.ObjFssArCashTblVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ar_create_cash.ObjFssArCashVo;

/**
 * @Description 应收收款接口
 * @ClassName ArCreateCashManagerTest
 * @Date 2015年12月1日 下午8:58:08
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ArCreateCashManagerTest extends ISimpleEbsApiTest<ObjFssArCashTblVo> {

    @Override
    protected ObjFssArCashTblVo getApiRequestEntity() {
        List<ObjFssArCashVo> bodyVoList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            ObjFssArCashVo bodyVo = new ObjFssArCashVo();
            bodyVo.setReceiptNumber("TEST-123");
            bodyVo.setReceiptMethod("");
            bodyVo.setCashType("CASH");
            bodyVo.setReceiptDate(new DateTime().toDate());
            bodyVo.setGlDate(new DateTime().toDate());
            bodyVo.setAmount(new BigDecimal("123013131.213131"));
            bodyVo.setCurrencyCode("CNY");
            bodyVoList.add(bodyVo);
        }
        ObjFssArCashTblVo vo = new ObjFssArCashTblVo();
        vo.setList(bodyVoList);
        return vo;
    }
}