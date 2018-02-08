package com.ronglian.fssc.webapp.UtilsTest;

import com.ronglian.fssc.webapp.platform.utils.wx.StringUtils;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TokenProcessor {//令牌 java加密器
    /**
     MD2
     MD5
     SHA-1
     SHA-256
     SHA-384
     SHA-512
     * 1.构造方法私有
     * 2.自己创建一个
     * 3.对外暴露一个方法，允许获取上面创建的对象
     */

    private TokenProcessor(){}

    private  static final TokenProcessor instance = new TokenProcessor();

    public static TokenProcessor getInstance(){
        return instance;
    }

    public static String genetateToken(String code,String type){
        String token="";
        if(StringUtils.isNotEmpty(code)){
            token=code;
        }else{
            token = System.currentTimeMillis()+new Random().nextInt()+"";
        }
        MessageDigest md=null;
        byte [] bytecode=null;
        try {
            if(type.equals("md5")){
                md =  MessageDigest.getInstance("md5");
                bytecode =  md.digest(token.getBytes());
            }
            if(type.equals("md2")){
                 md =  MessageDigest.getInstance("md2");
                bytecode =  md.digest(token.getBytes());

            }
            if(type.equals("sha-1")){
                 md =  MessageDigest.getInstance("sha-1");
                bytecode =  md.digest(token.getBytes());
            }
            if(type.equals("sha-256")){
                md =  MessageDigest.getInstance("sha-256");
                bytecode=  md.digest(token.getBytes());

            }
            if(type.equals("sha-384")){
                 md =  MessageDigest.getInstance("sha-384");
                bytecode =  md.digest(token.getBytes());
            }
            if(type.equals("sha-512")){
                md =  MessageDigest.getInstance("sha-512");
                bytecode =  md.digest(token.getBytes());
            }
            //base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(bytecode);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}