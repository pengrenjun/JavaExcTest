package com.ronglian.fssc.webapp.platform.manager.system;

import com.ronglian.fssc.webapp.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description TODO
 * @ClassName TSysNonPrjBgtMtManagerTest
 * @Date 2016年2月2日 上午10:47:42
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2016.
 */
public class TSysNonPrjBgtMtManagerTest extends TestBase {
    @Autowired
    private TSysNonPrjBgtMtManager manager;

    /**
     * @Method validateEBSBudget
     * @Description TODO
     * @throws Exception
     */
    @Test
    public void testValidateEBSBudget() throws Exception {
        System.out.println(manager.validateEBSBudget("yyy0002"));
    }
}
