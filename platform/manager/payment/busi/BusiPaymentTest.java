package com.ronglian.fssc.webapp.platform.manager.payment.busi;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.si.core.dao.GenericDao;
import com.deloitte.si.core.domain.datasource.FormSearchCondition;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.domain.payment.TFormBusiPayment;
import com.ronglian.fssc.webapp.platform.utils.ObjectViewer;

public class BusiPaymentTest extends TestBase {

    @Autowired
    private GenericDao dao;

    @Test
    public void findPayment() {
        List<TFormBusiPayment> list = dao.findEntityByFilter(TFormBusiPayment.class, new FormSearchCondition());
        System.out.println(ObjectViewer.obj2String(list));
    }
}
