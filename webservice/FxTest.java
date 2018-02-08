package com.ronglian.fssc.webapp.webservice;

/*
* 泛型类
* */
public class FxTest<T> {

    private  T t1;
    private T t2;

    public FxTest(T obj1,T obj2){
        this.t1=obj1;
        this.t2=obj2;
    }


    public T getT1() {
        return t1;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }

    public T getT2() {
        return t2;
    }

    public void setT2(T t2) {
        this.t2 = t2;
    }
}
