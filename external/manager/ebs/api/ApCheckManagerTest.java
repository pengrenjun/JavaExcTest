package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.List;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_check.ObjFssApCheckHTblVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_check.ObjFssApCheckHVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_check.ObjFssApCheckLVo;

/**
 * @Description 应付付款接口
 * @ClassName ApCheckManagerTest
 * @Date 2015年12月19日 下午6:07:03
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ApCheckManagerTest extends ISimpleEbsApiTest<ObjFssApCheckHTblVo> {

    @Override
    protected ObjFssApCheckHTblVo getApiRequestEntity() {
        ObjFssApCheckHVo hVo = null;
        ObjFssApCheckLVo lVo = null;
        List<ObjFssApCheckHVo> hVoList = Lists.newArrayList();
        List<ObjFssApCheckLVo> lVoList;
        for (int i = 0; i < 1; i++) {
            lVoList = Lists.newArrayList();
            for (int j = 0; j < 1; j++) {
                lVo = new ObjFssApCheckLVo();
                lVoList.add(lVo);
            }
            hVo = new ObjFssApCheckHVo();
            hVo.setList(lVoList);
            hVoList.add(hVo);
        }
        ObjFssApCheckHTblVo vo = new ObjFssApCheckHTblVo();
        vo.setList(hVoList);
        return vo;
    }

}
