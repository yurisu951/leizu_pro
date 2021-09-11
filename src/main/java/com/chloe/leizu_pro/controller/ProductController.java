package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.bean.SuperCategory;
import com.chloe.leizu_pro.service.ProductService;
import com.chloe.leizu_pro.utils.ProductPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = {"/men/products/{id}"},method = RequestMethod.GET)
    public ModelAndView menCategoryPage(@PathVariable("id") Integer categoryId) {
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryListPage(mav, productService, "men", categoryId);
    }

    @RequestMapping(value = {"/women/products/{id}"},method = RequestMethod.GET)
    public ModelAndView womenCategoryPage(@PathVariable("id") Integer categoryId){
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryListPage(mav, productService, "women", categoryId);
    }

    @RequestMapping(value = {"/men/details/{id}"}, method = RequestMethod.GET)
    public ModelAndView productDetailsMenPage(@PathVariable("id") Integer productId){
        ModelAndView mav = new ModelAndView();
        ProductPageUtils.productDetailsPage(mav, productService, "men", productId);
        return mav;
    }

    @RequestMapping(value = {"/women/details/{id}"}, method = RequestMethod.GET)
    public ModelAndView productDetailsWomenPage(@PathVariable("id") Integer productId){
        ModelAndView mav = new ModelAndView();
//        ProductPageUtils.productDetailsPage(mav, productService, "women", productId);
        Map<String, List<String>> genderCategoryList = productService.getSuperCategoryList("women");
        mav.addObject("gender", "women");
        mav.addObject("categoryList", genderCategoryList);

        Product productDetails = productService.getProductDetailsById(productId);
        mav.addObject("productDetails", productDetails);
        mav.setViewName("product_details");
        return mav;
    }







}
