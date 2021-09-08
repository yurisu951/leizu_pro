package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.bean.PromoCode;
import com.chloe.leizu_pro.service.ProductService;
import com.chloe.leizu_pro.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/women")
    public String womenMainPage(){

        return "product_main";
    }







}
