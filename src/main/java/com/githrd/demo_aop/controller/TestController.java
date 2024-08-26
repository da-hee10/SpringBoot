package com.githrd.demo_aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.githrd.demo_aop.service.TestService;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/hello")
    public String hello(){

        return testService.hello();
    }

    @GetMapping("/hi")
    public String hi(){

        return testService.hi();
    }
}
