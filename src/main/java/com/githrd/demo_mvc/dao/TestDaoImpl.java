package com.githrd.demo_mvc.dao;

import org.springframework.stereotype.Repository;

@Repository("test_dao")
public class TestDaoImpl implements TestDao{

    public TestDaoImpl() {
        super();
        System.out.println("--TestDaoImpl()--");
    }

    @Override
    public String hello() {
        return "TestDao : 안녕하세요";
    }
}
