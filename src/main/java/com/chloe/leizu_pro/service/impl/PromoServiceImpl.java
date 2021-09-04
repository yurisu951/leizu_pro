package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.PromoCode;
import com.chloe.leizu_pro.mapper.PromoCodeMapper;
import com.chloe.leizu_pro.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    PromoCodeMapper promoMapper;

    @Override
    public void addPromotion(PromoCode promotion) {
        promoMapper.addPromo(promotion);
    }
}
