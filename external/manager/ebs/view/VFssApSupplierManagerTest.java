package com.ronglian.fssc.webapp.external.manager.ebs.view;

import com.ronglian.fssc.webapp.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ProjectName: FSSC
 * Company: 德勤Deloitte
 *
 * @Copyright (c) All rights reserved 2015.
 * @Author: Liu, Tang
 * @Date: 14/03/2016
 * @Time: 11:23
 * @Description: 供应商信息单元测试.
 */
public class VFssApSupplierManagerTest extends TestBase {
    @Autowired
    private VFssApSupplierManager supplierManager;

    @Test
    public void testGetAllByFilter() {
        List<?> list = supplierManager.findAllByFilter();
        Assert.assertNotNull(list);
    }
}