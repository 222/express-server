package com.express.server.netty;

import com.express.server.common.LoginSession;
import com.express.server.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExpressServerApplicationTests {

    @Test
    void contextLoads() {

    }

    public static void main(String[] args) {
        Integer expires = 1;
        String secret = "123456";
        LoginSession loginSession = new LoginSession();
        String token = JwtUtils.createToken(expires, secret, loginSession);
        System.out.println(token);
    }

}
