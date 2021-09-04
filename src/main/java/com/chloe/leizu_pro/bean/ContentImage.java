package com.chloe.leizu_pro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentImage {
    private Integer id;
    private Integer productId; // Product Id
    private String image;
    private String href;
}
