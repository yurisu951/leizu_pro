package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.PromoCode;
import com.chloe.leizu_pro.mapper.PromoCodeMapper;
import com.chloe.leizu_pro.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {
    @Autowired
    PromoCodeMapper promoCodeMapper;

    @Override
    public Map<String, List<PromoCode>> getPromoList(String promoCode) {
        List<PromoCode> allPromoCode = promoCodeMapper.getAllPromoCode();
        PromoCode promo = promoCodeMapper.getPromoById(promoCode);
        Map<String, List<PromoCode>> result = new HashMap<>();
        result.put(promo.getPromoCode(), allPromoCode);
        return result;
    }
}
