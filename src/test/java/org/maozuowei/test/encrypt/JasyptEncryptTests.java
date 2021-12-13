package org.maozuowei.test.encrypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author maozuowei
 * @date 2020/10/10 12:24
 * @description 数据库帐号密码加密测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptEncryptTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encryptUsernameAndPasswordTest() {
        final String username = "root";
        final String password = "bangsun";
        System.out.println("加密后的用户名为：" + stringEncryptor.encrypt(username));
        System.out.println("加密后的密码为：" + stringEncryptor.encrypt(password));
    }
}
