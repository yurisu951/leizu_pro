package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.ColorImage;

import java.util.List;

public interface SearchService {

    List<ColorImage> getListByIds(String keyWord);

    List<ColorImage> getListByKeywordLimit(String keyWords, Integer index, Integer maxPage);

}
