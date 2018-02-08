package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ar_apply.ObjFssArApplyTblVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ar_apply.ObjFssArApplyVo;

/**
 * @Description 应收收款核销（取消核销）接口
 * @ClassName ArApplyManagerTest
 * @Date 2015年12月11日 下午4:02:39
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ArApplyManagerTest extends ISimpleEbsApiTest<ObjFssArApplyTblVo> {

    @Override
    protected ObjFssArApplyTblVo getApiRequestEntity() {
        List<ObjFssArApplyVo> bodyVoList = Lists.newArrayList();
        ObjFssArApplyVo bodyVo = null;
        for (int i = 0; i < 10; i++) {
            bodyVo = new ObjFssArApplyVo();
            bodyVo.setReceiptNumber("TEST-123");
            bodyVo.setApplyType("1");
            bodyVo.setTrxNumber("1");
            bodyVo.setAmountApplied(10.1);
            bodyVo.setDiscount(0.0);
            bodyVo.setApplyDate(new Date());
            bodyVo.setApplyGlDate(new Date());
            bodyVoList.add(bodyVo);
        }
        ObjFssArApplyTblVo vo = new ObjFssArApplyTblVo();
        vo.setArApplyList(bodyVoList);
        return vo;
    }

}
