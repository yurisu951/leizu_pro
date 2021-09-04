package com.chloe.leizu_pro.mapper;


import com.chloe.leizu_pro.bean.ContentImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ContentImageMapper {

    void addContentImageByCrawler(ContentImage contentImage);

}
