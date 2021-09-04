package com.chloe.leizu_pro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private Integer category;  // Category Id
    private String productName;
    private Integer price;
    private String promo;   // PromoName Id
    private Integer promoPrice;

}
