package com.chloe.leizu_pro.mapper.product;

import com.chloe.leizu_pro.bean.product.PromoCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PromoCodeMapper {
    PromoCode getPromoById(String id);

    List<PromoCode> getAllPromoCode();
}
