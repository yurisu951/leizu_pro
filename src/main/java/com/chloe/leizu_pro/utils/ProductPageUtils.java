package com.chloe.leizu_pro.utils;

import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.service.ProductService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public class ProductPageUtils {

    public static ModelAndView categoryMainPage(ModelAndView mav, ProductService productService, String gender){
        Map<String, List<String>> genderCategoryList = productService.getSuperCategoryList(gender);
        mav.addObject("gender", gender);
        mav.addObject("categoryList", genderCategoryList);

        List<Product> productListByLimit = productService.getProductListByLimit(0, 20, gender);
        mav.addObject("productList", productListByLimit);
        mav.setViewName("product_main");
        return mav;
    }

    public static ModelAndView categoryListPage(ModelAndView mav, ProductService productService, String gender, Integer categoryId){
        Map<String, List<String>> genderCategoryList = productService.getSuperCategoryList(gender);
        mav.addObject("gender", gender);
        mav.addObject("categoryList", genderCategoryList);

        Map<String, List<Product>> categoryListMap = productService.getProductListWithColorByCategory(categoryId);
        String subCategoryName = productService.getSuperCategoryById(categoryId).getSubCategoryName();
        mav.addObject("subTitle", subCategoryName);
        mav.addObject("categoryListMap", categoryListMap);
        mav.setViewName("product_category");
        return mav;
    }

    public static ModelAndView productDetailsPage(ModelAndView mav, ProductService productService, String gender, Integer productId, String colorName){
        Map<String, List<String>> genderCategoryList = productService.getSuperCategoryList(gender);
        mav.addObject("gender", gender);
        mav.addObject("categoryList", genderCategoryList);

        Product productDetails = productService.getProductDetailsById(productId);
        String promo = productDetails.getPromo();
        if (promo != null) {
            String promoName = productService.getPromoName(promo);
            mav.addObject("promoName", promoName);
        }
        if (colorName!=null)  mav.addObject("colorName", colorName);

        mav.addObject("productDetails", productDetails);
        mav.setViewName("product_details");

        return mav;
    }

}
