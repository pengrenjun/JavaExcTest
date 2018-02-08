package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.List;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ar_invoice_import.ObjFssArInvoiceRecVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ar_invoice_import.ObjFssArInvoiceTblVo;

/**
 * @Description 应收发票接口
 * @ClassName ArInvoiceImportManagerTest
 * @Date 2015年12月29日 下午4:54:49
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ArInvoiceImportManagerTest extends ISimpleEbsApiTest<ObjFssArInvoiceTblVo> {

    @Override
    protected ObjFssArInvoiceTblVo getApiRequestEntity() {
        ObjFssArInvoiceTblVo tblVo = new ObjFssArInvoiceTblVo();
        List<ObjFssArInvoiceRecVo> list = Lists.newArrayList();
        ObjFssArInvoiceRecVo vo = null;
        for (int i = 0; i < 10; i++) {
            vo = new ObjFssArInvoiceRecVo();
            list.add(vo);
        }
        tblVo.setList(list);
        return tblVo;
    }

}
