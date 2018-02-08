package com.ronglian.fssc.webapp.external.manager.ebs.view;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronglian.fssc.webapp.TestBase;


/**
 * ProjectName: FSSC
 * Company: 荣联
 *
 * @Copyright (c) All rights reserved 2016.
 * @Author: xlliu01@ronglian.com
 * @Date: 24/03/2016
 * @Time: 13:27
 * @Description: 预付未核销业务测试
 */
public class VFssApUnappInvoicesManagerTest  extends TestBase{
    @Autowired
    private  VFssApUnappInvoicesManager apUnappInvoicesManager;

    @Test
    public void getAllTasks(){
        List<?> list = apUnappInvoicesManager.findAllByFilter();
        Assert.assertNotNull(list);
        System.out.println("VFssApUnappInvoicesManager>>" + list.size());
    }
}  
