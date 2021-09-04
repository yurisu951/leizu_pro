package com.chloe.leizu_pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;

@Controller
public class TestController {

    @GetMapping("/test1")
    public ModelAndView testforward(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("hello", "hello, world!");
        mav.setViewName("forward:/test2");
        System.out.println("我在testforward");
        return mav;
    }

    @GetMapping("/test2")
    @ResponseBody
    public String testfoward2(ServletRequest request){
        String hello = (String) request.getAttribute("hello");
        System.out.println(hello);
        System.out.println("我在testforward2");
        return hello;
    }

}
