/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.ronglian.fssc.webapp;

import javax.persistence.MappedSuperclass;

import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.StopWatch;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.deloitte.si.core.manager.CommonManager;

/**
 * @Description 測試用例父類
 * @ClassName TestBase
 * @Date 2015年10月15日 下午7:06:15
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
@Ignore
@ActiveProfiles({ "test" })
@ContextConfiguration(locations = { 
        "classpath:conf/spring-context.xml"})
@Rollback(value = true)
@MappedSuperclass
public abstract class TestBase extends SpringTransactionalTestCase {
    public Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected CommonManager commonManager;

    public TestBase() {
    }

    protected StopWatch startNewWatch(String clzName) {
        StopWatch sw = new StopWatch(clzName);
        sw.start("Add a Test method information.");
        this.logger.info("Enter Method");
        return sw;
    }

    protected void stopWatch(StopWatch sw) {
        sw.stop();
        this.logger.info(sw.prettyPrint());
        this.logger.info("Finished Method");
    }
}