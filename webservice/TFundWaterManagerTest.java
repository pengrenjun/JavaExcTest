package com.ronglian.fssc.webapp.webservice;

import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.manager.fund.TFundWaterManager;
import com.ronglian.fssc.webapp.platform.vo.pay.PayBillQueryReqParamVo;
import com.ronglian.fssc.webapp.platform.vo.pay.PayBillQueryReturnParamVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description 收款查询接口测试 <br/>
 * @Author柳小龙 <br/>
 * @Date 16/8/5 15:08 <br/>
 * @Mail: xlliu2@163.com <br/>
 * @Copyright (c) All Rights Reserved, 2016~2018.
 */
public class TFundWaterManagerTest extends TestBase {
    @Autowired
    private TFundWaterManager manager;

    @Test
    public void testQueryPayBillInfo(){
        PayBillQueryReqParamVo reqVo = new PayBillQueryReqParamVo();
        reqVo.setCorpName("荣联本部"); // 单位名称
        //reqVo.setBankAcc("600020922302015"); // 银行账户
        //reqVo.setPageIndex("1");
        //reqVo.setPageSize("2");
        //reqVo.setStratDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date())); //
        //reqVo.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date()));
        // List<PayBillQueryReturnParamVo> reslut =  manager.queryPayBillInfo(reqVo);
        // Assert.assertNotNull(reslut);
    }
}
