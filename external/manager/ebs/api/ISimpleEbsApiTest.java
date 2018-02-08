package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.List;

import com.ronglian.fssc.webapp.external.EbsApiException;
import com.ronglian.fssc.webapp.external.vo.ebs.api.result.ObjFssReturnObjVo;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.ronglian.fssc.webapp.external.vo.ebs.api.request.SimpleApiRequestEntity;

/**
 * @Description TODO
 * @ClassName ISimpleEbsApiTest
 * @Date 2016年1月23日 下午2:30:29
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2016.
 */
public abstract class ISimpleEbsApiTest<REQ extends SimpleApiRequestEntity> extends IEbsApiTest<REQ, ObjFssReturnObjVo> {
    @Override
    public void before() {
        super.before();
        // 同步调用,将异步调用的代码提取出来即可
        req.setpOrganizationId(101l);
        req.setpUserId("2820");
    }

    /**
     * @Method testSyncExecute
     * @Description 同步调用
     * @throws EbsApiException
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testSyncExecute() throws EbsApiException {
        // 请求参数
        System.out.println(JSONObject.toJSONString(req));
        // 调用接口
        ObjFssReturnObjVo res = manager.syncExecute(req);
        // 返回状态
        System.out.println(res.getReturnStatus());
        // 返回MSG
        System.out.println(res.getReturnMsg());
        // EBS任务处理批号
        //System.out.println(res.getBatchId());
        // 错误信息集合
        //        List<String> list = res.getList();
        //        if (CollectionUtils.isEmpty(list)) {
        //            return;
        //        }
        //        for (String msg : list) {
        //            System.out.println(msg);
        //        }

    }
}
