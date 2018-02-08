package com.ronglian.fssc.webapp.external.manager.ebs.view;

import com.ronglian.fssc.webapp.TestBase;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VFssPerPeopleManagerTest extends TestBase {
    @Autowired
    private VFssPerPeopleManager manager;

    @Test
    public void synchronousData() throws IllegalAccessException, InvocationTargetException {
        manager.synchronousData();
    }
}
