package com.githrd.demo_mvc.dao;

import org.springframework.stereotype.Repository;

@Repository("test_dao2")
public class TestDao2Impl implements TestDao{

    public TestDao2Impl() {
        super();
        System.out.println("--TestDao2Impl()--");
    }

    @Override
    public String hello() {
        return "TestDao2 : 안녕하세요";
    }
}
