package com.chloe.leizu_pro.mapper.product;

import com.chloe.leizu_pro.bean.product.SuperCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SuperCategoryMapper {

    List<SuperCategory> getSuperCategoryListOnMen();

    List<SuperCategory> getSuperCategoryListOnWomen();

    SuperCategory getSuperCategoryById(Integer id);
}
