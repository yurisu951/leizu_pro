package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.PromoCode;

import java.util.List;
import java.util.Map;

public interface ActivitiesService {

    Map<String, List<PromoCode>> getPromoList(String promoCode);
}
