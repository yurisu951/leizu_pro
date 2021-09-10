package com.chloe.leizu_pro.mapper;

import com.chloe.leizu_pro.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    Product getProductById(Integer id);

    List<Product> getProductListByCategory(Integer category);

    List<Product> getProductListWithColorByCategoryId(Integer category);

}
