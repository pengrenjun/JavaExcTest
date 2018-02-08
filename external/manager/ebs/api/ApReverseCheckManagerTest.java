package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_reverse_check.ObjFssApReverseCheckTblVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_reverse_check.ObjFssApReverseCheckVo;

/**
 * @Description 应付取消付款接口
 * @ClassName ApReverseCheckManagerTest
 * @Date 2015年12月17日 下午5:59:19
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ApReverseCheckManagerTest extends ISimpleEbsApiTest<ObjFssApReverseCheckTblVo> {

    @Override
    protected ObjFssApReverseCheckTblVo getApiRequestEntity() {
        ObjFssApReverseCheckVo checkVo = null;
        List<ObjFssApReverseCheckVo> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            checkVo = new ObjFssApReverseCheckVo();
            checkVo.setPaymentNumber("10");
            checkVo.setReversalDate(new Date());
            list.add(checkVo);
        }
        ObjFssApReverseCheckTblVo vo = new ObjFssApReverseCheckTblVo();
        vo.setList(list);
        return vo;
    }

}
