package com.ronglian.fssc.webapp.FanXing.类型安全的异构容器;

import com.ronglian.fssc.webapp.UtilsTest.City;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class IsomericContainer {


    /*
    * 类型安全的异构容器
    *
    **/
     static class MapContainer{
         //这个Map集合的创建是将键进行了参数化,而不是整个Map容器进行参数化,从而实现异构的目的(键可以为多种类型)
        //以Class<?>作为键,来表示类型信息
         private Map<Class<?>,Object> map=new HashMap<Class<?>, Object>();

         //这个put方法其实是通过类型参数T来实现对通配符？的检测,这种是通配符？应用的常用技巧
         //通过Class<T> 实现了对instance的类型限制 实现了类型安全的目的
         /** private <T extends Number > void put(Class<T> classtype,T instance ){
         if(classtype==null){
         throw  new NullPointerException("class Type is null");
         }
         map.put(classtype,classtype.cast(instance));//进一步确保键值相同的数据类型,一些集合的包装类也采用了通用的方法,用来检测数据类型
         }


         <T extends Number >可以实现对传入类型的限制
         * */
         private <T > void put(Class<T> classtype,T instance ){
                       if(classtype==null){
                           throw  new NullPointerException("class Type is null");
                       }
             map.put(classtype,classtype.cast(instance));//进一步确保键值相同的数据类型,一些集合的包装类也采用了通用的方法,用来检测数据类型
         }

         /**
          * 这个从Map中取值的方法利用了Class的cast方法实现了将对象的动态转换,cast可以动态检测对象是否为指定的类型数据
          * 通过动态检测,进一步确定键值是相同的数据类型
          * */

         private <T> T getMapValue(Class<T> classtype ){

             return classtype.cast(map.get(classtype)) ;
         }

    }


    public static void main(String[] args) {
        /*Object a=Integer.class;
        System.out.println(a);

        Object o2= City.class;
        System.out.println(o2);*/

        MapContainer  container=new MapContainer();
        container.put(Integer.class,123123);
        container.put(String.class,"asda");

        Integer integerValue=container.getMapValue(Integer.class);
        String strValue=container.getMapValue(String.class);
        System.out.println(integerValue+"  "+strValue);

    }
}
