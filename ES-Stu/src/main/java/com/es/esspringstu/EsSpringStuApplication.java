package com.es.esspringstu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.es.esspringstu.canal.mapper")
public class EsSpringStuApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsSpringStuApplication.class, args);
    }

}
