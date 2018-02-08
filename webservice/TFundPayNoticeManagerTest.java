package com.ronglian.fssc.webapp.webservice;

import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.manager.fund.TFundPayNoticeManager;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @Description 付款通知接口测试<br/>
 * @Author柳小龙 <br/>
 * @Date 16/8/5 15:06 <br/>
 * @Mail: xlliu2@163.com <br/>
 * @Copyright (c) All Rights Reserved, 2016~2018.
 */
@Rollback(value = true)
public class TFundPayNoticeManagerTest extends TestBase{
    @Autowired
    private TFundPayNoticeManager manager;

    /**
     * 测试付款通知
     */
    @Test
    public void testPayNoticeBiz(){
//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//                "<root>" +
//                    "<row>" +
//                        "<payId>53</payId>" +
//                        "<payerName>昊天旭辉</payerName>" +
//                        "<payerAcc>1111111111111</payerAcc>" +
//                        "<payType>01</payType>" +
//                        "<dates>2016/06/24 17:58:00</dates>" +
//                        "<isPay>1</isPay>" +
//                        "<remark>现金支付测试</remark>" +
//                        "<ticketNum>2222222222222</ticketNum>" +
//                        "<billingDate>2016/06/26 18:58:00</billingDate>" +
//                        "<bank>XXXX</bank>" +
//                        "<ticketingDate>2016/06/26 18:59:11</ticketingDate>" +
//                        "<expiryDate>2016/06/26 18:59:11</expiryDate>" +
//                        "<resultCode>S</resultCode>" +
//                        "<resultMsg>SUCCESS</resultMsg>" +
//                        "<serialId>323232</serialId>" +
//                        "<reserve1>1</reserve1>" +
//                        "<reserve2>2</reserve2>" +
//                        "<reserve3>3</reserve3>" +
//                        "<reserve4>4</reserve4>" +
//                        "<reserve5>5</reserve5>" +
//                        "<reserve6>6</reserve6>" +
//                "</row>" +
//                "<row>" +
//                        "<payId>54</payId>" +
//                        "<payStatus>F</payStatus>" +
//                        "<resultMsg>收款账户不正确</resultMsg>" +
//                        "<dates>2016/06/24 17:58:00</dates>" +
//                        "<serialId>22222</serialId>" +
//                        "<reserve1>1</reserve1>" +
//                        "<reserve2>2</reserve2>" +
//                        "<reserve3>3</reserve3>" +
//                        "<reserve4>4</reserve4>" +
//                        "<reserve5>5</reserve5>" +
//                        "<reserve6>6</reserve6>" +
//                    "</row>" +
//                "</root>";
        String xml =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><row><isPay></isPay><ticketNum></ticketNum><resultCode>0</resultCode><billingDate></billingDate><ticketingDate></ticketingDate><resultMsg>交易成功。接口返回：(0)发送交易成功。/银行返回：(0)4|422397</resultMsg><dates>2016-08-23</dates><remark></remark><reserve1></reserve1><serial_id>201608231455475627450415</serial_id><bank></bank><expiryDate></expiryDate><corpname>北京荣之联科技股份有限公司(本部)</corpname><reserve2></reserve2><pay_type>34</pay_type><reserve3></reserve3></row><row><isPay>现金</isPay><ticketNum></ticketNum><resultCode>测试付款接口</resultCode><billingDate></billingDate><ticketingDate></ticketingDate><resultMsg>测试付款接口</resultMsg><dates>2016-08-21</dates><remark>现金</remark><reserve1></reserve1><serial_id>10211201607001126</serial_id><bank></bank><expiryDate></expiryDate><corpname>荣之联集团</corpname><reserve2></reserve2><pay_type>04</pay_type><reserve3></reserve3></row></root>";

        String result = manager.saveAndUpdateNoticeBiz(xml,"");
        Assert.assertNotNull(result);
    }


}
