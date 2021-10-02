package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.product.Inventory;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.PurchaseDetails;
import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.bean.user.UserCollection;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {

    String checkPwd(Integer userId, String password);

    boolean addUser(User user);

    boolean addKeep(UserCollection userCollection);

    boolean loginUser(HttpSession session, String account, String password, String remember);

    List<Integer> getUserKeep(Integer userId);

    boolean removeKeep(UserCollection userCollection);

    List<Product> getColorNameByProductId(List<Integer> productIds);

    List<Inventory> getInventoryListByColorId(Integer colorId);

    User getUserProfile(Integer userId);

    boolean updateUserProfile(Map<String,String> params,Integer userId);

    ModelAndView getUserOrders(ModelAndView mav, Integer userId);

    List<PurchaseDetails> getOrderDetails(Integer cartId);

    boolean checkEmailRegister(String email);

    String changePassword(String email, String password);


}
