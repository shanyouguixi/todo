package com.shangui;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String password = "123";
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
        String saltPassword = new Md5Hash(password,salt,5).toString(); //生成的密文，使用md5算法对明文与盐值的组合进行了三次加密
        System.out.println(salt);
        System.out.println(saltPassword);
    }
}
