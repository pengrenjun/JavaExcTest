package com.ronglian.fssc.webapp.Annotation;

import org.activiti.engine.test.Deployment;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnooMain {

    public static void main(String[] args) {
        A a=new A();

        Class<A> c= A.class;

        try {
            Method mt=c.getMethod("Tf",new Class[]{});
            if(mt.isAnnotationPresent(FieldMeta.class)){
                FieldMeta fm=mt.getAnnotation(FieldMeta.class);
                String vlu1=fm.description();
                boolean ed=fm.editable();
                System.out.println("注解的值为  "+vlu1+" "+ed);
            }

            Annotation[] arr=mt.getAnnotations();
            for(Annotation anc:arr){
                System.out.println(anc.toString());
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}

class A {

    @FieldMeta(description = "测试方法",order = 1,editable=true)
    @SuppressWarnings("unused")
    @Deprecated
    @Deployment
    public void Tf(){
        System.out.println("asd");
    }
}


