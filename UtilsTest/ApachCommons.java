package com.ronglian.fssc.webapp.UtilsTest;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.si.framework.domain.security.TSysUser;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.Lists;
import com.google.common.collect.MutableClassToInstanceMap;
import com.ronglian.fssc.webapp.platform.domain.dxttransitrmbbackbill.TransitRmbBackBill;
import org.apache.commons.collections.*;
import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.collections.bag.TreeBag;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.commons.collections.bidimap.DualTreeBidiMap;
import org.apache.commons.collections.buffer.CircularFifoBuffer;
import org.apache.commons.collections.buffer.UnmodifiableBuffer;
import org.apache.commons.collections.functors.*;
import org.apache.commons.collections.list.PredicatedList;
import org.apache.commons.collections.map.HashedMap;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


public class ApachCommons {


    public static void main(String[] args) {

//        testa();/*Predicate 断言 */
//        testb();/*Transformer */
//        testc();/*Closure：闭包：将业务逻辑处理封装 */
//        testd();/*IfClosure:二选一操作 predicate与ifClosure相结合*/
//        testf();/*whileClosure*/
//        testg();/*链式操作 ChainClosure*/
//        testh();/*集合操作 并集 交集 差集*/
//        testq();/*CircularFifoBuffer*/
//        testw();/*Map迭代器的扩展*/
//        teste();/*双向Map*/
//        testr();/*Bag 可存放重复的数据*/


    }

    /**
     * 函数式编程
     * Predicate 断言
     * 封装条件或者判别式if-else的替代
     * 相等：EqualPredicate 非空：NotNullPredicate 唯一性：UniquePredicate
     * 自定义：   @Override
                 public boolean evaluate
       组合：PredicateUtils.allPredicate(Collection<Predicate>)
     */
    public static void testa() {

        //字符串相等判断
        EqualPredicate equalPredicate = new EqualPredicate("qwe");
        boolean bl = equalPredicate.evaluate("qwe");
        System.out.println("相等判断：" + bl);


        //null判断
        Predicate notnullPredicate = NotNullPredicate.INSTANCE;
        boolean bll = notnullPredicate.evaluate(null);
        System.out.println("null判断：" + bll);

        List list = PredicatedList.decorate(Lists.newArrayList(), notnullPredicate);

        try {
            list.add(null);
        } catch (Exception e) {
            System.out.println("不能存入null值");
        }

        //唯一性非null判断
        Predicate uniquePredicate = UniquePredicate.getInstance();
        List uniquelist = PredicatedList.decorate(list, uniquePredicate);


        //自定义判断方法(自定义迭代器)
        Predicate defp = new Predicate() {
            @Override
            public boolean evaluate(Object o) {

                return o.toString().length() > 9 ? true : false;
            }
        };

        //将若干判断条件进行组合
        Collection<Predicate> pcoll= Lists.newArrayList();
        //组合的顺序就是检测的顺序
        pcoll.add(notnullPredicate);
        pcoll.add(uniquePredicate);
        pcoll.add(defp);
        Predicate predicate= PredicateUtils.allPredicate(pcoll);

        List<String> alist=PredicatedList.decorate(Lists.newArrayList(),predicate);
        alist.add("qqw");

    }


    /**
     *函数式编程：Transformer类型转换
     *将Long转换为日期类型
     * 数据与转换器结合：
     * CollectionUtils.collect(list,transformer)
     */

    public static void testb(){

      //编写类型转换器
     Transformer transformer=new Transformer() {
         @Override
         public Object transform(Object o) {
             return new SimpleDateFormat("yyyy-MM-dd").format(o);
         }
     };
     //初始化数据
        List<Long> list=Lists.newArrayList();
        list.add(100000000l);
        list.add(188299999l);

        //将数据与转换器结合 完成数据转换
        Collection<String> tranList=CollectionUtils.collect(list,transformer);

        System.out.println(JSONObject.toJSONString(tranList));

    }

    /**
     * Closure：闭包：将业务逻辑处理封装
     *  CollectionUtils.forAllDo(容器,功能处理类);
     * */
    public static void testc(){

        //将员工的等级转为中文显示
        TSysUser user1=new TSysUser();
        TSysUser user2=new TSysUser();
        TSysUser user3=new TSysUser();
        user1.setUserName("小王");user1.setEmpLevel("1");
        user2.setUserName("小李");user2.setEmpLevel("2");
        user3.setUserName("小赵");user3.setEmpLevel("3");
        List<TSysUser> userlist=Lists.newArrayList();
        userlist.add(user1);
        userlist.add(user2);
        userlist.add(user3);
        //封装处理流程
        Closure clos=new Closure() {
            @Override
            public void execute(Object o) {
               TSysUser user=(TSysUser) o;

                        if(user.getEmpLevel()=="1"){
                            user.setEmpLevel("一级员工");
                        }
                        if(user.getEmpLevel()=="2"){
                            user.setEmpLevel("二级员工");
                        }
                        if(user.getEmpLevel()=="3"){
                            user.setEmpLevel("三级员工");
                        }
                        else{
                            user.setEmpLevel("特级员工");
                        }
            }
        };
        //调用封装的处理流程
        CollectionUtils.forAllDo(userlist,clos);

        System.out.println(
                JSONObject.toJSONString(userlist)
        );

    }

    /**
     * IfClosure:二选一操作 predicate与ifClosure相结合
     * 在途货币资金申请单:如果实收总金额>=银行手续费用 将实际收款金额设为二者的差;若 实收总金额<银行手续费用
     * 将银行手续费用设为实收总金额*0.9  将实际收款金额设为二者的差
     * */
    public static void testd(){

        TransitRmbBackBill tr1=new TransitRmbBackBill();
        TransitRmbBackBill tr2=new TransitRmbBackBill();

        tr1.setTotalAmount(BigDecimal.valueOf(1000));
        tr1.setBankfeeAmount(BigDecimal.valueOf(200));

        tr2.setTotalAmount(BigDecimal.valueOf(2000));
        tr2.setBankfeeAmount(BigDecimal.valueOf(2300));

        List<TransitRmbBackBill> trList=Lists.newArrayList();
        trList.add(tr1);
        trList.add(tr2);

        //编写判别器
        Predicate predicate=new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                TransitRmbBackBill transitRmbBackBill=(TransitRmbBackBill)o;
                BigDecimal actualAmount=transitRmbBackBill.getTotalAmount().subtract(transitRmbBackBill.getBankfeeAmount());
                return actualAmount.compareTo(BigDecimal.ZERO)==1?true:false;
            }
        };

        //编写不同的处理流程
        Closure cl1=new Closure() {
            @Override
            public void execute(Object o) {
                TransitRmbBackBill obj=(TransitRmbBackBill)o;
                obj.setActulPaymentAmount(obj.getTotalAmount().subtract(obj.getBankfeeAmount()));
            }
        };

        Closure cl2=new Closure() {
            @Override
            public void execute(Object o) {
                TransitRmbBackBill obj=(TransitRmbBackBill)o;
                obj.setBankfeeAmount(obj.getTotalAmount().multiply((BigDecimal.valueOf(0.9))));
                obj.setActulPaymentAmount(obj.getTotalAmount().subtract(obj.getBankfeeAmount()));
            }
        };

        //将判别式和不同的处理流程相结合
        Closure ifClosure= IfClosure.getInstance(predicate,cl1,cl2);

        //处理数据

        CollectionUtils.forAllDo(trList,ifClosure);
        System.out.println(
                JSONObject.toJSONString(trList)
        );


    }

    /**
     * whileClosure
     * 循环执行操作
     * 确保在途货币资金的实际收款金额>10000
     * */
    public static void testf(){

        TransitRmbBackBill tr1=new TransitRmbBackBill();
        TransitRmbBackBill tr2=new TransitRmbBackBill();

        tr1.setTotalAmount(BigDecimal.valueOf(1000));
        tr1.setBankfeeAmount(BigDecimal.valueOf(200));
        tr1.setActulPaymentAmount(BigDecimal.valueOf(800));

        tr2.setTotalAmount(BigDecimal.valueOf(2000));
        tr2.setBankfeeAmount(BigDecimal.valueOf(2300));
        tr2.setActulPaymentAmount(BigDecimal.valueOf(-300));

        List<TransitRmbBackBill> trList=Lists.newArrayList();
        trList.add(tr1);
        trList.add(tr2);

        //编写判别器
        Predicate predicate=new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                TransitRmbBackBill obj=(TransitRmbBackBill)o;
                return obj.getActulPaymentAmount().compareTo(BigDecimal.valueOf(10000))==1?false:true;
            }
        };
        //执行流程
        Closure closure=new Closure() {
            @Override
            public void execute(Object o) {
                TransitRmbBackBill obj=(TransitRmbBackBill)o;
                obj.setTotalAmount(obj.getTotalAmount().multiply(BigDecimal.valueOf(1.2)));
                obj.setActulPaymentAmount(obj.getTotalAmount().subtract(obj.getBankfeeAmount()));
            }
        };

        //将判别式与执行流程结合
        Closure whileClosure= WhileClosure.getInstance(predicate,closure,false);

        //处理数据
        CollectionUtils.forAllDo(trList,whileClosure);

        System.out.println(
                JSONObject.toJSONString(trList)
        );

    }
    /**
     * 链式操作
     * ChainClosure
     * a.如果实收总金额>=银行手续费用 将实际收款金额设为二者的差;若 实收总金额<银行手续费用
     * 将银行手续费用设为实收总金额*0.9  将实际收款金额设为二者的差
     * b.确保在途货币资金的实际收款金额>10000
     * */
    public static  void testg(){

        TransitRmbBackBill tr1=new TransitRmbBackBill();
        TransitRmbBackBill tr2=new TransitRmbBackBill();

        tr1.setTotalAmount(BigDecimal.valueOf(1000));
        tr1.setBankfeeAmount(BigDecimal.valueOf(200));
        tr1.setActulPaymentAmount(BigDecimal.valueOf(800));

        tr2.setTotalAmount(BigDecimal.valueOf(2000));
        tr2.setBankfeeAmount(BigDecimal.valueOf(2300));
        tr2.setActulPaymentAmount(BigDecimal.valueOf(-300));

        List<TransitRmbBackBill> trList=Lists.newArrayList();
        trList.add(tr1);
        trList.add(tr2);

        //编写判别器
        Predicate predicate=new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                TransitRmbBackBill transitRmbBackBill=(TransitRmbBackBill)o;
                BigDecimal actualAmount=transitRmbBackBill.getTotalAmount().subtract(transitRmbBackBill.getBankfeeAmount());
                return actualAmount.compareTo(BigDecimal.ZERO)==1?true:false;
            }
        };

        //编写判别器
        Predicate predicateB=new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                TransitRmbBackBill obj=(TransitRmbBackBill)o;
                return obj.getActulPaymentAmount().compareTo(BigDecimal.valueOf(10000))==1?false:true;
            }
        };

        //a.编写不同的处理流程
        Closure cl1=new Closure() {
            @Override
            public void execute(Object o) {
                TransitRmbBackBill obj=(TransitRmbBackBill)o;
                obj.setActulPaymentAmount(obj.getTotalAmount().subtract(obj.getBankfeeAmount()));
            }
        };

        Closure cl2=new Closure() {
            @Override
            public void execute(Object o) {
                TransitRmbBackBill obj=(TransitRmbBackBill)o;
                obj.setBankfeeAmount(obj.getTotalAmount().multiply((BigDecimal.valueOf(0.9))));
                obj.setActulPaymentAmount(obj.getTotalAmount().subtract(obj.getBankfeeAmount()));
            }
        };

        //将判别式和不同的处理流程相结合
        Closure ifClosure= IfClosure.getInstance(predicate,cl1,cl2);

        //b.执行流程
        Closure closureb=new Closure() {
            @Override
            public void execute(Object o) {
                TransitRmbBackBill obj=(TransitRmbBackBill)o;
                obj.setTotalAmount(obj.getTotalAmount().multiply(BigDecimal.valueOf(1.2)));
                obj.setActulPaymentAmount(obj.getTotalAmount().subtract(obj.getBankfeeAmount()));
            }
        };

        //将判别式与执行流程结合
        Closure whileClosure= WhileClosure.getInstance(predicateB,closureb,false);

        Closure closure=ChainedClosure.getInstance(ifClosure,whileClosure);

        CollectionUtils.forAllDo(trList,closure);

        System.out.println(
                JSONObject.toJSONString(trList)
        );

    }

    /**
     * 集合操作 并集 交集 差集
     * */
    public static void testh(){

        Set<Integer> set1= com.google.common.collect.Sets.newHashSet(1,2,3,4,5);
        Set<Integer> set2= com.google.common.collect.Sets.newHashSet(1,2,4,5,6,7,8,9);

        //并集
        Collection<Integer> coll1=CollectionUtils.union(set1,set2);
        //交集
        Collection<Integer> coll2=CollectionUtils.intersection(set1,set2);
        //差集
        Collection<Integer> coll3=CollectionUtils.subtract(set1,set2);

        System.out.println(
                JSONObject.toJSONString(coll1)+
                JSONObject.toJSONString(coll2)+
                JSONObject.toJSONString(coll3)
        );
    }

    /**
     * Queue队列
     * */
    public  static  void testq(){

        //循环队列
        CircularFifoBuffer circularFifoBuffer=new CircularFifoBuffer(2);
        circularFifoBuffer.add("q");
        circularFifoBuffer.add("w");
        circularFifoBuffer.add("e");
        for(Object obj:circularFifoBuffer){
            System.out.println(obj);
        }

    }

    /**
     * 迭代器扩展
     *
     * a.Map迭代器的扩展:
     *  创建IterableMap   IterableMap iterableMap
     *  获取IterableMap的迭代器  MapIterator mapIterator=iterableMap.mapIterator()
     *  获取key:mapIterator.next()  value :mapIterator.getValue()
     * */
    public static void testw(){
        //创建IterableMap
        IterableMap iterableMap=new HashedMap();
        iterableMap.put("用户1","qwe");
        iterableMap.put("用户2","sdf");
        iterableMap.put("用户3","xcv");

        //获取IterableMap的迭代器
        MapIterator mapIterator=iterableMap.mapIterator();
        while (mapIterator.hasNext()){
            //一定要mapIterator.next() 在while内只能使用一次
            //取key a：
            String key= String.valueOf(mapIterator.next());
           /* //取=key b:
            mapIterator.next();
            String key2= String.valueOf(mapIterator.getKey());*/
            String val= String.valueOf(mapIterator.getValue());
            System.out.println(key+':'+val);
        }

    }

    /**
     * 双向Map:
     * 键和值都不能重复
     * 通过调用方法:key和Value可以互换位置 实现由key找value,value找key
     *
     * */
    public  static  void teste(){
        //无序的Map
        BidiMap bidiMap=new DualHashBidiMap();
        bidiMap.put("a","qwe");
        bidiMap.put("b","gfgfh");
        bidiMap.put("c","zxcvz");
       //bidiMap.inverseBidiMap().get 方法
        String getKeyByVal= String.valueOf(bidiMap.inverseBidiMap().get("qwe"));
        System.out.println(getKeyByVal);

        //遍历查看
        MapIterator mapIterator=bidiMap.inverseBidiMap().mapIterator();
        while (mapIterator.hasNext()){
            String key= String.valueOf(mapIterator.next());
            Object val=mapIterator.getValue();

            System.out.println(
                    key+":"+val
            );
        }

        //有序的Map
        BidiMap orderbidiMap=new DualTreeBidiMap();
        orderbidiMap.put("a","qwe");
        orderbidiMap.put("b","gfgfh");
        orderbidiMap.put("c","zxcvz");
        //bidiMap.inverseBidiMap().get 方法
        String ordergetKeyByVal= String.valueOf(orderbidiMap.inverseBidiMap().get("qwe"));
        System.out.println(getKeyByVal);

        //遍历查看
        MapIterator ordermapIterator=bidiMap.inverseBidiMap().mapIterator();
        while (ordermapIterator.hasNext()){
            String key= String.valueOf(ordermapIterator.next());
            Object val=ordermapIterator.getValue();

            System.out.println(
                    key+":"+val
            );
        }

    }
    /**
     * Bag 可存放重复的数据(相当于List)
     * */
    public static void testr(){

        Bag baga=new HashBag();//无序
        Bag bagb=new TreeBag();//有序

        baga.add("qwe",100);
        baga.add("asd",100);

        Iterator it=baga.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

    }







}