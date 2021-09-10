package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.bean.SuperCategory;
import com.chloe.leizu_pro.service.ProductService;
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
        Map<String, List<String>> women = productService.getSuperCategoryList("women");
        mav.setViewName("product_main");
        mav.addObject("categoryList", women);
        mav.addObject("gender", "women");
        return mav;
    }

    @RequestMapping("/men")
    public ModelAndView menMainPage(){
        ModelAndView mav = new ModelAndView();
        Map<String, List<String>> men = productService.getSuperCategoryList("men");
        mav.setViewName("product_main");
        mav.addObject("categoryList", men);
        mav.addObject("gender", "men");
        return mav;
    }

    @RequestMapping(value = {"/products"},method = RequestMethod.GET,params = "i")
    public ModelAndView categoryPage(@RequestParam("i") Integer categoryId){
        ModelAndView mav = new ModelAndView();
        Map<String, List<Product>> categoryListMap = productService.getProductListWithColorByCategory(categoryId);
        String subCategoryName = productService.getSuperCategoryById(categoryId).getSubCategoryName();
        mav.addObject("subTitle", subCategoryName);
        mav.addObject("categoryListMap", categoryListMap);
        mav.setViewName("product_category");
        return mav;
    }







}
