package com.chloe.leizu_pro.mapper;

import com.chloe.leizu_pro.bean.PromoCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PromoCodeMapper {
    PromoCode getPromoById(String id);

    List<PromoCode> getAllPromoCode();
}
