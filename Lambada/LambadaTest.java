package com.ronglian.fssc.webapp.Lambada;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.function.Predicate;

public class LambadaTest {

    public static void main(String[] args) {

        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        for(String str:players){
            System.out.println(str);
        }
        System.out.println("====================================================");
        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> soutFunction(player));

        System.out.println("====================================================");
       // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);

        System.out.println("=================使用Lambdas排序集合===================================");
        /*Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(Arrays.toString(atp));*/

        Arrays.sort(atp,(String str1,String str2)->str1.compareTo(str2));
        System.out.println(Arrays.toString(atp));

        System.out.println("=================使用Lambdas和Streams===================================");

        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));


        javaProgrammers.forEach((p) -> p.setSalary(p.getSalary()*2));
        javaProgrammers.forEach((p)-> System.out.println(p.getSalary()));

        System.out.println("定义过滤器========================================================================");
        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        // 定义 filters   Lambada
        Predicate<Person>  ageFilter=(person -> person.getAge()>24);
        Predicate<Person>  salaryFilter=(person -> person.getSalary()>1400);
        Predicate<Person>  genderFilter=(person -> person.getGender().equals("female"));



        phpProgrammers.stream().filter(ageFilter).filter(salaryFilter).filter(genderFilter).forEach((person -> System.out.println(JSONObject.toJSON(person))));

        //使用limit方法,可以限制结果集的个数:
        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的PHP程序员（前2名 按工资排序）:");
        phpProgrammers.stream()
                .sorted((p1,p2)-> (p2.getSalary()-p1.getSalary()))//先排序在进行过滤筛选
                .filter(ageFilter)
                .filter(salaryFilter)
                .limit(2)
                .forEach((person -> System.out.println(person.getFirstName()+" "+person.getSalary())));


        System.out.println("min和max方法========================================================================");
        Optional<Person> phpP= phpProgrammers.stream().max((p1, p2)->(p1.getSalary()-p2.getSalary()));
        System.out.println(JSONObject.toJSON(phpP.get().getSalary()));


    }

    public static void soutFunction(String str){
        System.out.println(str);
    }




}
