package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.List;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_invoice_import.ObjFssApInvLineVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_invoice_import.ObjFssApInvoiceTblVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_invoice_import.ObjFssApInvoiceVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_invoice_import.ObjFssApInvHeaderVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ap_invoice_import.ObjFssApPrepayVo;

/**
 * @Description 应付发票
 * @ClassName ApInvoiceImportManagerTest
 * @Date 2015年12月29日 上午11:53:25
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ApInvoiceImportManagerTest extends ISimpleEbsApiTest<ObjFssApInvoiceTblVo> {

    @Override
    protected ObjFssApInvoiceTblVo getApiRequestEntity() {
        ObjFssApInvoiceTblVo invoiceTbl = new ObjFssApInvoiceTblVo();
        List<ObjFssApInvoiceVo> invoiceList = Lists.newArrayList();
        ObjFssApInvoiceVo invoice = null;
        ObjFssApInvHeaderVo invHeader = null;
        List<ObjFssApInvLineVo> invlineList = null;
        ObjFssApInvLineVo invLineVo = null;
        List<ObjFssApPrepayVo> prepayList = null;
        ObjFssApPrepayVo prepayVo = null;
        for (int i = 0; i < 10; i++) {
            invoice = new ObjFssApInvoiceVo();
            //
            invHeader = new ObjFssApInvHeaderVo();
            invoice.setInvHeader(invHeader);
            //
            invlineList = Lists.newArrayList();
            for (int j = 0; j < 10; j++) {
                invLineVo = new ObjFssApInvLineVo();
                invlineList.add(invLineVo);
            }
            invoice.setInvlineList(invlineList);
            // 非必填
            prepayList = Lists.newArrayList();
            for (int j = 0; j < 10; j++) {
                prepayVo = new ObjFssApPrepayVo();
                prepayList.add(prepayVo);
            }
            invoice.setPrepayList(prepayList);
            //
            invoiceList.add(invoice);
        }
        invoiceTbl.setList(invoiceList);
        return invoiceTbl;
    }

}
