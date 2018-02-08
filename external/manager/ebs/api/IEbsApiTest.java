package com.ronglian.fssc.webapp.external.manager.ebs.api;

import javax.persistence.MappedSuperclass;

import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.external.EbsApiException;
import com.ronglian.fssc.webapp.external.manager.ebs.EbsApiManager;
import com.ronglian.fssc.webapp.external.vo.ebs.api.ApiResponseEntity;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ronglian.fssc.webapp.external.vo.ebs.api.ApiRequestEntity;

/**
 * @Description 接口测试父类
 * @ClassName ApiRequestTest
 * @Date 2015年12月10日 下午7:30:34
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
@MappedSuperclass
public abstract class IEbsApiTest<REQ extends ApiRequestEntity, RES extends ApiResponseEntity> extends TestBase {
    @Autowired
    protected EbsApiManager<REQ, RES> manager;

    protected abstract REQ getApiRequestEntity();

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    protected REQ req;

    @Before
    public void before() {
        // 同步调用,将异步调用的代码提取出来即可
        req = getApiRequestEntity();
    }

    @Test
    public abstract void testSyncExecute() throws EbsApiException;

    /**
     * @Method testAsynExecute
     * @Description 异步调用
     * @throws EbsApiException
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testAsynExecute() throws EbsApiException {
        // manager.asynExecute(t);
        // manager.asynExecute(t, "80481B0E6E744E609B4B14DD49DA8903", user);
    }

    @AfterClass
    public static void after() throws InterruptedException {
        // Thread.sleep(3000);
    }

}
