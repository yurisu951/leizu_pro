package com.chloe.leizu_pro.mapper;

import com.chloe.leizu_pro.bean.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InventoryMapper {

    void addInventoryByCrawler(Inventory inventory);
}
