package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.bean.SubCategory;
import com.chloe.leizu_pro.bean.SuperCategory;
import com.chloe.leizu_pro.mapper.ColorImageMapper;
import com.chloe.leizu_pro.mapper.ProductMapper;
import com.chloe.leizu_pro.mapper.SubCategoryMapper;
import com.chloe.leizu_pro.mapper.SuperCategoryMapper;
import com.chloe.leizu_pro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SuperCategoryMapper superCategoryMapper;
    @Autowired
    SubCategoryMapper subCategoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ColorImageMapper colorImageMapper;

    @Override
    public SuperCategory getSuperCategoryById(Integer id) {
        return superCategoryMapper.getSuperCategoryById(id);
    }

    @Override
    public Map<String, List<String>> getSuperCategoryList(String gender) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        List<SuperCategory> list = null;
        if ("women".equals(gender))
            list =  superCategoryMapper.getSuperCategoryListOnWomen();
        if ("men".equals(gender))
            list =  superCategoryMapper.getSuperCategoryListOnMen();

        for (SuperCategory superCategory: list){
            String cateName = superCategory.getCategory();
            Integer subCateId = superCategory.getId();
            if(result.containsKey(cateName)){
                List<String> list1 = result.get(cateName);
                list1.add(subCateId + superCategory.getSubCategoryName());
            } else {
                List<String> list1 = new ArrayList<>();
                list1.add(subCateId + superCategory.getSubCategoryName());
                result.put(cateName, list1);
            }
        }
        return result;
    }

    @Override
    public List<SubCategory> getSubCategoryList(Integer category) {
        return subCategoryMapper.getSubcategoryListByCategory(category);
    }

    @Override
    public Map<String, List<Product>> getProductListWithColorByCategory(Integer category) {
        Map<String, List<Product>> result = new LinkedHashMap<>();
        List<SubCategory> subcategoryListByCategory = subCategoryMapper.getSubcategoryListByCategory(category);
        for (SubCategory subCategory :subcategoryListByCategory) {
            Integer categoryId = subCategory.getId();
            String subTitle = subCategory.getSubCategoryName();
            List<Product> productList = productMapper.getProductListWithColorByCategoryId(categoryId);
            result.put(subTitle, productList);
        }
        return result;
    }

    @Override
    public List<Product> getProductListByLimit(Integer index, Integer count, String gender) {
        if ("women".equals(gender)) return productMapper.getWomenProductListByLimit(index, count);
        if ("men".equals((gender))) return productMapper.getMenProductListByLimit(index,count);
        return null;
    }

    @Override
    public Product getProductDetailsById(Integer id) {
        return productMapper.getProductWithColorAndContentById(id);
    }

}
