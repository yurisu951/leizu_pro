package com.chloe.leizu_pro.service;


import com.chloe.leizu_pro.bean.ColorImage;

import java.util.List;

public interface ColorImageService {

    void addColorImageByCrawler(ColorImage colorImage);

    List<Integer> getColorIamgeIds();

}
