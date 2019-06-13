package com.human.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by SX-503 on 2018/10/29.
 */
public class Md5Utils {


   // private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
   private static final String hashAlgorithmName = "MD5";
    private static final int hashIterations = 1024; // 加密次数

    public static String encrypt(String uname, String password) {
        System.out.println(uname+" "+password);
        ByteSource salt = ByteSource.Util.bytes(uname);
        System.out.println(salt);
    //    String pas= new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
        SimpleHash hash = new SimpleHash(hashAlgorithmName,password,salt,hashIterations);
        System.out.println(hash.toString());
        return hash.toString();
    }

//    @Override
//    public boolean registerData(String username, String password) {
//
//        // 将用户名作为盐值
//        ByteSource salt = ByteSource.Util.bytes(username);
//        /*
//         * MD5加密：
//         * 使用SimpleHash类对原始密码进行加密。
//         * 第一个参数代表使用MD5方式加密
//         * 第二个参数为原始密码
//         * 第三个参数为盐值，即用户名
//         * 第四个参数为加密次数
//         * 最后用toHex()方法将加密后的密码转成String
//         * */
//        String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();
//
//        Mapp<String, String> dataMap = new HashMap<>();
//        dataMap.put("id", id);
//        dataMap.put("username", username);
//        dataMap.put("password", newPs);
//
//        // 看数据库中是否存在该账户
//        Mapp<String, Object> userInfo = queryInfoByUsername(username);
//        if(userInfo == null) {
//            userMapper.insertData(dataMap);
//            return true;
//        }
//        return false;
//    }

}
