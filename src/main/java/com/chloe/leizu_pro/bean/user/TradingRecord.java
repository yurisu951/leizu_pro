package com.chloe.leizu_pro.bean.user;

import com.chloe.leizu_pro.bean.product.ColorImage;
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
    private String status;

}
