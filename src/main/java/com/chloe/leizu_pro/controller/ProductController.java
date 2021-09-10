package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.bean.SuperCategory;
import com.chloe.leizu_pro.service.ProductService;
import com.chloe.leizu_pro.utils.ProductPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/women")
    public ModelAndView womenMainPage(){
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryMainPage(mav, productService, "women");
    }

    @RequestMapping("/men")
    public ModelAndView menMainPage(){
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryMainPage(mav, productService, "men");
    }

    @RequestMapping(value = {"/men/products"},method = RequestMethod.GET,params = "i")
    public ModelAndView menCategoryPage(@RequestParam("i") Integer categoryId){
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryListPage(mav, productService, "men", categoryId);
    }

    @RequestMapping(value = {"/women/products"},method = RequestMethod.GET,params = "i")
    public ModelAndView womenCategoryPage(@RequestParam("i") Integer categoryId){
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryListPage(mav, productService, "women", categoryId);
    }







}
