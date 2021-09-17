package com.chloe.leizu_pro.bean.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String image;

    private List<ColorImage> colorImageList;
    private List<ContentImage> contentImageList;

    private Integer buyNumber;
    private Inventory thisProductInfo;
    private List<Inventory> otherProductsInfo;
}
