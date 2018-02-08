package com.ronglian.fssc.webapp.Annotation;

import com.ronglian.fssc.webapp.platform.domain.dxtfee.DxtFee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class B {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException {


            String path="com.ronglian.fssc.webapp.platform.domain.dxtfee.DxtFee";
            Class<DxtFee> clazz= (Class<DxtFee>) Class.forName(path);
            System.out.println("文件路径"+clazz.getName());
            Field[] fields=clazz.getDeclaredFields();//获得所有类的属性(私有，公有)
            for(Field f:fields){
                System.out.println(f);
            }

            Method[] methods=clazz.getDeclaredMethods();
            for(Method m:methods){
                System.out.println(m);
            }

        Constructor[]constructors=clazz.getConstructors();
        for(Constructor c:constructors){
            System.out.println("构造方法："+c);
        }
        /*//多态，参数不同得到不同的构造方法
        Constructor constructor=clazz.getConstructor(String.class,String.class);
        System.out.println("构造方法test"+constructor);*/

        //通过反射创建对象
        DxtFee dxtFee1=clazz.newInstance();

        Method mq=clazz.getDeclaredMethod("setBigBizName", String.class);

        //通过方法为对象赋值

        long st=System.currentTimeMillis();

        System.out.println(st);

        mq.setAccessible(true);

        mq.invoke(dxtFee1,"市场费预算");

        System.out.println("业务大类名称:"+dxtFee1.getBigBizName());

        Field fl=clazz.getDeclaredField("bigBizCode");

        fl.setAccessible(true);

        fl.set(dxtFee1,"12306");

        System.out.println("业务大类编码:"+dxtFee1.getBigBizCode());
















    }}



