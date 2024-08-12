package com.express;

import com.championlang.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpressServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpressServerApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public NettyServer nettyServer() {
        return new NettyServer();
    }
}
