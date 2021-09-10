package com.chloe.leizu_pro.mapper;

import com.chloe.leizu_pro.bean.SubCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubCategoryMapper {
    List<SubCategory> getSubcategoryListByCategory(Integer category);
}
