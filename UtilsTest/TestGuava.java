package com.ronglian.fssc.webapp.UtilsTest;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.si.framework.domain.security.TSysUser;
import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.ronglian.fssc.webapp.core.enums.BankBillEnum;
import com.ronglian.fssc.webapp.platform.utils.wx.StringUtils;

import java.util.*;

public class TestGuava {

    public static void main(String[] args) {
//        test1();//*只读设置(创建只读list)*//*
          test2();//*list过滤器 转化器 组合式编程*//*
//        test3();
//        test4();//*集合的操作:交集、并集、差集*//*
//        test5();//*Multiset集合 可存放重复数据*//*
//        test6();//*Multimap *//*
//        test7();//*双向Map ：BiMap 键和值都不能重复
       /* String userInput ="HJDSFKJ123_ASDF-ASD123SDZSD5683094";
        CharMatcher ID_MATCHER =CharMatcher.DIGIT.or(CharMatcher.is('-'));
        System.out.println(ID_MATCHER.retainFrom(userInput));*/

       // ClassToInstanceMapTest();//对象作为key进行查找


        /**
         * 排序器[Ordering]是Guava流畅风格比较器[Comparator]的实现，
         * 它可以用来为构建复杂的比较器，以完成集合排序的功能。
         * 从实现上说，Ordering实例就是一个特殊的Comparator实例。
         * Ordering把很多基于Comparator的静态方法（如Collections.max）包装为自己的实例方法（非静态方法），
         * 并且提供了链式调用方法，来定制和增强现有的比较器。
         * */
       // OrderingTestA();

        /**
         * 它其实是一个抽象类，其提供了主要有三个抽象方法，
         * start()用于返回内部的一个 ComparisonChain实现；
         * 重载了许多compare()方法，用于接收各种类型的参数，
         * compare方法返回的仍然是 ComparisonChain对象；result()方法用于返回比较后的结果。
         * */

       // ComparisonChainTest();

    }




    /**
     * 只读设置
     * */
    public static void  test1(){
        //初始化list immuablelist只可读
        List<String>  immuablelist= ImmutableList.of("a","b","c");
        //java.lang.UnsupportedOperationException
        immuablelist.add("qwe");
    }

    /**
     * 函数式编程
     * 1、predicate 谓语
     * 2、function  方法
     * 工具：Collections2.filter    过滤器
     *      Collections2.transform 转换器
     *      Functions.compose      组合式编程
     *
     *
     * */
    public static void test2(){
        //初始化 list
        List<String> filteList= Lists.newArrayList("a","bc","qwe","asdzxc","zxcvbndf");
        //对list进行过滤,筛选出字符串包含a的字符串
        Collection<String> relist= Collections2.filter(filteList, new Predicate<String>() {

         //匿名内部类对象
            @Override
            public boolean apply(String input) {
                //业务逻辑
                if(input.contains("a")){
                    return  true;
                }else{
                    return  false;
                }
            }});

        for(String res:relist){
            System.out.println(
                    "过滤："+res
            );
        }



        //数据转换 将枚举的value转化为name
        Set<String> enumvalueSet= Sets.newHashSet();
        enumvalueSet.add(BankBillEnum.BANK_WAY.getValue());
        enumvalueSet.add(BankBillEnum.BILL_WAY.getValue());
        enumvalueSet.add(BankBillEnum.WHOLESALE.getValue());

        Collection<String> enumname=Collections2.transform(enumvalueSet, new Function<String, String>() {
            @Override
            public String apply(String s) {
                //业务逻辑
               return BankBillEnum.getBankBillEnumName(s);
            }
        });

        for(String req:enumname){
            System.out.println(
                   "转化："+ req
            );
        }


        //组合式函数编程 在list中挑选出长度大于等于3的字符串,并且将其转化为大写
        //function_1
        Function<String,String > function_1=new Function<String,String >() {

            @Override
            public String apply(String s) {
                return s.length()>=3?s:"";
            }
        };

        //function_2
        Function<String,String> function_2=new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        //组合两个函数
        Function<String,String> function= Functions.compose(function_1,function_2);
        Collection<String> composeList=Collections2.transform(filteList,function);
        for(String comreq:composeList){
            if(StringUtils.isNotEmpty(comreq)){
                System.out.println(
                        "组合："+comreq
                );
            }

        }
    }


    /**
     * 创建约束
     *
     * */

    public static void test3(){
        //创建set集合
        Set<String> set= com.google.common.collect.Sets.newHashSet();
        //创建约束(对set集合内的String进行约束

    }
    /**
     * 集合的操作:交集、并集、差集
     * */
    public static void test4(){

        Set<Integer> set1= com.google.common.collect.Sets.newHashSet(1,2,3,4,5);
        Set<Integer> set2= com.google.common.collect.Sets.newHashSet(1,2,4,5,6,7,8,9);

        //交集
        Sets.SetView<Integer> intersectionSet=Sets.intersection(set1,set2);

        //并集
        Sets.SetView<Integer> unionSet =Sets.union(set1,set2);

        //差集

        Sets.SetView<Integer> differenceSet =Sets.difference(set1,set2);

        System.out.println(
                "交集"+ JSONObject.toJSONString(intersectionSet)
        );

        System.out.println(
                "并集"+ JSONObject.toJSONString(unionSet)
        );

        System.out.println(
                "差集"+ JSONObject.toJSONString(differenceSet)
        );
    }

    /**
     * 统计单词在一句话中出现的次数
     * HashMultiset.create() 创建 Multiset集合
     * mulSet.elementSet()   将Multiset集合转为Set集合
     * mulSet.count(strReq)  统计数据在集合中出现的次数
     * */
    public static void  test5(){
        String str="Guava is a set of core libraries that includes new collection types (such as multimap and multiset), immutable collections, a graph library, functional types, an in-memory cache, and APIs/utilities for concurrency, I/O, hashing, primitives, reflection, string processing, and much more!";

        //将str按空格分割 存入Multiset中进行统计
        String[] strArr=str.split(" ");
        Multiset<String> mulSet=HashMultiset.create();
        //无序可重复的存入数据
        for(String st:strArr){
            mulSet.add(st);
        }
        //将数据不重复存入set集合中
        Set<String> set=mulSet.elementSet();

        for(String strReq:set){

            System.out.println(
                    strReq+"-->"+  mulSet.count(strReq)
            );
        }
    }


    /**
     * Multimap的key可以重复 查看一个公司对应几个银行账号
     *
     *  ArrayListMultimap.create()  创建Multimap
     *  multimap.asMap() 将Multimap转换为Map<k,Collection<V>>
     *  遍历Multimap 就是遍历Map<k,Collection<V>> 取得key和value(Collection)
     *  ListMultimap中的静态方法Multimaps.asMap()得到一个Map<K, List<V>类型的数据；
        SetMultimap和SortedSetMultimap也类似
     * */
    public static void test6(){

        Map<String,String> hashMap= new HashMap<>();

        hashMap.put("123qwe","北京公司");
        hashMap.put("234asd","北京公司");
        hashMap.put("w34asda2","南京公司");
        hashMap.put("ieoo89082","上海公司");
        hashMap.put("3453zdsfsd","上海公司");
        hashMap.put("ieoo891230982","南京总公司");

        //创建Multimap
        Multimap<String,String> multimap=ArrayListMultimap.create();
        //遍历Map集合，将hashMap的key存放在Multimap的Value处，value存放在Multimap的key处
        for(String key:hashMap.keySet()){
            multimap.put(hashMap.get(key),key);
        }

       /*** 查看Multimap 可以利用asMap()方法来得到一个 Map<K, Collection<V>>类型的数据
         （或者利用ListMultimap中的静态方法Multimaps.asMap()得到一个Map<K, List<V>类型的数据；
        SetMultimap和SortedSetMultimap也类似）；*/
        Map<String, Collection<String>> map1 = multimap.asMap();
        for (Map.Entry<String, Collection<String>> entry : map1.entrySet()) {
            String key = entry.getKey();
            Collection<String> coll=entry.getValue();
            System.out.println(
                    key+"-->"+JSONObject.toJSONString(coll)
            );
        }}



        /**
         * 双向Map ：BiMap 键和值都不能重复
         * 通过用户找邮箱
         * 通过邮箱找用户
         * */
        public static void test7(){

            BiMap<String,String> biMap=HashBiMap.create();
            biMap.put("用户1","123@qq.com");
            biMap.put("用户2","234@qq.com");
            biMap.put("用户3","456@qq.com");

            String mail=biMap.get("用户1");//用户作为key
            String user=biMap.inverse().get("456@qq.com");//邮箱作为key

            System.out.println(mail);
            System.out.println(user);

    }

    public static void ClassToInstanceMapTest() {

        ClassToInstanceMap<Object> classToInstanceMapObj =MutableClassToInstanceMap.create();
        TSysUser persona= new TSysUser();
        persona.setUserName("小李");

        classToInstanceMapObj.putInstance(TSysUser.class,persona);
        classToInstanceMapObj.putInstance(String.class,"qwe");

        TSysUser person=classToInstanceMapObj.getInstance(TSysUser.class);
        System.out.println(person.getUserName());
        String str=classToInstanceMapObj.getInstance(String.class);
        System.out.println(str);

    }

    /**
     * 创建排序器：常见的排序器可以由下面的静态方法创建

     natural() 对可排序类型做自然排序，如数字按大小，日期按先后排序
     usingToString() 按对象的字符串形式做字典排序[lexicographical ordering]
     from(Comparator) 把给定的Comparator转化为排序器

     链式调用方法：通过链式调用，可以由给定的排序器衍生出其它排序器

     reverse() 获取语义相反的排序器
     nullsFirst() 使用当前排序器，但额外把null值排到最前面。
     nullsLast() 使用当前排序器，但额外把null值排到最后面。
     compound(Comparator) 合成另一个比较器，以处理当前排序器中的相等情况。
     lexicographical() 基于处理类型T的排序器，返回该类型的可迭代对象Iterable的排序器。
     onResultOf(Function) 对集合中元素调用Function，再按返回值用当前排序器排序。
     *
     * */
    private static void OrderingTestA() {

        //多参数对集合内的对象进行排序

        City city1=new City(100000000,1,"Beijing");
        City city2=new City(100000000,2,"ShangHai");
        City city3=new City(100000000,3,"LiaoYang");
        City city4=new City(10000000,2,"ShenZhen");
        City city5=new City(10000,7,"AB");

        List<City> cityList=Lists.newArrayList();
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        cityList.add(city4);
        cityList.add(city5);



        //按城市人口进行排序
        Ordering<City> ordering1 = Ordering.natural().onResultOf(
                new Function<City, Integer>() {
                    public Integer apply(City foo) {
                        return foo.getPeopleNum();
                    }
                });

        List<City> cityList2 = ordering1.sortedCopy(cityList);
        System.out.println(JSONObject.toJSONString(cityList2));

        //按城市受欢迎程度进行排序
        Ordering<City> ordering2 = Ordering.natural().onResultOf(
                new Function<City, Integer>() {
                    public Integer apply(City foo) {
                        return foo. getPopularity();
                    }
                });

        Ordering<City> orderingComPand=ordering1.compound(ordering2);
        List<City> cityList3 = orderingComPand.sortedCopy(cityList);
        System.out.println(
                JSONObject.toJSONString(cityList3)
        );



        /**
         * 通过将jdk比较器转换为google 的排序器 对集合的对象进行排序
         * */

        //将比较器转换为排序器
        Ordering<City> cityOrdering=Ordering.from(new CityByPeopleNumComparator());
        Collections.sort(cityList, cityOrdering);
        System.out.println(
                "比较器转换为排序器:"+JSONObject.toJSONString(cityList)
        );
        //将多个排序比较器进行结合
        cityOrdering.compound(new CityByPopluationComparator());
    }

    //编写jdk 比较器
    //按欢迎程度进行比较
    public static class CityByPopluationComparator implements Comparator<City> {

        @Override
        public int compare(City city1, City city2) {
            return Ints.compare(city1.getPeopleNum(), city2.getPeopleNum());
        }



    }

    //按人数进行比较
    public static class CityByPeopleNumComparator implements Comparator<City> {

        @Override
        public int compare(City city1, City city2) {
            return Ints.compare(city1.getPeopleNum(), city2.getPeopleNum());
        }


    }




    /**
     * 通过ComparisonChain的链式比较方法，实现对Odering排序器的代码简化
     * 将jdk的Collections.sort方法与google的ComparisonChain相结合,实现对集合内部对象的多参排序
     *
     * 源码分析：
     * ACTIVE和LESS GREATER 都继承了ComparisonChain，ACTIVE是比较器，
     * LESS或者GREATER是单纯的传递器，向后传递结果。如果equal，返回一个比较器，后面继续进行比较，
     * 如果not equal，则返回一个传递器，直接把结果传递到最后执行result
     *
     *  ComparisonChain classify(int result) {
     return result < 0 ? ComparisonChain.LESS : (result > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE);
     }
     * */
    private static void ComparisonChainTest() {

        //对城市先按人数排序,再按欢迎程度排序
        City city1=new City(100000000,1,"Beijing");
        City city2=new City(100000000,2,"ShangHai");
        City city3=new City(100000000,3,"LiaoYang");
        City city4=new City(10000000,2,"ShenZhen");
        City city5=new City(10000,7,"AB");

        List<City> cityList=Lists.newArrayList();
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        cityList.add(city4);
        cityList.add(city5);

        //调用排序方法
       Collections.sort(cityList, new Comparator<City>() {
           @Override
           public int compare(City o1, City o2) {
               return ComparisonChain.start().
                       compare(o1.getPeopleNum(),o2.getPeopleNum()).
                       compare(o1.getPopularity(),o2.getPopularity())
                       .result();
           }
       });

        System.out.println(
                JSONObject.toJSONString(cityList)
        );


    }






}




