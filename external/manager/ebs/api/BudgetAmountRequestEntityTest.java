package com.ronglian.fssc.webapp.external.manager.ebs.api;

import com.ronglian.fssc.webapp.core.enums.BillTypeEnum;
import com.ronglian.fssc.webapp.external.vo.ebs.api.budget.BudgetAmountRequestEntity;

/**
 * ProjectName: FSSC
 * Company: 德勤Deloitte
 *
 * @Copyright (c) All rights reserved 2016.
 * @Author: Liu, Tang
 * @Date: 26/05/2016
 * @Time: 18:14
 * @Description: BudgetAmountRequestEntity实体对象测试用例.
 */
public class BudgetAmountRequestEntityTest {

    public static void main(String[] args) {
        BudgetAmountRequestEntity requestEntity1 = new BudgetAmountRequestEntity();
        requestEntity1.setBillNo("10521201605003788");
        requestEntity1.setBillType(BillTypeEnum.EMPLOYEE_REIMBURSEMENT.getValue());
        requestEntity1.setDepartCode("101050500");
        requestEntity1.setProjectNo("9999999999");
        requestEntity1.setLittleBizCode("110169");
        requestEntity1.setTaskNo(null);

        BudgetAmountRequestEntity requestEntity2 = new BudgetAmountRequestEntity();
        requestEntity2.setBillNo("10521201605003788");
        requestEntity2.setBillType(BillTypeEnum.EMPLOYEE_REIMBURSEMENT.getValue());
        requestEntity2.setDepartCode("101050500");
        requestEntity2.setProjectNo("9999999999");
        requestEntity2.setLittleBizCode("110169");
        requestEntity2.setTaskNo(null);

        System.out.println(requestEntity1 == requestEntity2);
        System.out.println(requestEntity1.equals(requestEntity2));
    }
}