package com.githrd.demo_interceptor.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {

    @Autowired
    HttpSession session;

    @GetMapping("/home")
    @ResponseBody
    public String home(){

        return "My Home";
    }

    @GetMapping("/hi")
    @ResponseBody
    public String hi(){

        return "Hi~~";
    }

    // /error?reason=session_timeout
    @GetMapping("/myerror")
    @ResponseBody
    public String error(String reason){

        String message = "no_message";
        switch(reason)
        {
            case "session_timeout"   : message="로그아웃 되었습니다"; break;
            case "not_admin"         : message="관리자만 이용가능한 페이지입니다"; break;
            case "not_adult"         : message="성인만 이용가능한 페이지입니다"; break;
        }

        return message;
    }

    // login?name=홍길동&age=20&grade=normal(일반) or admin(관리자)
    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam Map<String, Object> user){

        System.out.println(user);

        // 로그인 유저정보 세션에 담는다
        session.setAttribute("user", user);

        return "Login 완료";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(){

        session.removeAttribute("user");

        return "Logout 완료";
    }

    @GetMapping("/admin/members")
    public String members(){

        return "admin/member_list";
    }

    @GetMapping("/adult/photo")
    public String photo(){

        return "adult/photo_list";
    }
}
