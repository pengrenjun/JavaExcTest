package com.ronglian.fssc.webapp.FanXing;


import com.google.common.collect.Sets;


import java.util.*;


//有限制通配符的使用,增强API的灵活性,解决参数类型匹配的问题
//生产者 ? extends E  消费者? super E
public class PECSmodelTest {

    //合并两个集合的数据  泛型方法
    //传入的参数作为生成者
    public  static  <T>  Set<T>  setunion(final Set<? extends T > set1, final Set<? extends  T>  set2){

        Set<T> unionSet= Sets.newHashSet(set1);
        unionSet.addAll(set2);
        return   unionSet;

    }
    //改进求容器最大值的方法
    /*  ①、首先输入参数List<T> list 是生产者。生产T实例，那么就应该变成List<? extends T> list

        ②、Comparable<T>是消费者。因为它获取T，并将T排序。那么就应该变成Comparable<? super T>*/
    public static <T extends Comparable<? super T>> T getMaxResult(List<? extends T> list){




        //list的迭代器
        //Iterator<T> iterator=  list.iterator();--这块需要修改,list.iterator()返回的是T的子类型迭代器,并没有返回T的迭代器,需要用有限制通配符进行转换
        Iterator<? extends T> iterator=  list.iterator();
        T result=iterator.next();//迭代器next()返回的是T的子类型,因此可以存在T中Z,不需要进行修改
        while (iterator.hasNext()){

            T t=iterator.next();
            if(t.compareTo(result)>0){
                result=t;
            }
        }

        return result;

    }


    //交换列表中被索引的项目
    //通配符(类型实参)和类型参数的双重性
    //类型参数写法
    public static<T> void swapa(List<T> list,int i,int j){
        list.set(i,list.set(j,list.get(i)));
   }
   //通配符的写法
    //如果类型参数在方法中只声明一次,就可以用通配符类取代类型形参
    public  static  void swapb(List<? > list, int i, int j){

       // list.set(i,list.set(j,list.get(i))); 直接这么写是编译不了的,因为list<?> ,不能把除了null之外的任何值放进list<?>中
        //解决方法:编写私有的辅助方法来捕捉通配符的类型,为了捕捉通配符类型,辅助方法必须是泛型方法
        SwapHelper(list,i,j);
    }
    //私有的辅助方法来捕捉通配符的类型
    /**
     * 这个方法就是知道从列表取出的数据都是具体的E类型,并且知道将具体的E类型放进列表中是安全的,可以实现比较好的通配符的方法声明,增强了API的灵活性,
     * 同时在其内部通过泛型E方法完成复杂化的处理,对于客户端是很有帮助的
     * */
    private static<E> void SwapHelper(List<E> list,int i,int j){
        list.set(i,list.set(j,list.get(i)));
    }




    public static void main(String[] args) {
        List<String> strList= Arrays.asList("asd","zxczc","zxcdsfasdf");
        System.out.println(getMaxResult(strList));
    }




}
