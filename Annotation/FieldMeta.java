package com.ronglian.fssc.webapp.Annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.TYPE})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface FieldMeta {

    /**
     * 是否为序号列
     * @return
     * */
    boolean id() default false;

    /**
     * 字段名称
     * @return
     * */
     String name() default "";

     /**
      * 是否为可编辑字段
      * @return
      **/

     boolean editable() default true;

     /**
      * 是否在列表中显示
      * @return
      * */
     boolean visible() default true;

     /**
      * 字段描述
      * @return
      * */
     String description() default "";

    /**
     * 排序字段
     * * @return
     */
     int order() default 0;




}
