package com.chloe.leizu_pro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorImage {
    private Integer id;
    private Integer productId;  // Product Id
    private String productColor;
    private String productColorImage;
    private String productImage;

    private List<Inventory> inventoryList;
    private Product productInfo;

}
