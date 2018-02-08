package com.ronglian.fssc.webapp.external.manager.ebs.api;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_journal_import.ObjFssGlJournalVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_journal_import.ObjFssGlJournalLVo;
import com.ronglian.fssc.webapp.external.vo.ebs.api.gl_journal_import.ObjFssGlJournalTblVo;

/**
 * @Description 总账日记账接口
 * @ClassName GlJournalImportManagerTest
 * @Date 2015年12月11日 下午4:02:45
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class GlJournalImportManagerTest extends ISimpleEbsApiTest<ObjFssGlJournalTblVo> {

    @Override
    protected ObjFssGlJournalTblVo getApiRequestEntity() {
        ObjFssGlJournalVo journal = null;
        List<ObjFssGlJournalLVo> journalLList = null;
        ObjFssGlJournalLVo journalL = null;
        List<ObjFssGlJournalVo> journalList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            journal = new ObjFssGlJournalVo();
            journalLList = Lists.newArrayList();
            for (int j = 0; j < 5; j++) {
                journalL = new ObjFssGlJournalLVo();
                journalL.setDrAmount(10.01d);
                journalL.setCrAmount(10.01);
                journalL.setConcatenatedSegments("0.0.0.0.0.0.0");
                journalLList.add(journalL);
            }
            journal.setList(journalLList);
            journal.setCurrencyCode("CNY");
            journal.setAccountingDate(new Date());
            // journal.setCreatedBy(userId);
            // journal.setCreationDate(date);
            journalList.add(journal);
        }
        ObjFssGlJournalTblVo journalTbl = new ObjFssGlJournalTblVo();
        journalTbl.setList(journalList);
        return journalTbl;
    }
}
