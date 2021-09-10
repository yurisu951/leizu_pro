package com.chloe.leizu_pro.utils;

import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.service.ProductService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public class ProductPageUtils {

    public static ModelAndView categoryMainPage(ModelAndView mav, ProductService productService,String gender){
        Map<String, List<String>> genderCategoryList = productService.getSuperCategoryList(gender);
        mav.setViewName("product_main");
        mav.addObject("categoryList", genderCategoryList);
        mav.addObject("gender", gender);
        List<Product> productListByLimit = productService.getProductListByLimit(0, 20, gender);
        mav.addObject("productList", productListByLimit);
        return mav;
    }

    public static ModelAndView categoryListPage(ModelAndView mav,ProductService productService,String gender, Integer categoryId){
        Map<String, List<Product>> categoryListMap = productService.getProductListWithColorByCategory(categoryId);
        String subCategoryName = productService.getSuperCategoryById(categoryId).getSubCategoryName();
        mav.addObject("subTitle", subCategoryName);
        mav.addObject("categoryListMap", categoryListMap);
        Map<String, List<String>> categoryList = productService.getSuperCategoryList(gender);
        mav.addObject("categoryList", categoryList);
        mav.addObject("gender", gender);
        mav.setViewName("product_category");
        return mav;
    }
}
