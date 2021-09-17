package com.chloe.leizu_pro.bean.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetails {
    private Integer id;
    private Integer cartId;
    private Integer productId;
    private Integer inventoryId;
    private Integer price;
    private Integer discount;
    private Integer quantity;

}
