package com.chloe.leizu_pro.mapper.product;

import com.chloe.leizu_pro.bean.product.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    Product getProductById(Integer id);

    List<Product> getProductListByCategory(Integer category);

    List<Product> getProductListWithColorByCategoryId(Integer category);

    List<Product> getWomenProductListByLimit(Integer index, Integer count);

    List<Product> getMenProductListByLimit(Integer index, Integer count);

    Product getProductWithColorAndContentById(Integer id);

    List<Integer> getProductListByPromoWomen(String promo);

    List<Integer> getProductListByPromoMen(String promo);

    Product getProductPromoInfoById(Integer id);

    List<Integer> getidListByKeyWord(List<Character> keys, @Param("gender") String gender);

    List<Integer> getRandomListByGender(@Param("gender") String gender);

}
