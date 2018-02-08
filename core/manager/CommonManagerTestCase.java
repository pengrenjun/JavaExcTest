package com.ronglian.fssc.webapp.core.manager;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.si.core.manager.CommonManager;
import com.deloitte.si.core.utils.BeanUtils;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.domain.payment.TFormPayAppList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * ProjectName: FSSC Reimbursement
 * Company: 德勤Deloitte
 *
 * @Copyright (c) All rights reserved 2015.
 * @Author: Liu, Tang
 * @Date: 25/01/2016
 * @Time: 17:30
 * @Description: File Template | Description Write Here.
 */
public class CommonManagerTestCase extends TestBase {
    @Autowired
    private CommonManager commonManager;

    @Test
    public void testSave() throws Exception {
        String source = "[{\"appPayAmount\":10000000,\"appPayDate\":\"2016-01-30\",\"busiOrigin\":\"NONBUSINESS\",\"businessEntity\":\"101\",\"currencyTypeEnumId\":\"CNY\",\"impBankAccount\":\"200101040002903\",\"impBankAddr\":\"北京\",\"impBankName\":\"中国农业银行北京先农坛支行\",\"invoiceAmount\":29000,\"invoiceNo\":\"101AP20151201003\",\"invoiceType\":\"STANDARD\",\"payAppId\":\"10120160110046\",\"payWay\":\"EBANK\",\"priorityLevel\":\"NORMAL\",\"rate\":1,\"supplierName\":\"胡文娟\",\"supplierNo\":\"2820\"}]";
        List<TFormPayAppList> payAppList = JSONObject.parseArray(source, TFormPayAppList.class);
        //this.commonManager.save(BeanUtils.collectionToArray(payAppList));


        Object result = payAppList;
        if (result instanceof Collection) {
            Collection<TFormPayAppList> results = (Collection<TFormPayAppList>) result;
            this.commonManager.save(BeanUtils.collectionToArray(results));
        }
    }
}
