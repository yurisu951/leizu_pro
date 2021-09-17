package com.chloe.leizu_pro.mapper.product;

import com.chloe.leizu_pro.bean.product.ContentImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContentImageMapper {

    List<ContentImage> getContentImageListByProductId(Integer productId);

}
