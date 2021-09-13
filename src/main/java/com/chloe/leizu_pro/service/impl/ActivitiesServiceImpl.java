package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.bean.PromoCode;
import com.chloe.leizu_pro.mapper.ColorImageMapper;
import com.chloe.leizu_pro.mapper.InventoryMapper;
import com.chloe.leizu_pro.mapper.ProductMapper;
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
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ColorImageMapper colorImageMapper;
    @Autowired
    InventoryMapper inventoryMapper;


    @Override
    public Map<String, List<PromoCode>> getPromoList(String promoCode) {
        List<PromoCode> allPromoCode = promoCodeMapper.getAllPromoCode();
        PromoCode promo = promoCodeMapper.getPromoById(promoCode);
        Map<String, List<PromoCode>> result = new HashMap<>();
        result.put(promo.getPromoCode(), allPromoCode);
        return result;
    }

    @Override
    public List<Integer> getProductIdListOnPromo(String promo, String gender) {
        List<Integer> productIdList = null;
        if ("women".equals(gender)) productIdList = productMapper.getProductListByPromoWomen(promo);
        if ("men".equals(gender)) productIdList = productMapper.getProductListByPromoMen(promo);
        return productIdList;
    }

    public List<ColorImage> getProductList(List<Integer> productIdList, Integer index){
        index = (index - 1) * 18;
        return  colorImageMapper.getColorImageListWithInventoryByProductIdLimit(productIdList, index);
    }

    public Integer getTotalPage(List<Integer> productIdList){
        Integer promoCount = colorImageMapper.getPromoCount(productIdList);
        return promoCount/18 + ((promoCount % 18 > 0) ?1:0);
    }


    @Override
    public Integer getInventory(Integer inventoryId){
        return inventoryMapper.getQuantityByInventoryId(inventoryId);
    }


}
