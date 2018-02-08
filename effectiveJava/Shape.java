package com.ronglian.fssc.webapp.effectiveJava;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//这个类用来表示圆和矩形,并提供计算面积的方法
public abstract class Shape {


         abstract double getArea();}



    class Rectange extends  Shape{
         @NotNull
         final double lng;
         @NotNull
         final double wit;

        public Rectange(double lng, double wit) {
            this.lng = lng;
            this.wit = wit;
        }

        @Override
        double getArea() {
            return lng*wit;
        }
    }


    class Circle extends  Shape{

         @NotNull
        final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }
        @Override
        double getArea() {
            BigDecimal bigDecimal=new BigDecimal(Math.PI*Math.pow(radius,2));
            bigDecimal=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
            return  bigDecimal.doubleValue();
        }
    }






