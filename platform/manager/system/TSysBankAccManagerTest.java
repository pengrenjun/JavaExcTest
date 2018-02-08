package com.ronglian.fssc.webapp.platform.manager.system;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronglian.fssc.webapp.TestBase;

public class TSysBankAccManagerTest extends TestBase {

    @Autowired
    private TSysBankAccManager manager;

    @Test
    public void loadExcelDataSupplier() throws Exception {
        String fPath = "F:/银行账户信息导入-供应商.xlsx";
        manager.importExcelDataSupplier(fPath);
    }
    
    
    @Test
    public void loadExcelDataEmployee() throws Exception {
        String fPath = "F:/work_home/项目相关/SSC/供应商银行账户信息维护/银行账户信息导入-员工-车网.xlsx";
        manager.importExcelDataEmployee(fPath,null,true);
    }
}
