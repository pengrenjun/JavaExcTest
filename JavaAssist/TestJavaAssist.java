package com.ronglian.fssc.webapp.JavaAssist;

import javassist.*;

import java.io.IOException;

public class TestJavaAssist {

    public static void main(String[] args) throws CannotCompileException, IOException {

        //加载类池 需要引入javassist的架包
        ClassPool pool=ClassPool.getDefault();

        CtClass cc=pool.makeClass("com.ronglian.fssc.webapp.JavaAssist.Emp");

        //创建属性 源码创建
        CtField ctf1=CtField.make("private String name;",cc);
        CtField ctf2=CtField.make("private  int age;",cc);

        cc.addField(ctf1);
        cc.addField(ctf2);

        //创建方法
        CtMethod ctm1=CtMethod.make("public String getName(){return name;}",cc);
        cc.addMethod(ctm1);


         //将创建的类存入"c:/javaAssistTest_01"
        cc.writeFile("c:/javaAssistTest_01");

    }
}
