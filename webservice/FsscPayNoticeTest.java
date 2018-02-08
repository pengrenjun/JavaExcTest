package com.ronglian.fssc.webapp.webservice;

import com.alibaba.fastjson.JSON;
import com.ronglian.fssc.webapp.platform.vo.pay.PayBillQueryReqParamVo;
import com.ronglian.fssc.webapp.platform.vo.pay.PayBillQueryReturnParamVo;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.io.OutputFormat;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FsscPayNoticeTest {
    // 支付通知
    private final static String WSDL_ADDRESS = "http://localhost:8080/ws/soap/FsscPayNoticeWebservice?wsdl";

    // 付款
    private final static String BAUTE_PAY_WSDL_ADDRESS = "http://10.200.7.234:8089/rzljk/services/rzlSscPayBillWebService?wsdl";

    // 收款查询
    private final static String BAUTE_SK_WSDL_ADDRESS = "http://10.200.7.234:8089/rzljk/services/rzlSscSkBillWebService?wsdl";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 支付通知
     */
    @Test
    public void payNoticeXml() {
        Client client = getClient(WSDL_ADDRESS);
        try {
                     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><row><ticketingDate></ticketingDate><isPay></isPay><remark></remark><expiryDate></expiryDate><ticketNum></ticketNum><paree_addr>广东省;深圳市</paree_addr><corpname>深圳爱豌豆电子商务有限公司</corpname><reserve3></reserve3><billingDate></billingDate><taxCode></taxCode><reserve2></reserve2><payer_acc>755926204810701</payer_acc><reserve1></reserve1><serial_id>161109000071</serial_id><resultMsg>[2016-11-09 09:45:24] 记录已提取，等待提交银行|[2016-11-09 09:45:27] 付款指令发送：交易已发送，稍后查询结果。接口返回：(0)发送交易成功。/银行返回：(SUC0000)|[2016-11-09 09:45:52] 查询付款结果指令发送：交易已发送，稍后查询结果。接口返回：(0)发送交易成功。/银行返回：(0)终审完毕|[2016-11-09 09:46:29] 查询付款结果指令发送：交易成功。接口返回：(0)发送交易成功。/银行返回：(0)成功</resultMsg><fls_num>201611090900000654</fls_num><dates>2016-11-09</dates><pay_type>05</pay_type><resultCode>S</resultCode><bank></bank></row><row><ticketingDate></ticketingDate><isPay></isPay><remark></remark><expiryDate></expiryDate><ticketNum></ticketNum><paree_addr>北京市;北京市</paree_addr><corpname>深圳爱豌豆电子商务有限公司</corpname><reserve3></reserve3><billingDate></billingDate><taxCode></taxCode><reserve2></reserve2><payer_acc>755926204810701</payer_acc><reserve1></reserve1><serial_id>161108000072</serial_id><resultMsg>[2016-11-09 09:45:59] 记录已提取，等待提交银行|[2016-11-09 09:46:02] 付款指令发送：交易已发送，稍后查询结果。接口返回：(0)发送交易成功。/银行返回：(SUC0000)|[2016-11-09 09:46:44] 查询付款结果指令发送：交易已发送，稍后查询结果。接口返回：(0)发送交易成功。/银行返回：(0)银行处理中|[2016-11-09 09:47:11] 查询付款结果指令发送：交易成功。接口返回：(0)发送交易成功。/银行返回：(0)成功</resultMsg><fls_num>201611081300000775</fls_num><dates>2016-11-09</dates><pay_type>05</pay_type><resultCode>S</resultCode><bank></bank></row></root>";

                    //                    "<root> " +
                    //                    "<row>" +
                    //                    "<ticketingDate></ticketingDate>" +
                    //                    "<isPay></isPay><remark></remark>" +
                    //                    "<expiryDate></expiryDate>" +
                    //                    "<ticketNum></ticketNum>" +
                    //                    "<paree_addr>北京市;北京市</paree_addr><corpname>北京荣之联科技股份有限公司(本部)</corpname><reserve3></reserve3> " +
                    //                    "<billingDate></billingDate>" +
                    //                    "<taxCode></taxCode>" +
                    //                    "<reserve2></reserve2>" +
                    //                    "<payer_acc>010900291410509</payer_acc>" +
                    //                    "<reserve1></reserve1>" +
                    //                    "<serial_id>161107000001</serial_id>" +
                    //                    "<resultMsg>sss" +
                    //                    "</resultMsg>" +
                    //                    "<fls_num>201611070900000637</fls_num>" +
                    //                    "<dates>2016-11-07</dates><pay_type>05</pay_type>" +
                    //                    "<resultCode>S</resultCode>" +
                    //                    "<bank></bank>" +
                    //                    "</row>" +
                    //                    "</root>";
                    //            String xml =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><row><isPay></isPay><ticketNum></ticketNum><resultCode>0</resultCode><billingDate></billingDate><ticketingDate></ticketingDate><resultMsg>交易成功。接口返回：(0)发送交易成功。/银行返回：(0)4|422397</resultMsg><dates>2016-08-23</dates><remark></remark><reserve1></reserve1><serial_id>201608231455475627450415</serial_id><bank></bank><expiryDate></expiryDate><corpname>北京荣之联科技股份有限公司(本部)</corpname><reserve2></reserve2><pay_type>34</pay_type><reserve3></reserve3></row><row><isPay>现金</isPay><ticketNum></ticketNum><resultCode>测试付款接口</resultCode><billingDate></billingDate><ticketingDate></ticketingDate><resultMsg>测试付款接口</resultMsg><dates>2016-08-21</dates><remark>现金</remark><reserve1></reserve1><serial_id>10211201607001126</serial_id><bank></bank><expiryDate></expiryDate><corpname>荣之联集团</corpname><reserve2></reserve2><pay_type>04</pay_type><reserve3></reserve3></row></root>";
                    //            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    //                    "<root>" +
                    //                    "<row>" +
                    //                    "<payId>4534543543533543</payId>" +
                    //                    "<payStatus>S</payStatus>" +
                    //                    "<payStatusMsg>success</payStatusMsg>" +
                    //                    "<transtime>2016/06/24 17:58:00</transtime>" +
                    //                    "<bfsWaterId>xxxxxxxxxxxxxx1</bfsWaterId>" +
                    //                    "<reserve1></reserve1>" +
                    //                    "<reserve2></reserve2>" +
                    //                    "<reserve3></reserve3>" +
                    //                    "</row>" +
                    //                    "<row>" +
                    //                    "<payId>5t35t45t45t45t54</payId>" +
                    //                    "<payStatus>F</payStatus>" +
                    //                    "<payStatusMsg>收款账户不正确</payStatusMsg>" +
                    //                    "<transtime>2016/06/24 17:58:00</transtime>" +
                    //                    "<bfsWaterId>xxxxxxxxxxxxxx2</bfsWaterId>" +
                    //                    "<reserve1></reserve1>" +
                    //                    "<reserve2></reserve2>" +
                    //                    "<reserve3></reserve3></row>" +
                    //                    "</root>";
                    Object[]objs = client.invoke("payNoticeXml", xml, "预留字段");
            System.out.println(objs[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 付款
     */
    @Test
    public void pay() {
        //System.out.println(getPayReqParamXml());
        Client client = getClient(BAUTE_PAY_WSDL_ADDRESS);
        try {
            System.out.println(getPayReqParamXml());
            Object[] objs = client.invoke("payBillSysService", getPayReqParamXml(), "预留字段");
            System.out.println(objs[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetPayReqParamXml() {
        System.out.println(getPayReqParamXml());
    }


    /**
     * 得到XML格式付款请求参数
     *
     * @return
     */
    private String getPayReqParamXml() {
        //用来得到XML字符串形式
        String xmlStr = null;
        try {
            // 一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串
            StringWriter writerStr = new StringWriter();
            OutputFormat format = OutputFormat.createPrettyPrint();

            Result resultStr = new StreamResult(writerStr);
            //获取sax生产工厂对象实例
            SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            //获取sax生产处理者对象实例
            TransformerHandler transformerHandle = saxTransformerFactory.newTransformerHandler();
            transformerHandle.setResult(resultStr);
            //获取sax生产器
            Transformer transformer = transformerHandle.getTransformer();
            //transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");//xml的编码格式
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");//换行
            //开始封装document文档对象，这里和解析一样都是成双成对的构造标签
            transformerHandle.startDocument();
            AttributesImpl attrImple = new AttributesImpl();
            transformerHandle.startElement("", "", "root", attrImple);

            transformerHandle.startElement("", "", "row", null);

            transformerHandle.startElement("", "", "fls_num", null);
            transformerHandle.characters("10131201607001139".toCharArray(), 0, "10131201607001139".length());
            transformerHandle.endElement("", "", "fls_num");
            transformerHandle.startElement("", "", "payer_name", null);
            transformerHandle.characters("北京荣之联科技股份有限公司武汉分公司".toCharArray(), 0, "北京荣之联科技股份有限公司武汉分公司".length());
            transformerHandle.endElement("", "", "payer_name");
            transformerHandle.startElement("", "", "payer_acc", null);
            transformerHandle.characters("11050166530000000125".toCharArray(), 0, "11050166530000000125".length());
            transformerHandle.endElement("", "", "payer_acc");

            transformerHandle.startElement("", "", "payee_name", null);
            transformerHandle.characters("请问前往慰问".toCharArray(), 0, "请问前往慰问".length());
            transformerHandle.endElement("", "", "payee_name");
            transformerHandle.startElement("", "", "payee_acc", null);
            transformerHandle.characters("11050166530000000125".toCharArray(), 0, "11050166530000000125".length());
            transformerHandle.endElement("", "", "payee_acc");
            transformerHandle.startElement("", "", "pay_type", null);
            transformerHandle.characters("34".toCharArray(), 0, "34".length());
            transformerHandle.endElement("", "", "pay_type");

            transformerHandle.startElement("", "", "isforindividual", null);
            transformerHandle.characters("0".toCharArray(), 0, "0".length());
            transformerHandle.endElement("", "", "isforindividual");
            transformerHandle.startElement("", "", "add_type", null);
            transformerHandle.characters("1".toCharArray(), 0, "1".length());
            transformerHandle.endElement("", "", "add_type");
            transformerHandle.startElement("", "", "dif_bank", null);
            transformerHandle.characters("".toCharArray(), 0, "".length());
            transformerHandle.endElement("", "", "dif_bank");

            transformerHandle.startElement("", "", "wish_payday", null);
            transformerHandle.characters("2016-08-20".toCharArray(), 0, "2016-08-20".length());
            transformerHandle.endElement("", "", "wish_payday");
            transformerHandle.startElement("", "", "pay_money", null);
            transformerHandle.characters("10000.01".toCharArray(), 0, "10000.01".length());
            transformerHandle.endElement("", "", "pay_money");
            transformerHandle.startElement("", "", "purpose", null);
            transformerHandle.characters("测试付款接口".toCharArray(), 0, "测试付款接口".length());
            transformerHandle.endElement("", "", "purpose");

            transformerHandle.startElement("", "", "is_quick", null);
            transformerHandle.characters("1".toCharArray(), 0, "1".length());
            transformerHandle.endElement("", "", "is_quick");
            transformerHandle.startElement("", "", "reserve1", null);
            transformerHandle.characters("".toCharArray(), 0, "".length());
            transformerHandle.endElement("", "", "reserve1");
            transformerHandle.startElement("", "", "reserve2", null);
            transformerHandle.characters("".toCharArray(), 0, "".length());
            transformerHandle.endElement("", "", "reserve2");
            transformerHandle.startElement("", "", "reserve3", null);
            transformerHandle.characters("".toCharArray(), 0, "".length());
            transformerHandle.endElement("", "", "reserve3");


            transformerHandle.startElement("", "", "fundItem", null);
            transformerHandle.characters("测绘".toCharArray(), 0, "测试".length());
            transformerHandle.endElement("", "", "fundItem");
            transformerHandle.endElement("", "", "row");

//            transformerHandle.startElement("", "", "row", null);
//
//            transformerHandle.startElement("", "", "fls_num", null);
//            transformerHandle.characters("10131201607001134".toCharArray(), 0, "10131201607001134".length());
//            transformerHandle.endElement("", "", "fls_num");
//            transformerHandle.startElement("", "", "payer_name", null);
//            transformerHandle.characters("荣之联有限公司".toCharArray(), 0, "荣之联有限公司".length());
//            transformerHandle.endElement("", "", "payer_name");
//            transformerHandle.startElement("", "", "payerAcc", null);
//            transformerHandle.characters("11050166530000000125".toCharArray(), 0, "11050166530000000125".length());
//            transformerHandle.endElement("", "", "payerAcc");
//
//            transformerHandle.startElement("", "", "payee_name", null);
//            transformerHandle.characters("拜特服务有限公司".toCharArray(), 0, "拜特服务有限公司".length());
//            transformerHandle.endElement("", "", "payee_name");
//            transformerHandle.startElement("", "", "payee_acc", null);
//            transformerHandle.characters("11050166530000000125".toCharArray(), 0, "11050166530000000125".length());
//            transformerHandle.endElement("", "", "payee_acc");
//            transformerHandle.startElement("", "", "pay_type", null);
//            transformerHandle.characters("34".toCharArray(), 0, "34".length());
//            transformerHandle.endElement("", "", "pay_type");
//
//            transformerHandle.startElement("", "", "isforindividual", null);
//            transformerHandle.characters("0".toCharArray(), 0, "0".length());
//            transformerHandle.endElement("", "", "isforindividual");
//            transformerHandle.startElement("", "", "add_type", null);
//            transformerHandle.characters("1".toCharArray(), 0, "1".length());
//            transformerHandle.endElement("", "", "add_type");
//            transformerHandle.startElement("", "", "dif_bank", null);
//            transformerHandle.characters("1".toCharArray(), 0, "1".length());
//            transformerHandle.endElement("", "", "dif_bank");
//
//            transformerHandle.startElement("", "", "wish_payday", null);
//            transformerHandle.characters("2016-08-20".toCharArray(), 0, "2016-08-20".length());
//            transformerHandle.endElement("", "", "wish_payday");
//            transformerHandle.startElement("", "", "pay_money", null);
//            transformerHandle.characters("10000.01".toCharArray(), 0, "10000.01".length());
//            transformerHandle.endElement("", "", "pay_money");
//            transformerHandle.startElement("", "", "purpose", null);
//            transformerHandle.characters("测试付款接口".toCharArray(), 0, "测试付款接口".length());
//            transformerHandle.endElement("", "", "purpose");
//
//            transformerHandle.startElement("", "", "is_quick", null);
//            transformerHandle.characters("1".toCharArray(), 0, "1".length());
//            transformerHandle.endElement("", "", "is_quick");
//            transformerHandle.startElement("", "", "reserve1", null);
//            transformerHandle.characters("".toCharArray(), 0, "".length());
//            transformerHandle.endElement("", "", "reserve1");
//            transformerHandle.startElement("", "", "reserve2", null);
//            transformerHandle.characters("".toCharArray(), 0, "".length());
//            transformerHandle.endElement("", "", "reserve2");
//            transformerHandle.startElement("", "", "reserve3", null);
//            transformerHandle.characters("".toCharArray(), 0, "".length());
//            transformerHandle.endElement("", "", "reserve3");
//            transformerHandle.startElement("", "", "fundItem", null);
//            transformerHandle.characters("测绘".toCharArray(), 0, "测试".length());
//            transformerHandle.endElement("", "", "fundItem");
//            transformerHandle.endElement("", "", "row");

            transformerHandle.endElement("", "", "root");
            //因为没有appendChild等等添加子元素的方法，sax提供的是构造在startElement()和endElement()区间内的标签为包含的节点的父节点
            transformerHandle.endDocument();
            xmlStr = writerStr.getBuffer().toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return xmlStr;
    }


    /**
     * 得到收款查询请求参数
     *
     * @param vo
     * @return
     */
    private String getSkReqParamXml(PayBillQueryReqParamVo vo) {
        //用来得到XML字符串形式
        String xmlStr = null;
        try {
            // 一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串
            StringWriter writerStr = new StringWriter();
            Result resultStr = new StreamResult(writerStr);
            //获取sax生产工厂对象实例
            SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            //获取sax生产处理者对象实例
            TransformerHandler transformerHandle = saxTransformerFactory.newTransformerHandler();
            transformerHandle.setResult(resultStr);
            //获取sax生产器
            Transformer transformer = transformerHandle.getTransformer();
            //transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");//xml的编码格式
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");//换行
            //开始封装document文档对象，这里和解析一样都是成双成对的构造标签
            transformerHandle.startDocument();
            AttributesImpl attrImple = new AttributesImpl();
            transformerHandle.startElement("", "", "root", attrImple);

            transformerHandle.startElement("", "", "row", null);

            transformerHandle.startElement("", "", "corpName", null);
            transformerHandle.characters(vo.getCorpName().toCharArray(), 0, vo.getCorpName().length());
            transformerHandle.endElement("", "", "corpName");
            transformerHandle.startElement("", "", "bankAcc", null);
            transformerHandle.characters(vo.getBankAcc().toString().toCharArray(), 0, vo.getBankAcc().toString().length());
            transformerHandle.endElement("", "", "bankAcc");
            transformerHandle.startElement("", "", "stratDate", null);
            transformerHandle.characters(dateFormat.format(vo.getStratDate()).toString().toCharArray(), 0, dateFormat.format(vo.getStratDate()).toString().length());
            transformerHandle.endElement("", "", "stratDate");
            transformerHandle.startElement("", "", "endDate", null);
            transformerHandle.characters(dateFormat.format(vo.getEndDate()).toString().toCharArray(), 0, dateFormat.format(vo.getEndDate()).toString().length());
            transformerHandle.endElement("", "", "endDate");
            transformerHandle.startElement("", "", "reserve1", null);
            transformerHandle.characters(vo.getReserve1().toCharArray(), 0, vo.getReserve1().length());
            transformerHandle.endElement("", "", "reserve1");
            transformerHandle.startElement("", "", "reserve2", null);
            transformerHandle.characters(vo.getReserve2().toCharArray(), 0, vo.getReserve2().length());
            transformerHandle.endElement("", "", "reserve2");
            transformerHandle.startElement("", "", "reserve3", null);
            transformerHandle.characters(vo.getReserve3().toCharArray(), 0, vo.getReserve3().length());
            transformerHandle.endElement("", "", "reserve3");

            transformerHandle.endElement("", "", "row");
            transformerHandle.endElement("", "", "root");
            //因为没有appendChild等等添加子元素的方法，sax提供的是构造在startElement()和endElement()区间内的标签为包含的节点的父节点
            transformerHandle.endDocument();
            xmlStr = writerStr.getBuffer().toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return xmlStr;
    }


    /**
     * 收款查询
     */
    @Test
    public void skPay() {
        Client client = getClient(BAUTE_SK_WSDL_ADDRESS);
        try {
            PayBillQueryReqParamVo reqVo = new PayBillQueryReqParamVo("1222", "bbca", "", "");
            System.out.println(getSkReqParamXml(reqVo));
            Object[] objs = client.invoke("payBillSyncService", getSkReqParamXml(reqVo), "预留字段");
            PayBillQueryReturnParamVo vo = parserReturnParam((String) objs[0]);
            System.out.println(objs[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 解析收款查询返回结果
     *
     * @param xml
     * @return
     */
    public static PayBillQueryReturnParamVo parserReturnParam(String xml) {
        SAXParser parser = null;
        PayBillQueryReturnParamVo vo = null;
        try {
            //构建SAXParser
            parser = SAXParserFactory.newInstance().newSAXParser();
            //实例化  DefaultHandler对象
            SaxParseXml parseXml = new SaxParseXml();
            //加载资源文件 转化为一个输入流
            ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(xml.getBytes());
            //调用parse()方法
            parser.parse(tInputStringStream, parseXml);
            //遍历结果
            vo = parseXml.getVo();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSON(vo).toString());
        return vo;
    }


    private static Client getClient(String wsdlAdrr) {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(wsdlAdrr);

        return client;
    }


}
