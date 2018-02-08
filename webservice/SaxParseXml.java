package com.ronglian.fssc.webapp.webservice;

import com.ronglian.fssc.webapp.platform.vo.pay.PayBillQueryReqParamVo;
import com.ronglian.fssc.webapp.platform.vo.pay.PayBillQueryReturnParamVo;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by ronglian on 2016/7/12.
 */
public class SaxParseXml extends DefaultHandler {

    private PayBillQueryReturnParamVo vo;

    //用来存放每次遍历后的元素名称(节点名称)
    private String tagName;

    public String getTagName() {
        return tagName;
    }


    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public PayBillQueryReturnParamVo getVo() {
        return vo;
    }

    public void setVo(PayBillQueryReturnParamVo vo) {
        this.vo = vo;
    }

    //只调用一次  初始化list集合
    @Override
    public void startDocument() throws SAXException {
    }


    //调用多次    开始解析
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        this.tagName=qName;
        if(qName.equals("row")){
            vo=new PayBillQueryReturnParamVo();
        }
    }


    //调用多次
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        this.tagName=null;
    }


    //只调用一次
    @Override
    public void endDocument() throws SAXException {
    }

    //调用多次
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(this.tagName!=null){
            String data=new String(ch,start,length);
            if(this.tagName.equals("serialid")){
                this.vo.setSerialId(data);
            }
            else if(this.tagName.equals("corpCode")){
                this.vo.setCorpCode(data);
            }
            else if(this.tagName.equals("corpname")){
                this.vo.setCorpname(data);
            }
            else if(this.tagName.equals("cdsign")){
                this.vo.setCdsign(data);
            }
            else if(this.tagName.equals("inamt")){
                this.vo.setCdsign(data);
            }
            else if(this.tagName.equals("transtime")){
                this.vo.setTranstime(data);
            }
            else if(this.tagName.equals("amt")){
                this.vo.setAmt(data);
            }
            else if(this.tagName.equals("outamt")){
                this.vo.setOutamt(data);
            }
            else if(this.tagName.equals("cur")){
                this.vo.setOutamt(data);
            }
            else if(this.tagName.equals("uses")){
                this.vo.setUses(data);
            }
            else if(this.tagName.equals("bankacc")){
                this.vo.setBankacc(data);
            }
            else if(this.tagName.equals("accname")){
                this.vo.setAccname(data);
            }
            else if(this.tagName.equals("pbankname")){
                this.vo.setBankname(data);
            }
            else if(this.tagName.equals("oppaccno")){
                this.vo.setOppaccno(data);
            }
            else if(this.tagName.equals("oppaccname")){
                this.vo.setOppaccname(data);
            }
            else if(this.tagName.equals("oppbankname")){
                this.vo.setOppbankname(data);
            }
            else if(this.tagName.equals("reserve1")){
                this.vo.setReserve1(data);
            }
            else if(this.tagName.equals("reserve2")){
                this.vo.setReserve2(data);
            }
            else if(this.tagName.equals("reserve3")){
                this.vo.setReserve3(data);
            }
        }
    }
}
