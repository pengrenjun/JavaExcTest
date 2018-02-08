package com.ronglian.fssc.webapp.UtilsTest;

import com.ronglian.fssc.webapp.platform.utils.wx.StringUtils;

public class StringUtilsTest {


    public static void main(String[] args) {
        String str1 = "" ;
        String str2 = " " ;
        String str3 = "\t" ;
        String str4 = null ;
        String str5 = "123" ;
        String str6 = "ABCDEFG" ;
        String str7 = "Itfeels good to use JakartaCommons.\r\n" ;

        System.out.println(StringUtils.isBlank(str2));
        System.out.println(str2.length());

        System.out.println(StringUtils.isNumeric(str5));




    }
}




