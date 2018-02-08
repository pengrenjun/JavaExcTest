package com.ronglian.fssc.webapp.external.manager.ebs.api;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.external.vo.ebs.api.result.ProcedureResultVo;

public class GlJournalImportUecManagerTest extends TestBase{
    @Autowired
    private GlJournalImportUecManager gl;

    @Test
    public void testGlJournalImportUecManager(){
        ProcedureResultVo vo = gl.apInvoiceImportUec("1", "1", "1");
        Assert.assertNotNull(vo);
    }

}