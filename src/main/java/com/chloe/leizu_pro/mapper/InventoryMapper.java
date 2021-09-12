package com.chloe.leizu_pro.mapper;


import com.chloe.leizu_pro.bean.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InventoryMapper {

    Inventory getInventoryById(Integer id);

    List<Inventory> getInventoryListByProductId(Integer productId);

}
