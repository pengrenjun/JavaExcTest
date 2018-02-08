package com.ronglian.fssc.webapp;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.JAXBException;

import com.ronglian.fssc.webapp.external.EbsApiException;
import com.ronglian.fssc.webapp.external.vo.nc.request.BasePaymentSettlementReqVo;
import com.ronglian.fssc.webapp.external.vo.nc.request.PublicPaymentSettlementReqVo;
import com.ronglian.fssc.webapp.external.vo.nc.request.body.PublicPaymentSettlementVoucherBodyEntryVo;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.si.core.utils.JaxbObjectAndXmlUtils;
import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.nc.request.body.PaymentSettlementVoucherBodyEntryVo;
import com.ronglian.fssc.webapp.external.vo.nc.request.head.PaymentSettlementVoucherHeadVo;
import com.ronglian.fssc.webapp.external.vo.nc.request.head.PublicPaymentSettlementVoucherHeadVo;

public class SimpleTest {
    @Test
    public void testSimple() throws Exception {
        // 请求
        PublicPaymentSettlementVoucherHeadVo voucherHeadVo = new PublicPaymentSettlementVoucherHeadVo();
        voucherHeadVo.setCompanyPk("01502");// 公司PK
        voucherHeadVo.setPrePayFlag("N");// 是否预付标志 字段说明:取值Y或N
        voucherHeadVo.setOrderDate(new Date());// 单据日期
        voucherHeadVo.setCreatedBy("2820");// 制单人
        voucherHeadVo.setCreatedDate(new Date());// 制单日期
        voucherHeadVo.setPayAccount("110060576018150104216");// 付款帐号
        voucherHeadVo.setVendorNum("10030079");// 客商编码
        voucherHeadVo.setVendorAccount("11001069700059666666");// 客商收款帐号
        voucherHeadVo.setPayDept("001");// 付款部门编码
        voucherHeadVo.setMethodPayment("001");// 结算方式编码
        voucherHeadVo.setSameCityFlag("1");// 同城异城标识 字段说明:取值：0/1 0代表异城，1代表同城

        PublicPaymentSettlementVoucherBodyEntryVo voucherBodyEntryVo = new PublicPaymentSettlementVoucherBodyEntryVo();
        voucherBodyEntryVo.setNote1("摘要1");// 凭证摘要 字段类型:字符 字段长度:200
        voucherBodyEntryVo.setNote2("摘要1");// 摘要 字段类型:字符 字段长度:5
        voucherBodyEntryVo.setAmount(new BigDecimal(30.303));// 付款金额 字段类型:小数 字段长度:20 字段说明:小数点后最多保留两位
        voucherBodyEntryVo.setFundSchema("202");// 资金计划项目
        voucherBodyEntryVo.setCurrencyCode("CNY");// 币种编码 固定值CNY

        PublicPaymentSettlementReqVo vo = new PublicPaymentSettlementReqVo();
        vo.setBilltype("D5");
        vo.setFilename("付款结算单demo.xml");
        vo.setPayOrderId("D5201311120009");
        vo.setVoucherHeadVo(voucherHeadVo);
        vo.setVoucherBodyEntryVoList(Lists.newArrayList(voucherBodyEntryVo));

        test(vo);
    }

    private void test(BasePaymentSettlementReqVo<? extends PaymentSettlementVoucherHeadVo, ? extends PaymentSettlementVoucherBodyEntryVo> vo)
            throws JAXBException {
        String xml = JaxbObjectAndXmlUtils.object2Xml(vo, vo.getHeadCls(), vo.getBodyCls());
        System.out.println(xml);
        System.out.println(JSONObject.toJSONString(JaxbObjectAndXmlUtils.xml2Object(xml, vo.getClass(), vo.getHeadCls(), vo.getBodyCls())));

    }
    
    
    @Test
    public void testTT(){
        String a = "sss";
        String b = a;
        a = null;
        System.out.print(b);

    }

    @Test
    public void testParseInt(){
        System.out.print(Integer.parseInt("000101"));
    }

}
