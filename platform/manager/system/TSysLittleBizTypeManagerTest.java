package com.ronglian.fssc.webapp.platform.manager.system;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.si.core.domain.datasource.FilterFactory;
import com.ronglian.fssc.webapp.TestBase;

public class TSysLittleBizTypeManagerTest extends TestBase {
    @Autowired
    private TSysLittleBizTypeManager manager;
    @Test
    public void test(){
        manager.findUniqueEntityByFilter(FilterFactory.eq("littleBizCode", "test2"));
    }
    
}
