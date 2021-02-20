package com.cy;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class MD5Tests {

    @Test
    void testMD501(){
        String password = "123456";
        //盐值
        String salt = UUID.randomUUID().toString();
        //shiro框架中的一个加密API，加密了1024次
        SimpleHash simpleHash = new SimpleHash("MD5",password,salt,1024);
        //返回加密后的16进制
        password = simpleHash.toHex();
        System.out.println(password);
    }
}
