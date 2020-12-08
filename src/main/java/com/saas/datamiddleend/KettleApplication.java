package com.saas.datamiddleend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.saas.datamiddleend.dao"})
public class KettleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KettleApplication.class, args);
    }

}
