package com.chloe.leizu_pro.mapper;

import com.chloe.leizu_pro.bean.ColorImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ColorImageMapper {

    void addColorImageByCrawler(ColorImage colorImage);

    List<Integer> getColorIamgeIds();
}
