package com.ronglian.fssc.webapp.UtilsTest;

import com.deloitte.si.core.utils.BeanUtils;
import com.ronglian.fssc.webapp.platform.utils.wx.StringUtils;

public class eqTest {
    public static void main(String[] args) {
        String str1=null;
        String str2="asd";
        System.out.println(str1==(str2));

        System.out.println(StringUtils.equals(str1,str2));

        String a="nontemp/2017/12/1513081954272_1 - 副本 - 副本 - 副本.jpg";
        String b="nontemp/1513170954170_2 - 副本 - 副本 - 副本 - 副本.jpg";

        String c= org.apache.commons.lang3.StringUtils.replace(a,"Supplier","nontemp");
        System.out.println(c);


    }
}
