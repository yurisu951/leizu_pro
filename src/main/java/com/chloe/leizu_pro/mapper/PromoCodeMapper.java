package com.chloe.leizu_pro.mapper;

import com.chloe.leizu_pro.bean.PromoCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PromoCodeMapper {

    void addPromo(PromoCode promo);
}
