package com.ronglian.fssc.webapp.JavaScriptEmg;

import com.ronglian.fssc.webapp.platform.domain.dxtfee.DxtFee;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;


public class JsEmg {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
        //JS引擎
        ScriptEngineManager sem= new ScriptEngineManager();
        //JS引擎对象
        ScriptEngine seg=sem.getEngineByName("javascript");

        DxtFee dxtFee=new DxtFee();
        dxtFee.setId("123456");

        seg.put("po",dxtFee);

        DxtFee dxtFee1= (DxtFee) seg.get("po");

        System.out.println(dxtFee1.getId());

        //定义函数
        String func1="function add(a,b,c){ if(a<0){return '第一个参数>=0';} if(c>=b&&c>=a){" +
                "return 'c的值不能同时大于其余两个值';} var sum=a+b+c; return sum;} ";
        seg.eval(func1);
        //引擎接口
        Invocable inv=(Invocable)seg;
        //执行脚本中的方法
        try {
            Object result=inv.invokeFunction("add",new Object[]{5,1,1});
            System.out.println("引擎方法计算结果: "+result);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

      /*//执行js文件
        URL url= JsEmg.class.getClassLoader().getResource("a.js");
        try {
            FileReader fr=new FileReader(url.getPath());
            seg.eval(fr);
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/




    }


}
