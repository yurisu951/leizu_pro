package com.chloe.leizu_pro.mapper;

import com.chloe.leizu_pro.bean.ColorImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ColorImageMapper {

    List<ColorImage> getColorImageAndNameOfListByProductId(Integer productId);

    ColorImage getColorNameById(Integer id);

    List<ColorImage> getColorImageListWithInventoryByProductIdLimit(List<Integer> pidList, Integer index);

    Integer getPromoCount(List<Integer> pidList);

    List<ColorImage> getImageAndNameByid(List<Integer> productIds, @Param("index")Integer index);

    Integer getMaxPageForImageAndNameByid(List<Integer> productIds);

}
