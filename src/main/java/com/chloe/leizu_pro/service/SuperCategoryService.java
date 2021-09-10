package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.SuperCategory;

import java.util.List;
import java.util.Map;

public interface SuperCategoryService {

    Map<String, List<String>> getSuperCategoryList(String gender);

    SuperCategory getSuperCategoryById(Integer id);

}
