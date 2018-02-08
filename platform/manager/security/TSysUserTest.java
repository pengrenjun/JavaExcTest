package com.ronglian.fssc.webapp.platform.manager.security;

import org.easymock.EasyMock;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.deloitte.si.framework.manager.security.TSysUserManager;

public class TSysUserTest {
    private static TSysUserManager userManager;

    @BeforeClass
    public static void before() {
        userManager = EasyMock.createMock(TSysUserManager.class);
    }

    @AfterClass
    public static void after() {
        userManager = null;
    }

    @Test
    public void testGetAll() {
        EasyMock.expect(userManager.getAll()).andReturn(null);
        EasyMock.replay(userManager);
        EasyMock.verify();
        EasyMock.reset();
    }

    @Test
    public void testFindUserByNoRoleId() throws Exception {
        EasyMock.expect(userManager.findUserByNoRoleId(1l)).andReturn(null);
        EasyMock.replay(userManager);
        EasyMock.verify();
        EasyMock.reset();
    }
}
