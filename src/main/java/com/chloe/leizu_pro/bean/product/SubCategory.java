package com.chloe.leizu_pro.bean.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {
    private Integer id;
    private Integer category;
    private String subCategoryName;
}
