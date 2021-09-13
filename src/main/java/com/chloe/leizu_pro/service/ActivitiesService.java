package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.bean.PromoCode;

import java.util.List;
import java.util.Map;

public interface ActivitiesService {

    Map<String, List<PromoCode>> getPromoList(String promoCode);

    List<Integer> getProductIdListOnPromo(String promo, String gender);

    List<ColorImage> getProductList(List<Integer> productIdList, Integer index);

    Integer getTotalPage(List<Integer> productIdList);

    Integer getInventory(Integer inventoryId);


}
