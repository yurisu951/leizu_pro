package com.chloe.leizu_pro.controller;

import com.chloe.leizu_pro.bean.product.Inventory;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.PurchaseDetails;
import com.chloe.leizu_pro.bean.user.TradingRecord;
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
    public ModelAndView loginRegister(HttpSession session){
        ModelAndView mav = new ModelAndView();
        User remember = (User) session.getAttribute("remember");
        if (remember != null){
            mav.addObject("rememberInfo",remember);
        }
        mav.setViewName("user/login_register");
        return mav;
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:index";
    }

    @PostMapping(value = "/check_again")
    @ResponseBody
    public String checkPwdAgain(HttpSession session,
                                 @RequestBody Map<String, String> params){
        Integer userId = (Integer) session.getAttribute("user");
        return userService.checkPwd(userId, params.get("password"));
    }


    @PostMapping(value = {"/user/login"}, params = {"account", "password"})
    public ModelAndView login(HttpSession session, @RequestParam("account") String account,
                              @RequestParam("password") String password, @RequestParam(value = "remember",required = false) String remember,
                              @RequestParam("url") String url ){
        ModelAndView mav = new ModelAndView();

        boolean result = userService.loginUser(session,account,password, remember);

        if (result){
            mav.setViewName("redirect:" + url);
            return mav;
        }
        mav.setViewName("redirect:/user/login_register");
        mav.addObject("failMsg", "密碼輸入錯誤");
        return mav;
    }


//    @PostMapping(value = "/test2", params = {"password", "userName", "email", "phone"})
//    public String registerUser(@RequestParam("password") String password,
//                               @RequestParam("userName") String userName,
//                               @RequestParam("email") String email,
//                               @RequestParam("phone") String phone,
//                               @RequestParam(value = "address", required = false) String address){
//        boolean addSuccess;
//        User user = null;
//        if (password != null && userName != null && email != null && phone != null){
//            user = new User(null, password, userName, email, phone, null);
//            addSuccess = userService.addUser(user);
//        }
//        return  "success";
//    }

    @GetMapping("/user/profile")
    public ModelAndView userProfile(HttpSession session){
        ModelAndView mav = new ModelAndView();
        Integer userId = (Integer) session.getAttribute("user");
        if (userId == null) {
            mav.setViewName("user/login_register");
            return mav;
        }
        mav.addObject("userProfile", userService.getUserProfile(userId));
        mav.setViewName("user/user_profile");
        return mav;
    }

    @PostMapping("/user/profile")
    public ModelAndView updateUserProfile(@RequestParam Map<String,String> params, HttpSession session){
        ModelAndView mav = new ModelAndView();
        Integer userId = (Integer) session.getAttribute("user");
        boolean result = userService.updateUserProfile(params, userId);

        mav.addObject("userProfile", userService.getUserProfile(userId));
        mav.setViewName("user/user_profile");
        return mav;
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
        if (userKeep != null && userKeep.size() > 0){
            List<Product> colorNameByProduct = userService.getColorNameByProductId(userKeep);
            mav.addObject("userKeepProductList", colorNameByProduct);
        }
        mav.setViewName("/user/user_mykeep");
        return mav;
    }

    @RequestMapping(value = "/user/keep/inventory/{colorId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Inventory> getInventory(@PathVariable("colorId") Integer colorId){
        return userService.getInventoryListByColorId(colorId);
    }

    @GetMapping(value = {"/user/orders"})
    public ModelAndView userOrders(HttpSession session){
        Integer userId = (Integer) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        if (userId == null) {
            mav.setViewName("redirect:/login_register");
            return mav;
        }
        mav = userService.getUserOrders(mav,userId);
        mav.setViewName("user/user_orders");
        return mav;
    }

    @GetMapping(value = {"/user/orders/detail/{id}"})
    public ModelAndView userOrdersDetails(@PathVariable("id") Integer detailId){
        ModelAndView mav = new ModelAndView();
        if (detailId != null){
            mav.addObject("orderDetails",userService.getOrderDetails(detailId));
        }
        mav.setViewName("user/user_order_details");
        return mav;
    }


}
