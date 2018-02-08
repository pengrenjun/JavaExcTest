package com.ronglian.fssc.webapp.platform.manager.system;

import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.domain.payment.TFormEmpECApp;
import org.aspectj.lang.annotation.AfterThrowing;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProjectName: FSSC Reimbursement
 * Company: 德勤Deloitte
 *
 * @Copyright (c) All rights reserved 2015.
 * @Author: Liu, Tang
 * @Date: 19/11/2015
 * @Time: 14:23
 * @Description: 流程相关服务层逻辑处理测试用例.
 */
public class WorkflowBaseManagerTest extends TestBase {
    @Autowired
    private WorkflowBaseManager workflowBaseManager;

    @AfterThrowing
    @Test
    public void generateAppWorkOrderCode_test() {
        try {
            String workOrderCode = workflowBaseManager.generateAppWorkOrderCode(TFormEmpECApp.class, "SPH", "EC");
            System.out.println("workOrderCode:" + workOrderCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}