package com.chloe.leizu_pro.service;


import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.bean.SubCategory;
import com.chloe.leizu_pro.bean.SuperCategory;

import java.util.List;
import java.util.Map;

public interface ProductService {

    SuperCategory getSuperCategoryById(Integer id);

    Map<String, List<String>> getSuperCategoryList(String gender);

    List<SubCategory> getSubCategoryList(Integer category);

    Map<String, List<Product>> getProductListWithColorByCategory(Integer category);







}

//    Map<String,Map<Product,List<ColorImage>>> getProductListByCategory(Integer category);
