package com.chloe.leizu_pro.controller;

import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping(value = "/test2", params = {"password", "userName", "email", "phone"})
    public String registerUser(@RequestParam("password") String password,
                               @RequestParam("userName") String userName,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam(value = "address", required = false) String address){
        boolean addSuccess;
        User user = null;
        if (password != null && userName != null && email != null && phone != null){
            user = new User(null, password, userName, email, phone, null);
            addSuccess = userService.addUser(user);
        }
        return  "success";
        }

}
