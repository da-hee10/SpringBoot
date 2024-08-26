package com.githrd.demo_mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

// Mapper위치가 basePackage밖에 존재할 경우에는 반드시 작성해야 함
//				basePackage내에 있을 경우는 생략가능하다
@Configuration
@MapperScan(basePackages = "com.githrd.demo_mybatis", annotationClass = Mapper.class)
public class DemoMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMybatisApplication.class, args);
	}

}
