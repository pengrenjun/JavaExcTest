package com.ronglian.fssc.webapp.external.manager.erp;

import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.external.vo.erp.ErpFsscResponseVo;
import com.ronglian.fssc.webapp.external.vo.erp.PaymentApplyStatusPassBackVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 讯博(erp)提供给FSSC调用接口
 * @ClassName ErpInterfaceManagerTest
 * @Date 2015年12月18日 下午4:06:03
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ErpInterfaceManagerTest extends TestBase {

    @Autowired
    private ErpInterfaceManager erpInterfaceManager;

    /**
     * @Method testPaymentApplyStatusPassBack
     * @Description 2.2.4 付款申请状态回传
     * @throws Exception
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testPaymentApplyStatusPassBack() throws Exception {
        PaymentApplyStatusPassBackVo reqVo = new PaymentApplyStatusPassBackVo();
        ErpFsscResponseVo response = erpInterfaceManager.paymentApplyStatusPassBack(reqVo);
        System.out.println(response);
        // String reqJson = JSONObject.toJSONString(reqVo);
        // Object[] resParams = erpWebServiceClient.invoke("", reqJson);
        // return JSONObject.parseObject(String.valueOf(resParams[0]), ErpFsscResponseVo.class);
    }
}
