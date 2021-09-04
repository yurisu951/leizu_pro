package com.chloe.leizu_pro.service.impl;


import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.mapper.ColorImageMapper;
import com.chloe.leizu_pro.service.ColorImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorImageServiceImpl implements ColorImageService {
    @Autowired
    private ColorImageMapper colorImageMapper;

    public void addColorImageByCrawler(ColorImage colorImage){
        colorImageMapper.addColorImageByCrawler(colorImage);
    }

    @Override
    public List<Integer> getColorIamgeIds() {
        return colorImageMapper.getColorIamgeIds();
    }
}
