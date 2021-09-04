package com.chloe.leizu_pro.service.impl;


import com.chloe.leizu_pro.bean.ContentImage;
import com.chloe.leizu_pro.mapper.ContentImageMapper;
import com.chloe.leizu_pro.service.ContentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentImageServiceImpl implements ContentImageService {

    @Autowired
    private ContentImageMapper contentImageMapper;

    @Override
    public void addContentImageByCrawler(ContentImage contentImage) {
        contentImageMapper.addContentImageByCrawler(contentImage);
    }
}
