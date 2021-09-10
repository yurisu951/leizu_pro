package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.bean.PromoCode;
import com.chloe.leizu_pro.service.ProductService;
import com.chloe.leizu_pro.service.PromoService;
import com.chloe.leizu_pro.service.SuperCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    SuperCategoryService superCategoryService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/women")
    public ModelAndView womenMainPage(){
        ModelAndView mav = new ModelAndView();
        Map<String, List<String>> women = superCategoryService.getSuperCategoryList("women");
        mav.setViewName("product_main");
        mav.addObject("womenCategoryList", women);
        return mav;
    }







}
