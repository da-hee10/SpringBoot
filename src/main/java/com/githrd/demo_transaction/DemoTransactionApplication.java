package com.githrd.demo_transaction;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan(basePackages = "com.githrd.demo_transaction", annotationClass = Mapper.class)
public class DemoTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTransactionApplication.class, args);
	}

}
