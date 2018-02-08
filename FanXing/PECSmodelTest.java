package com.ronglian.fssc.webapp.FanXing;


import com.google.common.collect.Sets;
import com.sun.xml.bind.v2.schemagen.xmlschema.Union;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




//有限制通配符的使用,增强API的灵活性,解决参数类型匹配的问题
//生产者 ? extends E  消费者? super E
public class PECSmodelTest {

    //合并两个集合的数据  泛型方法
    //传入的参数作为生成者
    public  static  <T>  Set<T>  setunion(final Set<? extends T > set1, final Set<? extends  T>  set2){

        Set<T> unionSet= Sets.newHashSet(set1);
        unionSet.addAll(set2);
        return   unionSet;

    }
    //改进求容器最大值的方法
    /*  ①、首先输入参数List<T> list 是生产者。生产T实例，那么就应该变成List<? extends T> list

        ②、Comparable<T>是消费者。因为它获取T，并将T排序。那么就应该变成Comparable<? super T>*/
    public static <T extends Comparable<? super T>> T getMaxResult(List<? extends T> list){

        //结合lambada表达式
        return list.stream().max((x, y)->x.compareTo(y)).get();

        /*
        //list的迭代器
        Iterator<T> iterator=  list.iterator();
        T result=iterator.next();
        while (iterator.hasNext()){

            T t=iterator.next();
            if(t.compareTo(result)>0){
                result=t;
            }
        }

        return result;*/

    }

    public static void main(String[] args) {
        List<String> strList= Arrays.asList("asd","zxczc","zxcdsfasdf");
        System.out.println(getMaxResult(strList));
    }




}
