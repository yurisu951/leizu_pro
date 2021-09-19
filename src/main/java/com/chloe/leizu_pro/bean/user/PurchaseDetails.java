package com.chloe.leizu_pro.bean.user;

import com.chloe.leizu_pro.bean.product.ColorImage;
import com.chloe.leizu_pro.bean.product.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@NoArgsConstructor
public class PurchaseDetails {
    private Integer id;
    private Integer cartId;
    private Integer productId;
    private Integer inventoryId;
    private Integer price;
    private Integer discount;
    private Integer quantity;

    private String productName;
    private String promoInfo;
    private Inventory inventoryInfo;

    public PurchaseDetails(Integer id, Integer cartId, Integer productId, Integer inventoryId, Integer price, Integer discount, Integer quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.inventoryId = inventoryId;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }
}
