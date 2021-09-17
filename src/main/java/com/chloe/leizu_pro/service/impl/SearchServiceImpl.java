package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.product.ColorImage;
import com.chloe.leizu_pro.mapper.product.ColorImageMapper;
import com.chloe.leizu_pro.mapper.product.ProductMapper;
import com.chloe.leizu_pro.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    ColorImageMapper colorImageMapper;
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ColorImage> getListByIds(String keyWord) {
        List<Integer> productIds = null;
        if (keyWord.contains("=")){
            keyWord =  keyWord.substring(keyWord.indexOf("="));
        }
        String[] keyWords = keyWord.split("[+]");
        for (String keyItem :keyWords) {
            if (keyItem.trim().length() == 5){
                if (productIds == null) productIds = new ArrayList<>();
                productIds.add(Integer.valueOf(keyItem));
            }
            if (keyItem.trim().length() == 7) {
                productIds.add(Integer.valueOf(keyItem.substring(2)));
            }
        }
        return colorImageMapper.getImageAndNameByid(productIds ,null);
    }

    @Override
    public List<Integer> getIdListByKeyword(String keyWords, String gender){
        List<Character> set = new ArrayList<>();
        char[] chars = keyWords.toCharArray();
        for (char a:chars) {
            set.add(a);
        }
        return  productMapper.getidListByKeyWord(set, gender);
    }

    @Override
    public Integer getMaxPageFronIdList(List<Integer> ids){
        return  colorImageMapper.getMaxPageForImageAndNameByid(ids);
    }


    @Override
    public List<ColorImage> getListLimit(List<Integer> ids, Integer index) {
        return colorImageMapper.getImageAndNameByid(ids, index);
    }

    @Override
    public List<ColorImage> getRandList(String gender) {
        List<Integer> ids = productMapper.getRandomListByGender(gender);
        return colorImageMapper.getRandListBypId(ids);
    }

}
