package com.ronglian.fssc.webapp.FanXing;

import com.google.common.collect.Maps;
import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFx {

    public static void main(String[] args) {
        //创建无限制通配符的数组是可以的
        List<? >[] arr=new List<? >[]{};

        //泛型静态工厂方法创建 Map集合 google的Maps类帮助创建了
      /*  public static <K, V> HashMap<K, V> newHashMap() {
            return new HashMap<K, V>();
        }*/

        Map<String,List<String>> map= Maps.newHashMap();







    }
}
