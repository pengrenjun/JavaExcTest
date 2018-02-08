package com.ronglian.fssc.webapp;

import com.deloitte.si.core.domain.datasource.DbOrder;
import com.deloitte.si.core.domain.datasource.FilterFactory;
import com.deloitte.si.core.domain.datasource.FormSearchCondition;
import com.deloitte.si.framework.domain.task.TFormMain;
import com.deloitte.si.framework.domain.task.TFormStep;
import com.deloitte.si.framework.manager.task.TFormMainManager;
import com.deloitte.si.framework.manager.task.TFormStepManager;
import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.platform.domain.payment.*;
import com.ronglian.fssc.webapp.platform.domain.workflow.TFormAuditInst;
import com.ronglian.fssc.webapp.platform.manager.workflow.TFormAuditInstManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @Description TODO 一句话简要描述 <br/>
 * @ClassName <br/><br/>
 * @Author柳小龙 <br/>
 * @Date 16/8/24 03:11 <br/>
 * @Mail xlliu2@163.com <br/>
 * @Copyright (c) All Rights Reserved, 2016~2018.
 */
@Rollback(value = false)
public class RecoverMainFlowData extends TestBase {
    @Autowired
    private TFormMainManager formMainManager;
    @Autowired
    private TFormStepManager formStepManager;
    @Autowired
    private TFormAuditInstManager formAuditInstManager;

    @Test
    public void recoverMainFlowData_T_FORM_EMP_APP() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_EMP_EC_APP"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("BusinessAuditActivity");
                        List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                        if (!CollectionUtils.isEmpty(ais)) {
                            formMain.setCurrentActivityName(ais.get(0).getActivityName());
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("BusinessAuditActivity")) {
                        TFormEmpECApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpECApp.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报销申请");
                        } else {
                            List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                            if (!CollectionUtils.isEmpty(ais)) {
                                formMain.setCurrentActivityId("BusinessAuditActivity");
                                formMain.setCurrentActivityName(ais.get(0).getActivityName());
                            } else {
                                formMain.setCurrentActivityId("CheckInfoActivity");
                                formMain.setCurrentActivityName("收单审核人审核");
                            }
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckInfoActivity")) {
                        TFormEmpECApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpECApp.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报销申请");
                        } else {
                            formMain.setCurrentActivityId("CheckFinanceActivity");
                            formMain.setCurrentActivityName("费用核算岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckFinanceActivity")) {
                        TFormEmpECApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpECApp.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报销申请");
                        } else {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckFinanceActivity")) {
                        TFormEmpECApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpECApp.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报销申请");
                        }
                    }
                    commonManager.update(formMain);
                }
            }
        }
    }


    @Test
    public void recoverMainFlowData_T_FORM_EMP_REPAYMENT_APP() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_EMP_REPAYMENT_APP"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("CashierAuditActivity");
                        formMain.setCurrentActivityName("出纳审核");
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CashierAuditActivity")) {
                        TFormEmpRepaymentApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpRepaymentApp.class, FilterFactory.eq("repaymentFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("还款申请");
                        } else {
                            formMain.setCurrentActivityId("CheckFinanceActivity");
                            formMain.setCurrentActivityName("费用核算岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckFinanceActivity")) {
                        TFormEmpRepaymentApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpRepaymentApp.class, FilterFactory.eq("repaymentFormNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("还款申请");
                        } else {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckFinanceActivity")) {
                        TFormEmpRepaymentApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpRepaymentApp.class, FilterFactory.eq("repaymentFormNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("还款申请");
                        }
                    }
                    commonManager.update(formMain);
                }
            }
        }
    }


    @Test
    public void recoverMainFlowData_T_FORM_EMP_LOAN_UNOFF() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_EMP_LOAN_APP"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("BusinessAuditActivity");
                        List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                        if (!CollectionUtils.isEmpty(ais)) {
                            formMain.setCurrentActivityName(ais.get(0).getActivityName());
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("BusinessAuditActivity")) {
                        TFormEmpLoanApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpLoanApp.class, FilterFactory.eq("loanFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("借款审批");
                        } else {
                            List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                            if (!CollectionUtils.isEmpty(ais)) {
                                formMain.setCurrentActivityId("BusinessAuditActivity");
                                formMain.setCurrentActivityName(ais.get(0).getActivityName());
                            } else {
                                formMain.setCurrentActivityId("CheckFinanceActivity");
                                formMain.setCurrentActivityName("费用核算岗审核");
                            }
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckFinanceActivity")) {
                        TFormEmpLoanApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpLoanApp.class, FilterFactory.eq("loanFormNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("借款审批");
                        } else {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckFinanceActivity")) {
                        TFormEmpLoanApp ecApp = commonManager.findUniqueEntityByFilter(TFormEmpLoanApp.class, FilterFactory.eq("loanFormNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("借款审批");
                        }
                    }
                    commonManager.update(formMain);
                }
            }
        }
    }


    @Test
    public void recoverMainFlowData_T_FORM_NONBUSI_PAYABLE() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_NONBUSI_PAYABLE"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("BusinessAuditActivity");
                        List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                        if (!CollectionUtils.isEmpty(ais)) {
                            formMain.setCurrentActivityName(ais.get(0).getActivityName());
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("BusinessAuditActivity")) {
                        TFormNonBusiPayable ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPayable.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报账申请");
                        } else {
                            List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                            if (!CollectionUtils.isEmpty(ais)) {
                                formMain.setCurrentActivityId("BusinessAuditActivity");
                                formMain.setCurrentActivityName(ais.get(0).getActivityName());
                            } else {
                                formMain.setCurrentActivityId("CheckInfoActivity");
                                formMain.setCurrentActivityName("收单审核人审核");
                            }
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckInfoActivity")) {
                        TFormNonBusiPayable ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPayable.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报账申请");
                        } else {
                            formMain.setCurrentActivityId("CheckFinanceActivity");
                            formMain.setCurrentActivityName("费用核算岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckFinanceActivity")) {
                        TFormNonBusiPayable ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPayable.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报账申请");
                        } else {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckFinanceActivity")) {
                        TFormNonBusiPayable ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPayable.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报账申请");
                        }
                    }
                    commonManager.update(formMain);
                }
            }
        }
    }


    @Test
    public void recoverMainFlowData_T_FORM_NONBUSI_PAYMENT() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_NONBUSI_PAYMENT"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("BusinessAuditActivity");
                        List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                        if (!CollectionUtils.isEmpty(ais)) {
                            formMain.setCurrentActivityName(ais.get(0).getActivityName());
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("BusinessAuditActivity")) {
                        TFormNonBusiPayment ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPayment.class, FilterFactory.eq("payNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("应付申请");
                        } else {
                            List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                            if (!CollectionUtils.isEmpty(ais)) {
                                formMain.setCurrentActivityId("BusinessAuditActivity");
                                formMain.setCurrentActivityName(ais.get(0).getActivityName());
                            } else {
                                formMain.setCurrentActivityId("CheckFinanceActivity");
                                formMain.setCurrentActivityName("费用核算岗审核");
                            }
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckFinanceActivity")) {
                        TFormNonBusiPayment ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPayment.class, FilterFactory.eq("payNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("应付申请");
                        } else {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckFinanceActivity")) {
                        TFormNonBusiPayment ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPayment.class, FilterFactory.eq("payNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("应付申请");
                        }
                    }
                    commonManager.update(formMain);
                }
            }
        }
    }


    @Test
    public void recoverMainFlowData_T_FORM_NONBUSI_PRE_PAY() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_NONBUSI_PRE_PAY"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("BusinessAuditActivity");
                        List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                        if (!CollectionUtils.isEmpty(ais)) {
                            formMain.setCurrentActivityName(ais.get(0).getActivityName());
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("BusinessAuditActivity")) {
                        TFormNonBusiPrePay ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPrePay.class, FilterFactory.eq("preFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("预付申请");
                        } else {
                            List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                            if (!CollectionUtils.isEmpty(ais)) {
                                formMain.setCurrentActivityId("BusinessAuditActivity");
                                formMain.setCurrentActivityName(ais.get(0).getActivityName());
                            } else {
                                formMain.setCurrentActivityId("CheckFinanceActivity");
                                formMain.setCurrentActivityName("费用核算岗审核");
                            }
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckFinanceActivity")) {
                        TFormNonBusiPrePay ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPrePay.class, FilterFactory.eq("preFormNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("预付申请");
                        } else {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckFinanceActivity")) {
                        TFormNonBusiPrePay ecApp = commonManager.findUniqueEntityByFilter(TFormNonBusiPrePay.class, FilterFactory.eq("preFormNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckFinanceActivity");
                            formMain.setCurrentActivityName("费用复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("预付申请");
                        }
                    }
                    commonManager.update(formMain);
                }
            }
        }
    }


    @Test
    public void recoverMainFlowData_T_FORM_BUSI_PAYABLE() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_BUSI_PAYABLE"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("BusinessAuditActivity");
                        List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                        if (!CollectionUtils.isEmpty(ais)) {
                            formMain.setCurrentActivityName(ais.get(0).getActivityName());
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("BusinessAuditActivity")) {
                        TFormBusiPayable ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPayable.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报账申请");
                        } else {
                            List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                            if (!CollectionUtils.isEmpty(ais)) {
                                formMain.setCurrentActivityId("BusinessAuditActivity");
                                formMain.setCurrentActivityName(ais.get(0).getActivityName());
                            } else {
                                formMain.setCurrentActivityId("CheckInfoActivity");
                                formMain.setCurrentActivityName("收单审核人审核");
                            }
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckInfoActivity")) {
                        TFormBusiPayable ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPayable.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报账申请");
                        } else {
                            formMain.setCurrentActivityId("CheckPayableFinaActivity");
                            formMain.setCurrentActivityName("应付核算岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckPayableFinaActivity")) {
                        TFormBusiPayable ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPayable.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报账申请");
                        } else {
                            formMain.setCurrentActivityId("ReCheckPayableFinanceActivity");
                            formMain.setCurrentActivityName("应付复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckPayableFinanceActivity")) {
                        TFormBusiPayable ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPayable.class, FilterFactory.eq("ecFormNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckPayableFinanceActivity");
                            formMain.setCurrentActivityName("应付复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("报账申请");
                        }
                    }
                }
                commonManager.update(formMain);
            }
        }
    }


    @Test
    public void recoverMainFlowData_T_FORM_BUSI_PAYMENT() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_BUSI_PAYMENT"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("BusinessAuditActivity");
                        List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                        if (!CollectionUtils.isEmpty(ais)) {
                            formMain.setCurrentActivityName(ais.get(0).getActivityName());
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("BusinessAuditActivity")) {
                        TFormBusiPayment ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPayment.class, FilterFactory.eq("payNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("应付申请");
                        } else {
                            TFormBusiPayment payment = commonManager.findUniqueEntityByFilter(TFormBusiPayment.class, FilterFactory.eq("payNo", formMain.getFormNo()));
                            if ("excel".equalsIgnoreCase(payment.getAttribute1())) {
                                formMain.setCurrentActivityId("CheckPayableFinaActivity");
                                formMain.setCurrentActivityName("应付核算岗审核");
                            } else {
                                List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                                if (!CollectionUtils.isEmpty(ais)) {
                                    formMain.setCurrentActivityId("BusinessAuditActivity");
                                    formMain.setCurrentActivityName(ais.get(0).getActivityName());
                                } else {
                                    formMain.setCurrentActivityId("CheckPayableFinaActivity");
                                    formMain.setCurrentActivityName("应付核算岗审核");
                                }
                            }
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckPayableFinaActivity")) {
                        TFormBusiPayment ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPayment.class, FilterFactory.eq("payNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("应付申请");
                        } else {
                            formMain.setCurrentActivityId("ReCheckPayableFinanceActivity");
                            formMain.setCurrentActivityName("应付复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckPayableFinanceActivity")) {
                        TFormBusiPayment ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPayment.class, FilterFactory.eq("payNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckPayableFinanceActivity");
                            formMain.setCurrentActivityName("应付复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("应付申请");
                        }
                    }
                }
                commonManager.update(formMain);
            }
        }
    }


    @Test
    public void recoverMainFlowData_T_FORM_BUSI_PAY_PAY() {
        List<TFormMain> formMains = formMainManager.findEntityByFilter(FilterFactory.eq("bizTableName", "T_FORM_BUSI_PRE_PAY"),
                FilterFactory.eq("formStatus", "2"));
        if (!CollectionUtils.isEmpty(formMains)) {
            List<DbOrder> dbOrders = Lists.newArrayList();
            dbOrders.add(new DbOrder("createdDate", DbOrder.DbOrderTypeEnum.DESC));
            for (TFormMain formMain : formMains) {
                List<TFormStep> ts = formStepManager.findEntityByFilter(dbOrders, FilterFactory.eq("procInstId", formMain.getProcInstId()));
                if (!CollectionUtils.isEmpty(ts)) {
                    if (ts.get(0).getActivityEnName().equalsIgnoreCase("DraftActivity")) {
                        formMain.setCurrentActivityId("BusinessAuditActivity");
                        List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                        if (!CollectionUtils.isEmpty(ais)) {
                            formMain.setCurrentActivityName(ais.get(0).getActivityName());
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("BusinessAuditActivity")) {
                        TFormBusiPrePay ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPrePay.class, FilterFactory.eq("prePayNo", formMain.getFormNo()));
                        if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("预付申请");
                        } else {
                            List<TFormAuditInst> ais = formAuditInstManager.findEntityByProcInstId(formMain.getProcInstId(), false);
                            if (!CollectionUtils.isEmpty(ais)) {
                                formMain.setCurrentActivityId("BusinessAuditActivity");
                                formMain.setCurrentActivityName(ais.get(0).getActivityName());
                            } else {
                                formMain.setCurrentActivityId("CheckPayableFinaActivity");
                                formMain.setCurrentActivityName("应付核算岗审核");
                            }

                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("CheckPayableFinaActivity")) {
                        TFormBusiPrePay ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPrePay.class, FilterFactory.eq("prePayNo", formMain.getFormNo()));
                        if (null != ecApp && !ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("预付申请");
                        } else {
                            formMain.setCurrentActivityId("ReCheckPayableFinanceActivity");
                            formMain.setCurrentActivityName("应付复核岗审核");
                        }
                    } else if (ts.get(0).getActivityEnName().equalsIgnoreCase("ReCheckPayableFinanceActivity")) {
                        TFormBusiPrePay ecApp = commonManager.findUniqueEntityByFilter(TFormBusiPrePay.class, FilterFactory.eq("prePayNo", formMain.getFormNo()));
                        if (null != ecApp && ecApp.getStatus().equalsIgnoreCase("approving")) {
                            formMain.setCurrentActivityId("ReCheckPayableFinanceActivity");
                            formMain.setCurrentActivityName("应付复核岗审核");
                        } else if (null != ecApp && (ecApp.getStatus().equalsIgnoreCase("recalled") || ecApp.getStatus().equalsIgnoreCase("rejected"))) {
                            formMain.setCurrentActivityId("DraftActivity");
                            formMain.setCurrentActivityName("预付申请");
                        }
                    }
                }
                commonManager.update(formMain);
            }
        }

    }

    @Test
    public void testTax(){
        List<TFormNonBusiPayable> lists = commonManager.findEntityByFilter(TFormNonBusiPayable.class);
        for (TFormNonBusiPayable p : lists ) {
            BigDecimal amount = BigDecimal.ZERO;
            Set<TFormNonBusiPayDetail> payDes = p.getPayDetailSet();
            for (TFormNonBusiPayDetail  payDe : payDes ) {
                amount.add(payDe.getTaxAmount());
            }
            p.setTaxAmountCount(amount);
            commonManager.update(p);
        }
    }

}


