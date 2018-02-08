package com.ronglian.fssc.webapp.platform.manager.security;

import com.ronglian.fssc.webapp.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.si.framework.manager.security.TSysRoleManager;

public class TSysRoleManagerTest extends TestBase {
    @Autowired
    private TSysRoleManager manager;

    @Test
    public void getAllTest() {
        manager.getAll();
    }
}
