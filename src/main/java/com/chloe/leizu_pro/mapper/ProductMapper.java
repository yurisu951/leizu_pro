package com.chloe.leizu_pro.mapper;

import com.chloe.leizu_pro.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMapper {

    public Product getProductById(Integer id);

    public void addProduct(Product product);

    public void addProductByCrawler(Product product);
}
