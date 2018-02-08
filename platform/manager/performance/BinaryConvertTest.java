package com.ronglian.fssc.webapp.platform.manager.performance;

public class BinaryConvertTest {

    public static void testInteger() {
        int i = 2010;
        System.err.println();
        System.err.println("原始数据：" + i);
        // 二进制转换
        System.err.println("==========整型——二进制转换==========");
        System.err.println("二进制：" + Integer.toBinaryString(i));
        System.err.println("十进制：" + Integer.parseInt(Integer.toBinaryString(i), 2));
        // 八进制转换
        System.err.println("==========整型——八进制转换==========");
        System.err.println("八进制：" + Integer.toOctalString(i));
        System.err.println("十进制：" + Integer.parseInt(Integer.toOctalString(i), 8));
        // 十六进制转换
        System.err.println("==========整型——十六进进制转换==========");
        System.err.println("十六进制：" + Integer.toHexString(i));
        System.err.println("十进制：" + Integer.parseInt(Integer.toHexString(i), 16));
    }

    public static void testLong() {
        Long i = 2010201020102010201L;
        System.err.println();
        System.err.println("原始数据：" + i);
        // 二进制转换
        System.err.println("==========长整型——二进制转换==========");
        System.err.println("二进制：" + Long.toBinaryString(i));
        System.err.println("十进制：" + Long.parseLong(Long.toBinaryString(i), 2));
        // 八进制转换
        System.err.println("==========长整型——八进制转换==========");
        System.err.println("八进制：" + Long.toOctalString(i));
        System.err.println("十进制：" + Long.parseLong(Long.toOctalString(i), 8));
        // 十六进制转换
        System.err.println("==========长整型——十六进进制转换==========");
        System.err.println("十六进制：" + Long.toHexString(i));
        System.err.println("十进制：" + Long.parseLong(Long.toHexString(i), 16));
    }

    public static void testHexToLong(){
        String hex = "1be5ab2d624ee5591";
        System.err.println("==========十六进制——十进制转换==========");
        System.err.println("十进制: "+ Long.valueOf(hex, 16));
    }
    public static void main(String[] arg){
//        testInteger();
//        testLong();
//        testHexToLong();
        
        Integer a = 1;
        Integer b = 2;
        System.out.println(a.compareTo(b));
    }
}
