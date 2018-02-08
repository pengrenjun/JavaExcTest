package com.ronglian.fssc.webapp.external.manager.ebs.view;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronglian.fssc.webapp.TestBase;

public class VFssTasksManagerTest extends TestBase{
    @Autowired
    private VFssTasksManager fssTasksManager;

    @Test
    public void getAllTasks(){
        List<?> list = fssTasksManager.findAllByFilter();
        Assert.assertNotNull(list);
        //System.out.println(">>" + list.size());
    }
}   

