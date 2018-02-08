package com.ronglian.fssc.webapp.platform.manager.security;

import com.ronglian.fssc.webapp.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.si.framework.manager.security.TSysFunctionMenuRelManager;

public class TSysFunctionMenuRelManagerTest extends TestBase {
    @Autowired
    private TSysFunctionMenuRelManager manager;

    @Test
    public void findMenuRelAuthCountAll() {
        manager.findMenuRelAuthCountAll("1", "1");
    }
}
