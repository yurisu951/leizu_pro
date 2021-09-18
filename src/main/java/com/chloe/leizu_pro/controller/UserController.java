package com.chloe.leizu_pro.controller;

import com.chloe.leizu_pro.bean.product.ColorImage;
import com.chloe.leizu_pro.bean.product.Inventory;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.bean.user.UserCollection;
import com.chloe.leizu_pro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login_register")
    public String loginRegister(){
        return "user/login_register";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:index";
    }

    @PostMapping(value = {"/user/login"}, params = {"account", "password"})
    public ModelAndView login(HttpSession session, @RequestParam("account") String account,
                              @RequestParam("password") String password, @RequestParam(value = "remember",required = false)String remember){
        ModelAndView mav = new ModelAndView();
        String email = null;
        String phone = null;
        if (account.contains("@")){
            email = account;
        } else {
            phone = account;
        }

        User temp = new User(null, password, null,email,phone,null);
        System.out.println(temp);
        boolean result = userService.loginUser(temp, session);

        if (result){
            mav.setViewName("index");
            return mav;
        }
        mav.setViewName("user/login_register");
        mav.addObject("failMsg", "密碼輸入錯誤");
        return mav;
    }



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

    @PostMapping("/user/keep/{productId}")
    @ResponseBody
    public String addKeep(HttpSession session , @PathVariable("productId") Integer productId){
        Integer user = (Integer) session.getAttribute("user");
        if (user != null){
            UserCollection userCollection = new UserCollection(user,productId);
            boolean b = userService.addKeep(userCollection);
            if (b)  return "success";
        }
        return "false";
    }

    @DeleteMapping("/user/keep/{productId}")
    @ResponseBody
    public String removeKeep(HttpSession session , @PathVariable("productId") Integer productId){
        Integer user = (Integer) session.getAttribute("user");
        if (user != null){
            UserCollection userCollection = new UserCollection(user,productId);
            boolean b = userService.removeKeep(userCollection);
            if (b)  return "success";
        }
        return "false";
    }

    @RequestMapping("/user/keepes")
    public ModelAndView userKeep(HttpSession session){
        ModelAndView mav = new ModelAndView();
        Integer userId = (Integer) session.getAttribute("user");
        if (userId == null) {
            mav.setViewName("user/login_register");
            return mav;
        }
        List<Integer> userKeep = userService.getUserKeep(userId);

        List<Product> colorNameByProduct = userService.getColorNameByProductId(userKeep);

        mav.addObject("userKeepProductList", colorNameByProduct);
        mav.setViewName("/user/user_mykeep");
        return mav;
    }

    @RequestMapping(value = "/user/keep/inventory/{colorId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Inventory> getInventory(@PathVariable("colorId") Integer colorId){
        return userService.getInventoryListByColorId(colorId);
    }



}
