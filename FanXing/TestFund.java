package com.ronglian.fssc.webapp.FanXing;

import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.platform.manager.common.StringUtil;
import com.ronglian.fssc.webapp.webservice.FxTest;
import com.ronglian.fssc.webapp.webservice.TypeTest;
import oracle.sql.NUMBER;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;


import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestFund<T> {

    @Autowired
    private  ProcessEngine processEngine;

    @Test
    public  void startProInstance( )  {

      /*List arr= Lists.newArrayList();

      arr.add("123");
      arr.add(123);

      for(Object a:arr){
          String syso=(String)a;

          System.out.println(
                  syso
          );
      }*/


      /*  List<String> arr1= Lists.newArrayList();

        arr1.add("123");
        arr1.add("String");
        *//*arr1.add(123);*//*

        for(Object a:arr1){
            String syso=(String)a;

            System.out.println(
                    syso
            );
        }
        */
      /*  List<String> stringClass=Lists.newArrayList();
        List<Integer> integerClass=Lists.newArrayList();

        Class n1=stringClass.getClass();
        Class n2=integerClass.getClass();

        System.out.println("n1："+n1+"  n2:"+n2);
*/

     /*Fx<String>  c1=new Fx<String>("123","456");

     String var11=c1.getVar1();
     String var22=c1.getVar2();

        System.out.println(var11+" "+var22);


        Fx c2= new Fx("String_v",123);
        System.out.println(c2.getVar1()+"  "+c2.getVar2());

        Fx c3=new Fx(c1,c2);
        System.out.println((Fx)c3.getVar1());
    }
*/
      Fx<Integer> fx1=new Fx<>(1,2);
      Fx<Number>  fx2=new Fx<>(12,15);

     /* getnum(fx1);
      getnum(fx2);



     List ARR= fx1.printMesaage(123,345,678);

     for(Object lp:ARR){
         System.out.println(lp);
     }


        Fx fx3=new Fx("string_v",234567);

        List arr=fx3.printMesaage("string_v",234567);
        for(Object lp:arr){
            System.out.println(lp);
        }*/

        Fg  fg1=new Fg(123,"qqwesad");

        Object sup=Fg.getMessage(123,345);
        System.out.println(sup);


        List<?>[] ls2 = new ArrayList<?>[10];
        List<String>[] ls = new ArrayList[10];

}




    public void getnum(Fx<?> var_obj){

        System.out.println(var_obj.getVar1()+" "+var_obj.getVar2());
    }


}
class Fx<T>{
    private T var1;

    private T var2;

    public List<T> printMesaage(T...args){
        List<T> list=Lists.newArrayList();
       for(T t:args){
           System.out.println("传入的参数："+t);
           list.add(t);
       }
       return  list;
    }

    public <T,K> T getDoubleMesaage(Fx<T> n1,Fx<K> n2){
        return null;
    }

    public Fx(T var1,T var2){
        this.var1=var1;
        this.var2=var2;
    }

    public T getVar1() {
        return var1;
    }

    public void setVar1(T var1) {
        this.var1 = var1;
    }

    public T getVar2() {
        return var2;
    }

    public void setVar2(T var2) {
        this.var2 = var2;
    }
}

class  Fg<T> extends  Fx{

    public Fg(T var1, T var2) {
        super(var1, var2);
    }
    @SuppressWarnings("unchecked")
    public static<T > T  getMessage(T var1, T var2) {

        if (var1 instanceof Integer && var2 instanceof Integer) {
            int tmp = (Integer)var1 + (Integer) var2;
            String result = String.valueOf(tmp);
            return (T) result;
        } else if (var1 instanceof String && var2 instanceof String) {
            return (T) ((String) var1 + (String) var2);
        } else {
            throw new RuntimeException("Cant use sum for the type!");
        }
    }
 


}




