package com.ronglian.fssc.webapp.UtilsTest;

import com.alibaba.fastjson.JSONObject;

import com.ronglian.fssc.webapp.platform.domain.payment.TFormEmpRepaymentApp;


import com.ronglian.fssc.webapp.platform.manager.common.StringUtil;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.ClassUtils;

import java.lang.reflect.InvocationTargetException;




public class BeanUtilsTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        BeanUtilsTest.test1();
    }


    public static void test1() throws InvocationTargetException, IllegalAccessException, InstantiationException {

        TFormEmpRepaymentApp  app1=new TFormEmpRepaymentApp();
        TFormEmpRepaymentApp  app2=new TFormEmpRepaymentApp();


        BeanUtils.setProperty(app1,"attachmentNum","12");
        BeanUtils.setProperty(app1,"cmpBankAccount","123456");
        BeanUtils.setProperty(app1,"cmpBankName","工商银行");

        System.out.println(JSONObject.toJSONString(app1));


        Class clazz=TFormEmpRepaymentApp.class;

        Object obj=clazz.newInstance();

        BeanUtils.copyProperties(obj,app1);

        System.out.println(JSONObject.toJSONString(obj));




    }
}
