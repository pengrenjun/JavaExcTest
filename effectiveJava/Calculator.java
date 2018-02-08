package com.ronglian.fssc.webapp.effectiveJava;

public class Calculator {

    //公有静态成员类
    public enum option{

        PLUS,MINUTE,MUTIPLY,DIVIDE;

        double apply(double x,double y){

            switch (this){


                case PLUS:return x+y;
                case MINUTE:return x-y;
                case MUTIPLY:return x*y;
                case DIVIDE:return x/y;
            }

            throw  new AssertionError("UNDEFINED OP"+this);

        }

        public static void main(String[] args) {

            System.out.println(option.MUTIPLY.apply(3,2));
        }
    }
}
