package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ar_reverse.ObjFssArCashReversalTblVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ar_reverse.ObjFssArCashReversalVo;

/**
 * @Description 应收收款冲销接口
 * @ClassName ArReverseManagerTest
 * @Date 2015年12月11日 下午4:52:13
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ArReverseManagerTest extends ISimpleEbsApiTest<ObjFssArCashReversalTblVo> {

    @Override
    protected ObjFssArCashReversalTblVo getApiRequestEntity() {
        List<ObjFssArCashReversalVo> bodyVoList = Lists.newArrayList();
        ObjFssArCashReversalVo bodyVo = null;
        for (int i = 0; i < 10; i++) {
            bodyVo = new ObjFssArCashReversalVo();
            bodyVo.setReceiptNumber("TEST-123");
            bodyVo.setReversalGlDate(new Date());
            bodyVo.setReversalReasonCode("CNY");
            bodyVo.setReversalCategoryCode("CNY");
            bodyVoList.add(bodyVo);
        }
        ObjFssArCashReversalTblVo vo = new ObjFssArCashReversalTblVo();
        vo.setList(bodyVoList);
        return vo;
    }

}
