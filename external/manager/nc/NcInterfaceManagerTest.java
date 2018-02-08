package com.ronglian.fssc.webapp.external.manager.nc;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.JAXBException;

import com.ronglian.fssc.webapp.external.vo.nc.request.PublicPaymentSettlementReqVo;
import com.ronglian.fssc.webapp.external.vo.nc.request.head.PrivatePaymentSettlementVoucherHeadVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.si.core.utils.JaxbObjectAndXmlUtils;
import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.external.vo.nc.request.PrivatePaymentSettlementReqVo;
import com.ronglian.fssc.webapp.external.vo.nc.request.body.PrivatePaymentSettlementVoucherBodyEntryVo;
import com.ronglian.fssc.webapp.external.vo.nc.request.body.PublicPaymentSettlementVoucherBodyEntryVo;
import com.ronglian.fssc.webapp.external.vo.nc.request.head.PublicPaymentSettlementVoucherHeadVo;

public class NcInterfaceManagerTest extends TestBase {
    @Autowired
    private NcInterfaceManager ncInterfaceManager;

    @Test
    public void testPublicPaymentSettlement() throws JAXBException, Exception {
        // 请求
        PublicPaymentSettlementVoucherHeadVo voucherHeadVo = new PublicPaymentSettlementVoucherHeadVo();
        voucherHeadVo.setCompanyPk("01502");// 公司PK
        voucherHeadVo.setPrePayFlag("N");// 是否预付标志 字段说明:取值Y或N
        voucherHeadVo.setOrderDate(new Date());// 单据日期
        voucherHeadVo.setCreatedBy("2820");// 制单人
        voucherHeadVo.setCreatedDate(new Date());// 制单日期
        voucherHeadVo.setPayAccount("110060576018150104216");// 付款帐号
        voucherHeadVo.setPayDept("001");// 付款部门编码
        voucherHeadVo.setMethodPayment("001");// 结算方式编码
        voucherHeadVo.setSameCityFlag("1");// 同城异城标识 字段说明:取值：0/1 0代表异城，1代表同城

        voucherHeadVo.setVendorNum("10030079");// 客商编码
        voucherHeadVo.setVendorAccount("11001069700059666666");// 客商收款帐号

        PublicPaymentSettlementVoucherBodyEntryVo voucherBodyEntryVo = new PublicPaymentSettlementVoucherBodyEntryVo();
        voucherBodyEntryVo.setNote1("摘要1");// 凭证摘要 字段类型:字符 字段长度:200
        voucherBodyEntryVo.setNote2("摘要1");// 摘要 字段类型:字符 字段长度:5
        voucherBodyEntryVo.setAmount(new BigDecimal(30.303));// 付款金额 字段类型:小数 字段长度:20 字段说明:小数点后最多保留两位
        voucherBodyEntryVo.setFundSchema("202");// 资金计划项目
        voucherBodyEntryVo.setCurrencyCode("CNY");// 币种编码 固定值CNY

        // JaxbObjectAndXmlUtils
        PublicPaymentSettlementReqVo vo = new PublicPaymentSettlementReqVo();
        vo.setBilltype("D5");
        vo.setFilename("付款结算单demo.xml");
        vo.setPayOrderId("D5201311120009");// 付款结算单ID,付款指令相关ID，唯一标识，传递返回失败后更新；
        vo.setVoucherHeadVo(voucherHeadVo);
        vo.setVoucherBodyEntryVoList(Lists.newArrayList(voucherBodyEntryVo));
        String xml = JaxbObjectAndXmlUtils.object2Xml(vo);

        System.out.println(xml);
        JaxbObjectAndXmlUtils.xml2Object(xml, PublicPaymentSettlementReqVo.class);

        // 响应
        // PaymentSettlementResBodyVo resBodyVo = new PaymentSettlementResBodyVo();
        // resBodyVo.setReturnCode("Y");// 成功标识,成功:Y 失败:N；
        // resBodyVo.setReturnIdxId("jk20151203145013838");// 标识ID,付款结算单ID
        // resBodyVo.setNcFormNo("FJ15120300010");// NC单据号,回传付款指令对应的NC单据号，接到回传信息后，将相关信息存储至相关字段；
        // resBodyVo.setErrorInfo("发送成功，付款结算单生成成功！");// 失败原因,如果失败返回相关原因
        // PaymentSettlementResVo resVo = new PaymentSettlementResVo();
        // resVo.setResBodyVo(resBodyVo);
        // resVo.setBilltype("D5");
        // xml = JaxbObjectAndXmlUtils.object2Xml(resVo);
        // System.out.println(xml);
        // JaxbObjectAndXmlUtils.xml2Object(xml, PaymentSettlementResVo.class);

        //PaymentSettlementResVo resVo = ncInterfaceManager.paymentSettlement(vo);
    }

    @Test
    public void testPrivatePaymentSettlement() throws JAXBException, Exception {
        // 请求
        PrivatePaymentSettlementVoucherHeadVo voucherHeadVo = new PrivatePaymentSettlementVoucherHeadVo();
        voucherHeadVo.setCompanyPk("01502");// 公司PK
        voucherHeadVo.setPrePayFlag("N");// 是否预付标志 字段说明:取值Y或N
        voucherHeadVo.setOrderDate(new Date());// 单据日期
        voucherHeadVo.setCreatedBy("2820");// 制单人
        voucherHeadVo.setCreatedDate(new Date());// 制单日期
        voucherHeadVo.setPayAccount("110060576018150104216");// 付款帐号
        voucherHeadVo.setPayDept("001");// 付款部门编码
        voucherHeadVo.setMethodPayment("001");// 结算方式编码
        voucherHeadVo.setSameCityFlag("1");// 同城异城标识 字段说明:取值：0/1 0代表异城，1代表同城

        voucherHeadVo.setTransObjType("2");// 交易对象类型,固定值：2 2代表人员
        voucherHeadVo.setEmpCode("2820");// 员工编码,集团员工账户
        voucherHeadVo.setCollectAccount("11001069700059666666");// 员工收款帐号
        voucherHeadVo.setBehalfType("按照银行提供的代发代扣类型编码");// 代发代扣类型,按照银行提供的代发代扣类型编码

        PrivatePaymentSettlementVoucherBodyEntryVo voucherBodyEntryVo = new PrivatePaymentSettlementVoucherBodyEntryVo();
        voucherBodyEntryVo.setNote1("摘要1");// 凭证摘要 字段类型:字符 字段长度:200
        voucherBodyEntryVo.setNote2("摘要1");// 摘要 字段类型:字符 字段长度:5
        voucherBodyEntryVo.setAmount(new BigDecimal(30.303));// 付款金额 字段类型:小数 字段长度:20 字段说明:小数点后最多保留两位
        voucherBodyEntryVo.setFundSchema("202");// 资金计划项目
        voucherBodyEntryVo.setCurrencyCode("CNY");// 币种编码 固定值CNY

        PrivatePaymentSettlementReqVo vo = new PrivatePaymentSettlementReqVo();
        vo.setBilltype("D5");
        vo.setFilename("付款结算单demo.xml");
        vo.setPayOrderId("D5201311120009");// 付款结算单ID,付款指令相关ID，唯一标识，传递返回失败后更新；
        vo.setVoucherHeadVo(voucherHeadVo);
        vo.setVoucherBodyEntryVoList(Lists.newArrayList(voucherBodyEntryVo));

        String xml = JaxbObjectAndXmlUtils.object2Xml(vo);

        System.out.println(xml);
        JaxbObjectAndXmlUtils.xml2Object(xml, PublicPaymentSettlementReqVo.class);

        // 响应
        // PaymentSettlementResBodyVo resBodyVo = new PaymentSettlementResBodyVo();
        // resBodyVo.setReturnCode("Y");// 成功标识,成功:Y 失败:N；
        // resBodyVo.setReturnIdxId("jk20151203145013838");// 标识ID,付款结算单ID
        // resBodyVo.setNcFormNo("FJ15120300010");// NC单据号,回传付款指令对应的NC单据号，接到回传信息后，将相关信息存储至相关字段；
        // resBodyVo.setErrorInfo("发送成功，付款结算单生成成功！");// 失败原因,如果失败返回相关原因
        // PaymentSettlementResVo resVo = new PaymentSettlementResVo();
        // resVo.setResBodyVo(resBodyVo);
        // resVo.setBilltype("D5");
        // xml = JaxbObjectAndXmlUtils.object2Xml(resVo);
        // System.out.println(xml);
        // JaxbObjectAndXmlUtils.xml2Object(xml, PaymentSettlementResVo.class);
        //PaymentSettlementResVo resVo = ncInterfaceManager.paymentSettlement(vo);
    }
}
