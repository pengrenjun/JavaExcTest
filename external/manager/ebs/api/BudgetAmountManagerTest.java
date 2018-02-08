package com.ronglian.fssc.webapp.external.manager.ebs.api;

import com.alibaba.fastjson.JSONObject;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.core.enums.BillTypeEnum;
import com.ronglian.fssc.webapp.external.vo.ebs.api.budget.BudgetAmountRequestEntity;
import com.ronglian.fssc.webapp.external.vo.ebs.api.budget.BudgetAmountResponseEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProjectName: FSSC
 * Company: 德勤Deloitte
 *
 * @Copyright (c) All rights reserved 2016.
 * @Author: Liu, Tang
 * @Date: 26/05/2016
 * @Time: 16:57
 * @Description: 预算可用金额业务处理层测试用例.
 */
public class BudgetAmountManagerTest extends TestBase {

    @Autowired
    private BudgetAmountManager amountManager;

    @Test
    public void testFindBudgetAmount() {
        try {
            BudgetAmountRequestEntity requestEntity = new BudgetAmountRequestEntity();
            requestEntity.setBillNo("10521201605003788");
            requestEntity.setBillType(BillTypeEnum.EMPLOYEE_REIMBURSEMENT.getValue());
            requestEntity.setLittleBizCode("110169");
            requestEntity.setDepartCode("101050500");
            requestEntity.setProjectNo("9999999999");
            requestEntity.setTaskNo(null);

            long start = System.currentTimeMillis();
            BudgetAmountResponseEntity responseEntity = this.amountManager.findBudgetAmount(requestEntity);
            long end = System.currentTimeMillis();
            System.out.println("Time:" + (end - start));
            System.out.println(responseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}