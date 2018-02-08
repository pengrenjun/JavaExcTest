package com.ronglian.fssc.webapp.UtilsTest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ronglian.fssc.webapp.platform.domain.payment.TFormEmpECApp;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class CollectionExp {

    public static void main(String[] args) {

        Collection coll=new ArrayList<>();
        coll.add(123);
        coll.add(345);

        TFormEmpECApp app1=new TFormEmpECApp();
        app1.setStatus("1");
        app1.setGlDate(new Date());

        TFormEmpECApp app2=new TFormEmpECApp();
        app2.setStatus("2");
        app2.setGlDate(new Date());

        Collection coll2=new ArrayList<>();


        coll2.add(app1);
        coll2.add(app2);


        List list= Lists.newArrayList();
        list.add(app1);
        list.add(app2);

        String res= null;
        try {
            res = CollectionToString(coll,"\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(res);


    }

    /**
     * 将集合转换为字符串
     * @param coll       传入的集合
     * @param splitFlag
     *
     * */
    public static <T> String CollectionToString(Collection<T> coll,String splitFlag) throws Exception {

        StringBuffer str=new StringBuffer();

        int size=coll.size();
        int i=0;

        //1.判断传入的集合是否有值

        if(CollectionUtils.isEmpty(coll)){
            throw new Exception("传入的集合为空");
        }

        //2.遍历集合，进行字符串拼接

        for(Iterator<T> ite=coll.iterator();ite.hasNext();){


            str.append(JSONObject.toJSONString(ite.next()));
            i++;
            if(i<size){ str.append(splitFlag);}
        }



        return str.toString();
    }
}
