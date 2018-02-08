package com.ronglian.fssc.webapp.platform.manager.common;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import com.ronglian.fssc.webapp.external.manager.ebs.api.GlCheckBudgetManager;
import com.ronglian.fssc.webapp.external.manager.ebs.event.GlJournalImportAsynEvent;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_check_budget.ObjFssGlBudgetHVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_journal_import.ObjFssGlJournalTblVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_journal_import.ObjFssGlJournalVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.external.EbsApiException;
import com.ronglian.fssc.webapp.external.manager.ebs.api.GlJournalImportManager;
import com.ronglian.fssc.webapp.external.manager.ebs.api.GlRevBudgetManager;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_check_budget.GlCheckBudgetReqVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_check_budget.ObjFssGlBudgetLVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_journal_import.ObjFssGlJournalLVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_rev_budget.ObjFssGlRevTblVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.result.ObjFssReturnObjVo;
import com.ronglian.fssc.webapp.platform.domain.ledger.TFormGLApp;
import com.ronglian.fssc.webapp.platform.domain.ledger.TFormGLDetail;
import com.ronglian.fssc.webapp.platform.manager.ledger.TFormGLAppManager;

public class TSysConfigTest extends TestBase {
    @Autowired
    private GlCheckBudgetManager glCheckBudgetManager;
    @Autowired
    private TFormGLAppManager gLAppManager;
    @Autowired
    private GlJournalImportManager glJImportManager;
    @Autowired
    private GlRevBudgetManager glRevBudgetManager;

    /*
     * @Test
     * public void testSaveOrUpdate() {
     * Set<String> invoiceNoSet = new HashSet<String>();
     * invoiceNoSet.add("1001");
     * invoiceNoSet.add("1002");
     * List<VFssArInvoicesVo> invoiceNoList = this.invoiceManager.getAllInvoicesByFilter(FilterFactory.in("INVOICE_NUMBER", invoiceNoSet.toArray()));
     * System.out.println("*****************8");
     * System.out.println(null ==invoiceNoList );
     * System.out.println(invoiceNoList.size() );
     * }
     */
    /**
     * @Method testYsKz
     * @Description 预算控制
     * @throws IOException
     * @throws EbsApiException
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testYsKz() throws IOException, EbsApiException {
        TFormGLApp dbApp = this.commonManager.get(TFormGLApp.class, "E1E7860D4CD94037BCDFEFE508C07845");// 根据Id获取总账信息
        List<ObjFssGlBudgetLVo> glBudgetList = Lists.newArrayList();// 总账明细信息
        for (TFormGLDetail detail : dbApp.getGlDetailSet()) {
            ObjFssGlBudgetLVo glDetail = new ObjFssGlBudgetLVo();
            glDetail.setExpenseLineNumber(detail.getLineNo().toString());
            glDetail.setDrAmount(detail.getDebitAmount());
            glDetail.setCrAmount(detail.getCreditsAmount());
            glDetail.setConcatenatedSegments(detail.getAccountTitle());
            glBudgetList.add(glDetail);
        }

        ObjFssGlBudgetHVo glH = new ObjFssGlBudgetHVo();// 总账报账信息
        glH.setExpenseNumber(dbApp.getGlFormNo());
        glH.setGlDate(dbApp.getAccountDate());
        glH.setCurrencyCode(dbApp.getCurrencyTypeEnumId());
        glH.setExchangeRate(dbApp.getRate());
        glH.setList(glBudgetList);

        GlCheckBudgetReqVo vo = new GlCheckBudgetReqVo();
        vo.setVo(glH);

        ObjFssReturnObjVo response = glCheckBudgetManager.syncExecute(vo);
        System.out.println("预算控制*************");
        System.out.println(response.getReturnStatus());
        System.out.println(JSONObject.toJSONString(response));
    }

    /**
     * @Method testZzrjz
     * @Description 总账日记账
     * @throws IOException
     * @throws EbsApiException
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testZzrjz() throws IOException, EbsApiException {
        TFormGLApp app = this.commonManager.get(TFormGLApp.class, "E1E7860D4CD94037BCDFEFE508C07845");// 根据Id获取总账信息
        List<ObjFssGlJournalLVo> journalLList = Lists.newArrayList();
        for (TFormGLDetail detail : app.getGlDetailSet()) {
            ObjFssGlJournalLVo glDetail = new ObjFssGlJournalLVo();
            glDetail.setLineNumber(detail.getLineNo().toString());
            glDetail.setDrAmount(detail.getDebitAmount().doubleValue());
            glDetail.setCrAmount(detail.getCreditsAmount().doubleValue());
            glDetail.setConcatenatedSegments(detail.getAccountTitle());
            glDetail.setLineDescription(detail.getExplain());
            glDetail.setCfItem(detail.getCashFlow());
            journalLList.add(glDetail);
        }
        List<ObjFssGlJournalVo> journalList = Lists.newArrayList();
        ObjFssGlJournalVo jVo = new ObjFssGlJournalVo();
        jVo.setExpenseNumber(app.getGlFormNo());
        jVo.setJournalDescription(app.getStatus());
        jVo.setAccountingDate(app.getAccountDate());
        jVo.setActualFlag("A");
        jVo.setCurrencyCode(app.getCurrencyTypeEnumId());
        jVo.setExchangeRate(app.getRate());
        jVo.setList(journalLList);

        journalList.add(jVo);
        ObjFssGlJournalTblVo journalTbl = new ObjFssGlJournalTblVo();
        journalTbl.setList(journalList);
       // glJImportManager.asynExecute(journalTbl, null, null, null, GlJournalImportAsynEvent.class);
    }

    /**
     * @Method testCxblk
     * @Description 冲销保留款
     * @throws IOException
     * @throws EbsApiException
     * @return void
     * @since 1.0.0
     */
    @Test
    @Transactional
    public void testCxblk() throws IOException, EbsApiException {
        // TFormGLApp dbApp = this.commonManager.get(TFormGLApp.class, "2748B0EC992B449DA113ECFB61055821");//根据Id获取总账信息
        ObjFssGlRevTblVo glTv = new ObjFssGlRevTblVo();
        glTv.setEcCodes("2748B0EC992B449DA113ECFB61055821");

        ObjFssReturnObjVo response = glRevBudgetManager.syncExecute(glTv);
        System.out.println("冲销保留款*************");
        System.out.println(response.getReturnStatus());
        System.out.println(JSONObject.toJSONString(response));
    }
}
