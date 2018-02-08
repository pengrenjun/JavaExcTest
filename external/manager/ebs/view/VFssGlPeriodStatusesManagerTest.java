package com.ronglian.fssc.webapp.external.manager.ebs.view;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronglian.fssc.webapp.TestBase;

public class VFssGlPeriodStatusesManagerTest extends TestBase {
    @Autowired
    private VFssGlPeriodStatusesManager manager;

    @Test
    public void testGetAllByFilter() {
        List<?> list = manager.findAllByFilter();
        Assert.assertNotNull(list);
    }
}
