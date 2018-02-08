package com.ronglian.fssc.webapp.UtilsTest;


//复合转发代替继承，创建类(组件化)

import com.google.common.collect.Lists;
import org.apache.commons.collections.keyvalue.AbstractMapEntry;
import org.junit.Before;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/***
 * 包装类
 * 创建新的集合，可以用来统计向集合中添加数据的次数
 */
public class ComposeTranform<E>   extends  ComposeSet<E>{

    //AbstractMapEntry<K, V>
    List a= Lists.newArrayList();



     //创建用来统计添加次数的变量
     int count ;

    public ComposeTranform(Set<E> set) {
        super(set);
    }

    public boolean add(E e) {

        count++;
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        count+=c.size();
        return false;
    }

    public int getCount() {
        return count;
    }
}
//创建组件
class ComposeSet<E> implements Set<E> {

    //需要建立私有域,为其建立构造方法

    private final Set<E>  set;

    public ComposeSet(Set<E> set) {
        this.set = set;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}


