package com.ronglian.fssc.webapp.platform.manager.payment.employee;

import com.deloitte.si.core.domain.datasource.FilterFactory;
import com.deloitte.si.core.manager.CommonManager;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.domain.payment.TFormEmpECDetail;
import org.aspectj.lang.annotation.AfterThrowing;
import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ProjectName: FSSC Reimbursement
 * Company: 德勤Deloitte
 *
 * @Copyright (c) All rights reserved 2015.
 * @Author: Liu, Tang
 * @Date: 10/11/2015
 * @Time: 17:06
 * @Description: File Template | Description Write Here.
 */
public class TFormEmpECAppManagerTest extends TestBase {
    @Autowired
    private TFormEmpECAppManager appManager;

    @Autowired
    private CommonManager commonManager;

    @AfterThrowing
    @Test
    public void testQueryECDetail() {
        CommonManager manager =  EasyMock.createMock(CommonManager.class);
        EasyMock.expect(manager.getAll(TFormEmpECDetail.class)).andReturn(null);
        EasyMock.replay();
        EasyMock.verify();
        EasyMock.reset();
        //List<TFormEmpECDetail> detailList = this.appManager.getAll(TFormEmpECDetail.class);
        //System.out.println("---");
    }

    @AfterThrowing
    @Test
    public void testQueryECDetail2() {

        List<TFormEmpECDetail> detailList = this.commonManager.findEntityByFilter(TFormEmpECDetail.class, FilterFactory.eq("ecPkId","sdsadsa"));
        System.out.println("----");
    }

    @AfterThrowing
    @Test
    public void testSaveECDetail() {
        TFormEmpECDetail detail = new TFormEmpECDetail();
        CommonManager commonManager =  EasyMock.createMock(CommonManager.class);
        commonManager.save(detail);
        EasyMock.expectLastCall();
        EasyMock.replay();
        EasyMock.verify();
        EasyMock.reset();
    }

    @AfterThrowing
    @Test
    public void testSaveECDetail2() {
        TFormEmpECDetail detail = new TFormEmpECDetail();
        detail.setEcPkId("100000");
        this.commonManager.save(detail);
        System.out.println("----");
    }
}
