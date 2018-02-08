package com.ronglian.fssc.webapp.webservice;

import com.ronglian.fssc.webapp.core.enums.BizTypeEnum;

public enum TypeTest {
    /**
     * 员工费用报帐
     */
    EMP_EC("员工费用报帐", "0"),
    /**
     * 员工借款
     */
    EMP_LOAN("员工借款", "1"),
    /**
     * 员工还款
     */
    EMP_REPAYMENT("员工还款", "2"),
    /**
     * 其它收款
     */
    RM_OTHER("其它收款", "3"),

    /**
     * 收款管理
     */
    RM_COLLECT("收款管理", "4"),
    /**
     * 总账报账
     */
    GL_REIMBURSEMENT("总账报账", "5"),
    /**
     * 总账报账冲销
     */
    GL_WRITEOFF("总账报账冲销", "6"),
    /**
     * 发票导入
     */
    IMP_INVOICE("发票导入", "7"),
    /**
     * 非经营性应付报账
     */
    NON_BUSI_EC("对公支付报销", "8"),
    /**
     * 非经营性应付付款
     */
    NON_BUSI_PAY("对公付款申请", "9"),
    /**
     * 非经营性预付
     */
    NON_BUSI_PRE("对公预付申请", "10"),
    /**
     * 经营性预付
     */
    BUSI_PRE("PO预付申请", "11"),
    /**
     * 借阅申请
     */
    LEND_APPLY("借阅申请", "12"),
    /**
     * 经营性应付付款
     */
    BUSI_PAY("PO付款申请", "13"),
    /**
     * 经营性应付报账
     */
    BUSI_PAYABLE("PO发票报账", "14"),
    /**
     * 立项
     */
    SET_PROJECT("立项申请", "15"),
    /**
     * 供应商
     */
    SUPPLIER_INFO("供应商申请", "91"),
    /**
     * 费用单
     */
    DXTFEE_INFO("费用单申请","45"),

    /**
     * 收款单
     */
    CASH_APPLY("收款单申请", "94"),
    /**
     * 客户
     */
    CLIENT_INFO("客户申请","92"),
    /**
     * 应付发票单
     */
    DXT_INVOICE("应付发票单", "41"),
    /**
     * 预付款通知单
     */
    DXT_PREPAY("预付款通知单", "42"),
    /**
     * 付款通知单
     */
    DXT_PAY("付款通知单", "43"),
    /**
     * 价保单 44
     */
    P_PROTECT("价保单申请","44"),
    /**
     * 返利单
     */
    C_PROFIT("返利单申请","46"),
    /**
     * 综合备案申请
     */
    MULTPLE_REQUEST("综合备案申请","31"),
    /**
     * 市场费备案申请
     */
    MARKET_REQUEST("市场费备案申请","32"),
    /**
     * 装修费备案申请
     */
    DECORATER_REQUEST("装修费备案申请","33");

    private TypeTest(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static TypeTest getBizTypeEnumByVal(String value) {
        for (TypeTest e : TypeTest.values()) {
            if (value.equals(e.getValue())) {
                return e;
            }
        }
        return null;
    }
}
