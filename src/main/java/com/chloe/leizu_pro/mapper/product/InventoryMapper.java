package com.chloe.leizu_pro.mapper.product;


import com.chloe.leizu_pro.bean.product.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InventoryMapper {

    Inventory getInventoryById(Integer id);

    List<Inventory> getInventoryListByProductId(Integer productId);

    List<Inventory> getInventoryListByColorId(Integer colorId);

    Integer getQuantityByInventoryId(Integer inventoryId);

    Inventory getInventoryInfo(Integer inventoryId);

}
