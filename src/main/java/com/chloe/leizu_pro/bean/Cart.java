package com.chloe.leizu_pro.bean;

import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.PurchaseDetails;
import com.chloe.leizu_pro.bean.user.TradingRecord;
import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private Integer cartId;
    private Integer userId;

    private List<Product> productList;


    private TradingRecord tradingRecord;
    private List<PurchaseDetails> purchaseDetails;
}
