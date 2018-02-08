package com.ronglian.fssc.webapp;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class TestGuava {
   public static void main(String[] args) {
       List<String> oneList = Lists.newArrayList("aaaa","bbbb");
       Set<String> oneSet = Sets.newHashSet(oneList);
       Set<String> twoSet=Sets.newHashSet("aaaa","cccc");  
       SetView<String> diffSetHandle=Sets.difference(oneSet, twoSet);//是得到左边中不同或者特有的元素，若无，则返回长度为0的集合  
       Set<String> diffImmutable=diffSetHandle.immutableCopy();//返回一个不可变的左边Set中特有元素集合的Set拷贝  
       Iterator iter=diffSetHandle.iterator();  
       while(iter.hasNext()){  
           System.out.println("Set的不同元素："+iter.next().toString());  
       }  
      
}
}
