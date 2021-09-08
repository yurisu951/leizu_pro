package com.chloe.leizu_pro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperCategory {
    private Integer id;
    private Integer gender;
    private String category;
    private String subCategoryName;
    private String url;
}
