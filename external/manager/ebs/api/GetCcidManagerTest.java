package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.ronglian.fssc.webapp.external.vo.ebs.api.get_ccid.GetCcidApiRequestVo;
import com.ronglian.fssc.webapp.external.EbsApiException;
import com.ronglian.fssc.webapp.external.vo.ebs.api.result.GetCcidResponseVo;

/**
 * @Description 获取账户组合的CCID
 * @ClassName GetCcidManagerTest
 * @Date 2016年1月23日 下午4:25:08
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2016.
 */
public class GetCcidManagerTest extends IEbsApiTest<GetCcidApiRequestVo, GetCcidResponseVo> {

    @Override
    protected GetCcidApiRequestVo getApiRequestEntity() {
        GetCcidApiRequestVo reqVo = new GetCcidApiRequestVo();
        reqVo.setpConcatenatedSegments("0.0.0.0.0.0.0.0");
        reqVo.setpCoa(1l);
        reqVo.setpCheckDate(new Date());
        return reqVo;
    }

    @Override
    public void testSyncExecute() throws EbsApiException {
        // 请求参数
        System.out.println(JSONObject.toJSONString(req));
        // 调用接口
        GetCcidResponseVo res = manager.syncExecute(req);
        // 返回状态
        System.out.println(res.getReturnStatus());
        System.out.println(res.getpCcid());
        System.out.println(res.getpErrMsg());
    }

}
