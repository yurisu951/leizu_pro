package com.chloe.leizu_pro.bean;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private Integer cartId;
    private Integer userId;

    private List<Product> productList;
}
