package com.ronglian.fssc.webapp.webservice;

import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.manager.fund.TFundPayReqManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @Description 付款接口测试 <br/>
 * @Author柳小龙 <br/>
 * @Date 16/8/5 15:08 <br/>
 * @Mail: xlliu2@163.com <br/>
 * @Copyright (c) All Rights Reserved, 2016~2018.
 */
@Rollback(value = false)
public class TFundPayReqManagerTest  extends TestBase {
    @Autowired
    private TFundPayReqManager manager;

    @Test
    public void pay() throws Exception {
       // manager.payReqBiz();
    }
}
