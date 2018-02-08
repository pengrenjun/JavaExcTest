package com.ronglian.fssc.webapp.UtilsTest;

import java.beans.BeanInfo;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class BeanInsertUtil {



        private static void getSql(Class className,List<String> suffixs) throws Exception {
            try {
                //获得bean类
                BeanInfo beninfo = Introspector.getBeanInfo(className);
                //获得所有的属性描述
                PropertyDescriptor[] propertydescriptors = beninfo.getPropertyDescriptors();
                for(PropertyDescriptor pd:propertydescriptors){
                    //获得读写方法
                    System.out.println(pd.getName());
                    System.out.println(pd.getReadMethod().getName());//读
                    System.out.println(pd.getWriteMethod().getName());//写

                }
            } catch (IntrospectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /**通常需要将一个bean对象通过get方法插入到表中，本方法可以动态的生成sql语句
         * 将对象的sql语句生成
         * suffixs长度为空，则对所有的field进行插入
         * table为插入的表名
         * */
        public static String getInsertSql(Class className,List<String> suffixs,String table,String beanName) throws NoSuchFieldException{
            if("".equals(table)){
                System.out.println("表名为空");
                return null;
            }
            String sqlparam = "";
            String sql ="insert into "+table+"(sqlparam) values(";
            String bean = beanName;
            if("".equals(bean)){
                //获得类名并且将第一个字母转为小写
                int index = className.getName().lastIndexOf(".")+1;
                bean = className.getName().substring(index);
                bean = bean.substring(0, 1).toLowerCase()+bean.substring(1);
            }
            //获得这个类的所有方法
            Method[] methods = className.getMethods();
            //是否取所有的fields
            if(suffixs.size() == 0){
                //获得私有属性
                Field[] fields = className.getDeclaredFields();
                for(Field field:fields){
                    suffixs.add(field.getName());
                }
                //获得共有属性
                fields = className.getFields();
                for(Field field:fields){
                    suffixs.add(field.getName());
                }
            }
            System.out.println(suffixs);
            for(String suffix:suffixs)
            {
                for(Method method:methods){
                    //判断是否由get方法
                    if(method.getName().toLowerCase().equals("get"+suffix)){
                        try {
                            //Field field = className.getDeclaredField(suffix);
                            if (method.getReturnType() == java.lang.String.class){
                                sqlparam += suffix+",";
                                sql +="'\"+"+bean +"."+method.getName()+"()+\"',";
                            }else
                            if (method.getReturnType() == int.class)
                            {
                                sqlparam += suffix+",";
                                sql +="'+"+bean +"."+method.getName()+"()+',";
                            }

                        } catch (SecurityException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            sql = sql.replace("(sqlparam)", "("+sqlparam.substring(0, sqlparam.length()-1)+")");
            sql = "\""+sql.substring(0, sql.length()-1)+");\"";
            System.out.println(sql);
            return sql;
        }
    }

