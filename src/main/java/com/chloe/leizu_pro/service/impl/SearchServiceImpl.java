package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.mapper.ColorImageMapper;
import com.chloe.leizu_pro.mapper.ProductMapper;
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
    public List<ColorImage> getListByKeywordLimit(String keyWords, Integer index, Integer maxPage) {
        List<Character> set = new ArrayList<>();
        char[] chars = keyWords.toCharArray();
        for (char a:chars) {
            set.add(a);
        }

        List<Integer> ids = productMapper.getidListByKeyWord(set);
        maxPage = colorImageMapper.getMaxPageForImageAndNameByid(ids);
        return colorImageMapper.getImageAndNameByid(ids, index);
    }

}
