package com.ronglian.fssc.webapp.external.manager.ebs.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.si.core.domain.datasource.FormSearchCondition;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApNcUserVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApNcVendorVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApPaymentMethodsVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApPwPaymethodVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApPwPayplanVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApSupplierAccountVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApSupplierVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApSysPaymentProfilesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApTaxCodesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApTermsVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssApUnappInvoicesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssCeBankAccountsVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssCustSalesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssFndCurrenciesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssGlCoaSegmentValuesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssGlCoaValidRuleLinesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssGlCoaValidationRulesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssGlHeadersVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssGlLinesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssGlPeriodStatusesVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssGlPeriodsVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssHrDepartmentVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssHrOperatingUnitsVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssHrOuMappingVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssNcBankAttVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssPerAssignmentVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssPerPeopleVo;
import com.ronglian.fssc.webapp.external.vo.ebs.view.VFssUecHrOuDeptVo;

public class VMainManagerTest extends TestBase {
    // private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VFssApUnappInvoicesManager vFssApUnappInvoicesManager;
    @Autowired
    private VFssGlCoaSegmentValuesManager vFssGlCoaSegmentValuesManager;
    @Autowired
    private VFssGlCoaValidationRulesManager vFssGlCoaValidationRulesManager;
    @Autowired
    private VFssGlCoaValidRuleLinesManager vFssGlCoaValidRuleLinesManager;

    /** 应付发票信息：员工未核销借款 [V_FSS_AP_UNAPP_INVOICES] */
    @Test
    public void execApUnappInvoices() {
        // String sql = "SELECT * FROM V_FSS_AP_UNAPP_INVOICES@EBSDB ";
        List<VFssApUnappInvoicesVo> list = vFssApUnappInvoicesManager.findAllByFilter(new FormSearchCondition().getFilters());
        System.out.println("V_FSS_AP_UNAPP_INVOICES >>>>>>> " + list.size());
        // logger.info("V_FSS_AP_UNAPP_INVOICES ::::: " + list.size());

    }

    /** 科目九段值:将COA九段值传送值报账 [V_FSS_GL_COA_SEGMENT_VALUES] */
    @Test
    public void execGlCoaSegmentValues() {
        FormSearchCondition fsc = new FormSearchCondition();
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("SET_OF_BOOKS_ID_EQ", "2022");
        fsc.setVars(vars);
        List<VFssGlCoaSegmentValuesVo> list = vFssGlCoaSegmentValuesManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_GL_COA_SEGMENT_VALUES >>>>>>> " + list.size());
        // logger.info("V_FSS_GL_COA_SEGMENT_VALUES ::::: " + list.size());
    }

    /** 交叉验证规则：[V_FSS_GL_COA_VALIDATION_RULES,V_FSS_GL_COA_VALID_RULE_LINES] */
    @Test
    public void execGlCoaValidations() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssGlCoaValidationRulesVo> rules = vFssGlCoaValidationRulesManager.findAllByFilter(fsc.getFilters());
        List<VFssGlCoaValidRuleLinesVo> lines = vFssGlCoaValidRuleLinesManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_GL_COA_VALIDATION_RULES >>>>>>> " + rules.size());
        System.out.println("V_FSS_GL_COA_VALID_RULE_LINES >>>>>>> " + lines.size());
    }

    //    /** 币种：[V_FSS_FND_CURRENCIES] */
    //    @Autowired
    //    private VFssCurrenciesManager vFssCurrenciesManager;

    //    @Test
    //    public void execFndCurrencies() {
    //        FormSearchCondition fsc = new FormSearchCondition();
    //        List<VFssFndCurrenciesVo> list = vFssFndCurrenciesManager.findAllByFilter(fsc.getFilters());
    //        System.out.println("V_FSS_FND_CURRENCIES >>>>>>> " + list.size());
    //    }

    /** 会计日历：[V_FSS_GL_PERIODS] */
    @Autowired
    private VFssGlPeriodsManager vFssGlPeriodsManager;

    @Test
    public void execGlPeriods() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssGlPeriodsVo> list = vFssGlPeriodsManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_GL_PERIODS >>>>>>> " + list.size());
    }

    /** 会计期间：[V_FSS_GL_PERIOD_STATUSES] */
    @Autowired
    private VFssGlPeriodStatusesManager vFssGlPeriodStatusesManager;

    @Test
    public void execGlPeriodStatuses() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssGlPeriodStatusesVo> list = vFssGlPeriodStatusesManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_GL_PERIOD_STATUSES >>>>>>> " + list.size());
    }

    /** 凭证头视图：[V_FSS_GL_HEADERS] 暂搁置 */
    @Autowired
    private VFssGlHeadersManager vFssGlHeadersManager;

    @Test
    public void execGlHeaders() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssGlHeadersVo> list = vFssGlHeadersManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_GL_HEADERS >>>>>>> " + list.size());
    }

    /** 凭证行视图：[V_FSS_GL_LINES] 暂搁置 */
    @Autowired
    private VFssGlLinesManager vFssGlLinesManager;

    @Test
    public void execGlLines() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssGlLinesVo> list = vFssGlLinesManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_GL_LINES >>>>>>> " + list.size());
    }

    /**
     * 员工信息:
     * 将员工供应商、
     * 员工银行账户及银行信息，员工对应的管理部门、
     * 公司等信息传送至报账平台，以便员工提交报账及支付申请[V_FSS_PER_PEOPLE]
     */
    @Autowired
    private VFssPerPeopleManager vFssPerPeopleManager;

    @Test
    public void execPerPeople() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssPerPeopleVo> list = vFssPerPeopleManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_PER_PEOPLE >>>>>>> " + list.size());
    }

    /**
     * 员工组织分配信息:
     * 员工组织，公司，管理部门信息[V_FSS_PER_ASSIGNMENT]
     */
    @Autowired
    private VFssPerAssignmentManager vFssPerAssignmentManager;

    @Test
    public void execPerAssignment() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssPerAssignmentVo> list = vFssPerAssignmentManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_PER_ASSIGNMENT >>>>>>> " + list.size());
    }

    /**
     * 供应商信息:
     * 将供应商、及供应商银行账户（客户化界面）传送至报账，以便在报账系统进行报账及付款[V_FSS_AP_SUPPLIER]
     */
    @Autowired
    private VFssApSupplierManager vFssApSupplierManager;

    @Test
    public void execApSupplier() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApSupplierVo> list = vFssApSupplierManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_SUPPLIER >>>>>>> " + list.size());
    }

    /**
     * 供应商银行信息:供应商银行账户[V_FSS_AP_SUPPLIER_ACCOUNT]
     */
    @Autowired
    private VFssApSupplierAccountManager vFssApSupplierAccountManager;

    @Test
    public void execApSupplierAccount() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApSupplierAccountVo> list = vFssApSupplierAccountManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_SUPPLIER_ACCOUNT >>>>>>> " + list.size());
    }

    /**
     * 银行账户信息同步:将公司银行、银行分行、支行及账户信息同步至报账系统[V_FSS_CE_BANK_ACCOUNTS]
     */
    @Autowired
    private VFssCeBankAccountsManager vFssCeBankAccountsManager;

    @Test
    public void execCeBankAccounts() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssCeBankAccountsVo> list = vFssCeBankAccountsManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_CE_BANK_ACCOUNTS >>>>>>> " + list.size());
    }

    /** 付款条件:应付发票付款条件同步；目前默认为“SPH_立即”[V_FSS_AP_TERMS] */
    @Autowired
    private VFssApTermsManager vFssApTermsManager;

    @Test
    public void execApTerms() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApTermsVo> list = vFssApTermsManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_TERMS >>>>>>> " + list.size());
    }

    /** 付款方法：应付发票付款方法同步；目前仅有“通用”[V_FSS_AP_PAYMENT_METHODS] */
    @Autowired
    private VFssApPaymentMethodsManager vFssApPaymentMethodsManager;

    @Test
    public void execApPaymentMethods() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApPaymentMethodsVo> list = vFssApPaymentMethodsManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_PAYMENT_METHODS >>>>>>> " + list.size());
    }

    /**
     * 付款处理配置文件：
     * 付款时将付款处理配置文件同步至报账，目前仅有“SPH_标准付款”
     * [V_FSS_AP_SYS_PAYMENT_PROFILES]
     */
    @Autowired
    private VFssApSysPaymentProfilesManager vFssApSysPaymentProfilesManager;

    @Test
    public void execApSysPaymentProfiles() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApSysPaymentProfilesVo> list = vFssApSysPaymentProfilesManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_SYS_PAYMENT_PROFILES >>>>>>> " + list.size());
    }

    /** 税率：传送至报账系统，供发票上传时校验（含快码SPH_AR_TAX）[V_FSS_AP_TAX_CODES] */
    @Autowired
    private VFssApTaxCodesManager vFssApTaxCodesManager;

    @Test
    public void execApTaxCodes() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApTaxCodesVo> list = vFssApTaxCodesManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_TAX_CODES >>>>>>> " + list.size());
    }

    /** 业务实体（公司）信息：公司信息 [V_FSS_HR_OPERATING_UNITS] */
    @Autowired
    private VFssHrOperatingUnitsManager vFssHrOperatingUnitsManager;

    @Test
    public void execHrOperatingUnits() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssHrOperatingUnitsVo> list = vFssHrOperatingUnitsManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_HR_OPERATING_UNITS >>>>>>> " + list.size());
    }

    /** 讯博与EBS业务实体映射关系[V_FSS_HR_OU_MAPPING] */
    @Autowired
    private VFssHrOuMappingManager vFssHrOuMappingManager;

    @Test
    public void execHrOuMapping() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssHrOuMappingVo> list = vFssHrOuMappingManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_HR_OU_MAPPING >>>>>>> " + list.size());
    }

    /** 管理部门信息：[V_FSS_HR_DEPARTMENT] */
    @Autowired
    private VFssHrDepartmentManager vFssHrDepartmentManager;

    @Test
    public void execHrDepartment() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssHrDepartmentVo> list = vFssHrDepartmentManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_HR_DEPARTMENT >>>>>>> " + list.size());
    }

    /**
     * 销售员与客户对应关系（现有优化）：
     * 将客户化的销售员及客户的对应关系传送至报账，以便根据客户推送工单给相应的销售员
     * [V_FSS_CUST_SALES]
     */
    @Autowired
    private VFssCustSalesManager vFssCustSalesManager;

    @Test
    public void execCustSales() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssCustSalesVo> list = vFssCustSalesManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_CUST_SALES >>>>>>> " + list.size());
    }

    /** 资金计划表项：EBS资金计划表项值集：SPH_PW_Payplan[V_FSS_AP_PW_PAYPLAN] */
    @Autowired
    private VFssApPwPayplanManager vFssApPwPayplanManager;

    @Test
    public void execApPwPayPlan() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApPwPayplanVo> list = vFssApPwPayplanManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_PW_PAYPLAN >>>>>>> " + list.size());
    }

    /** 付款方式：[V_FSS_AP_PW_PAYMETHOD] */
    @Autowired
    private VFssApPwPaymethodManager vFssApPwPaymethodManager;

    @Test
    public void execApPwPaymethod() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApPwPaymethodVo> list = vFssApPwPaymethodManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_PW_PAYMETHOD >>>>>>> " + list.size());
    }

    /** 付款员：[V_FSS_AP_NC_USER] */
    @Autowired
    private VFssApNcUserManager vFssApNcUserManager;

    @Test
    public void execApNcUser() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApNcUserVo> list = vFssApNcUserManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_NC_USER >>>>>>> " + list.size());
    }

    /** NC供应商编码：[V_FSS_AP_NC_VENDOR] */
    @Autowired
    private VFssApNcVendorManager vFssApNcVendorManager;

    @Test
    public void execApNcVendor() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssApNcVendorVo> list = vFssApNcVendorManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_AP_NC_VENDOR >>>>>>> " + list.size());
    }

    /** 银行附属属性：[V_FSS_NC_BANK_ATT] */
    @Autowired
    private VFssNcBankAttManager vFssNcBankAttManager;

    @Test
    public void execNcBankAtt() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssNcBankAttVo> list = vFssNcBankAttManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_FSS_NC_BANK_ATT >>>>>>> " + list.size());
    }

    /** 组织架构:[V_UEC_HR_OU_DEPT] */
    @Autowired
    private VFssUecHrOuDeptManager vFssUecHrOuDeptManager;

    @Test
    public void execUecHrOuDept() {
        FormSearchCondition fsc = new FormSearchCondition();
        List<VFssUecHrOuDeptVo> list = vFssUecHrOuDeptManager.findAllByFilter(fsc.getFilters());
        System.out.println("V_UEC_HR_OU_DEPT >>>>>>> " + list.size());
    }
}
