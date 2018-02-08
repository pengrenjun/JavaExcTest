package com.ronglian.fssc.webapp.external.manager.ebs.view;

import java.util.List;

import com.ronglian.fssc.webapp.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description Gl账期
 * @ClassName VFssGlPeriodsManager
 * @Date 2015年11月12日 下午4:00:07
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class VFssGlPeriodsManagerTest extends TestBase {
    @Autowired
    private VFssGlPeriodsManager manager;

    @Test
    public void testGetAllByFilter() {
        List<?> list = manager.findAllByFilter();
        Assert.assertNotNull(list);
    }
}
