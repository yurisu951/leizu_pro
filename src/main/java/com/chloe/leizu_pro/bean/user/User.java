package com.chloe.leizu_pro.bean.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String password;
    private String userName;
    private String email;
    private String phone;
    private String address;

}
