package com.ronglian.fssc.webapp.UtilsTest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.core.enums.BankBillEnum;
import com.ronglian.fssc.webapp.core.enums.RecognitionStatusEnum;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringFormat<T> {

    public static<T> void main(String[] args) {

      /*  String str1=String.format("%5.2s","234234.123");
        System.out.println(str1);

        String str2=String.format("%s.local.package","ronglian.package");
        System.out.println(str2);
*/
        Collection<String> list1= new ArrayList<>();
/*
        list1.add( "asdcadsca");
        list1.add("Ssbjgsjsndf");
        //创建集合的镜像 只能查看
        Collection<String>  list2= CollectionUtils.unmodifiableCollection(list1);
        Object[] arr= list2.toArray();

        String res= JSONObject.toJSONString(list2);

        System.out.println(res);
        System.out.println(arr.length);*/

        /* String a=BankBillEnum.getBankBillEnumName(String.valueOf(BankBillEnum.getBankBillEnumByVal("RECEIVABLE_WAY_BILL")));
        System.out.println(a);*/

      String b= RecognitionStatusEnum.getRecognitionStatusEnumName("new");
        System.out.println(b);



        String c=BankBillEnum.getBankBillEnumName("RECEIVABLE_WAY_BANK");
        System.out.println(c);






    }
}
