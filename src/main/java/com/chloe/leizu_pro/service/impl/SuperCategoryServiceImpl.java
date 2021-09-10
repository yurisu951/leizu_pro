package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.SuperCategory;
import com.chloe.leizu_pro.mapper.SuperCategoryMapper;
import com.chloe.leizu_pro.service.SuperCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuperCategoryServiceImpl implements SuperCategoryService {

    @Autowired
    SuperCategoryMapper superCategoryMapper;

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
    public SuperCategory getSuperCategoryById(Integer id) {
        SuperCategory superCategoryById = superCategoryMapper.getSuperCategoryById(id);
        return superCategoryById;
    }
}
