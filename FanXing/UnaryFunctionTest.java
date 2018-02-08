package com.ronglian.fssc.webapp.FanXing;

import java.lang.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UnaryFunctionTest<T>{

   //泛型单例模式
    private static UnaryFunction<Object> unaryFunctionObj=new UnaryFunction<Object>() {
       @Override
       public Object apply(Object o) {
           return o;
       }
   };
    @SuppressWarnings("unchecked")
    public  static<T> UnaryFunction<T> getUnaryFunction(){

        //这块产生了未受检的转换检测,但因为原来的接口中的方法是直接复制返还,因此是安全的类型转换
        return (UnaryFunction<T>)unaryFunctionObj;
    }

    //要对列表内的元素进行比较等操作,类表内的元素需要继承Coparator接口,对类型参数进行限制
    //<T extends Comparable<T>> 说明List内的元素是可以进行compare比较的
    //<T extends Comparable<T>> 这种写法很常见,可以对T增加具体的操作方法
    public static <T extends Comparable<T>> T getMaxResult(List<T> list){

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
        //递归类型限制 在Comparator 的应用最为明显
        System.out.println(getUnaryFunction().apply(123));
        System.out.println(getUnaryFunction().apply("qwe"));

        List<String> strList= Arrays.asList("asd","zxczc","zxcdsfasdf");
        System.out.println(getMaxResult(strList));

        List<Integer> numList= Arrays.asList(123,234,3546354);
        System.out.println(getMaxResult(numList));



    }


}
