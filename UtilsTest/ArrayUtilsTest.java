package com.ronglian.fssc.webapp.UtilsTest;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang.ArrayUtils;


public class ArrayUtilsTest {

    public static void main(String[] args) {

        String a="a,b,c";
        String[] b = String.valueOf(a).split(",");
        System.out.println(ArrayUtils.toString(b));

    }
    }


