package com.ronglian.fssc.webapp.FanXing;

//定义数据验证接口
public interface ValidataInterface<T> {

    boolean yzAddr(T t);
    boolean yzInput(T t);

}
