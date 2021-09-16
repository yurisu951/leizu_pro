package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.ColorImage;

import java.util.List;

public interface SearchService {

    List<ColorImage> getListByIds(String keyWord);

    List<Integer> getIdListByKeyword(String keyWords, String gender);
    Integer getMaxPageFronIdList(List<Integer> ids);

    List<ColorImage> getListLimit(List<Integer> ids, Integer index);

    List<ColorImage> getRandList(String gender);

}
