package com.chloe.leizu_pro.bean.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradingRecord {
    private Integer cartId;
    private Integer userId;
    private Date buyDate;
    private Integer totalQuantity;
    private Integer totalPrice;

}
