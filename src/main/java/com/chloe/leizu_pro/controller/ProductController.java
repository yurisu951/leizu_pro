package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.service.ActivitiesService;
import com.chloe.leizu_pro.service.ProductService;
import com.chloe.leizu_pro.service.SearchService;
import com.chloe.leizu_pro.utils.ProductPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    SearchService searchService;



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

    @RequestMapping(value = {"/men/details/{id}","/men/details/{id}/{color}"}, method = RequestMethod.GET)
    public ModelAndView productDetailsMenPage(@PathVariable("id") Integer productId,
                                              @PathVariable(value = "color", required = false) String colorName){
        ModelAndView mav = new ModelAndView();
        ProductPageUtils.productDetailsPage(mav, productService, "men", productId, colorName);
        return mav;
    }

    @RequestMapping(value = {"/women/details/{id}","/women/details/{id}/{color}"}, method = RequestMethod.GET)
    public ModelAndView productDetailsWomenPage(@PathVariable("id") Integer productId,
                                                @PathVariable(value = "color", required = false) String colorName){
        ModelAndView mav = new ModelAndView();
        ProductPageUtils.productDetailsPage(mav, productService, "women", productId, colorName);
        return mav;
    }

    @RequestMapping(value = "/load/{gender}", method = RequestMethod.POST)
    @ResponseBody
    public List<Product> loadMoreProduct(@PathVariable("gender") String gender){
        int  indexRan = (int)(Math.random()*50+1);
        List<Product> productListByLimit = productService.getProductListByLimit( indexRan, 20, gender);
        return productListByLimit;
    }

    @RequestMapping(value = {"/search", "/search/{gender}"},method = RequestMethod.GET)
    public ModelAndView searchPage(@RequestParam(value = "keyWord", required = false) String keyWord,
                                   @RequestParam(value = "keyWords", required = false) String keyWords,
                                   @PathVariable(value = "gender", required = false)String gender){
        ModelAndView mav = new ModelAndView();
        List<ColorImage> productList = null;
        Integer maxPage = 0;

        if (keyWord != null){
            productList = searchService.getListByIds(keyWord);
        }
        if (keyWords != null){
            productList = searchService.getListByKeywordLimit(keyWords, 0 ,maxPage);
            maxPage = maxPage /20 +(maxPage%20>0? 1:0);
        }

        mav.addObject("productList", productList);
        mav.addObject("maxPage", maxPage);
        mav.setViewName("product_search");
        return mav;
    }


}
