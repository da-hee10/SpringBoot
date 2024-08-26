package com.githrd.demo_aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class Advice {

    //Spring에게 Injection시켜달라고 요청
    @Autowired
    HttpServletRequest request;

    // Target(감시지점) 설정
    //   *(..) : 모든메소드(.. <-인자구분없다)
    @Pointcut("execution(* com.githrd.demo_aop.service.*Service.*(..))")
    public void service_pointcut(){}
   
    @Before("service_pointcut()")
    public void before(JoinPoint jp){

        Signature s = jp.getSignature();
        System.out.println("--Before : "  + s.toShortString());

        long start = System.currentTimeMillis();
        //request binding 
        request.setAttribute("start", start);

    }

    @After("service_pointcut()")
    public void after(JoinPoint jp){

        Signature s = jp.getSignature();
        System.out.println("--After : "  + s.toShortString());


        long start = (Long)request.getAttribute("start");

        long end = System.currentTimeMillis();


        System.out.printf("--[수행시간] : %d(ms)\n", end - start);

    }
}
