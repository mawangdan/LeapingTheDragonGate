package com.mawang.qianrushu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mawang.qianrushu.mapper")
public class QianrushuApplication {

    public static void main(String[] args) {
        SpringApplication.run(QianrushuApplication.class, args);
    }

}
