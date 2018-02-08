package com.ronglian.fssc.webapp.UtilsTest;
import static java.lang.Math.E;//静态导入(精准导入,在类内使用时不需要在常量前面添加类名)

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class CompareTest {

    public static void main(String[] args) {

        String a="qwe";
        String b="qwe";

        City c1=new City();
        c1.setCityName("北京");
        City c2=new City();
        c2.setCityName("北京");
        System.out.println(E);



        System.out.println((a.compareTo(b)==0)==a.equals(b));

        ComposeTranform<String> c=new ComposeTranform<>(new Set<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        });
        c.add("asd");
        c.add("zxc");
        Collection<String> col= Lists.newArrayList();
        col.add("qwe");
        col.add("zxcsdfdasd");
        col.add("zxcsdfdasdasdas");
        c.addAll(col);
        System.out.println("添加次数："+c.getCount());



    }
}
