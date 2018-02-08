package com.ronglian.fssc.webapp.external.manager.ebs.view;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronglian.fssc.webapp.TestBase;

/**
 * @Description TODO
 * @ClassName VFssHrDepartmentManagerTest
 * @Date 2015年12月8日 下午2:02:20
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class VFssHrDepartmentManagerTest extends TestBase {
    @Autowired
    private VFssHrDepartmentManager manager;

    @Test
    public void synchronousData() throws IllegalAccessException, InvocationTargetException {
        manager.synchronousData();
    }
}
