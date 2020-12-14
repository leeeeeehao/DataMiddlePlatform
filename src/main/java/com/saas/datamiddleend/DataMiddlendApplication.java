package com.saas.datamiddleend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.saas.datamiddleend.dao"})
public class DataMiddlendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataMiddlendApplication.class, args);
    }

}
