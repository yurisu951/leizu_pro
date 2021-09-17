package com.chloe.leizu_pro.bean.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private Integer id;
    private Integer productId; // Product Id
    private Integer color;  // Color Id
    private String size;
    private Integer quantity;

    private ColorImage colorInfo;
}
