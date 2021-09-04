package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.Inventory;
import com.chloe.leizu_pro.mapper.InventoryMapper;
import com.chloe.leizu_pro.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public void addInventoryByCrawler(Inventory inventory) {
        inventoryMapper.addInventoryByCrawler(inventory);
    }
}
