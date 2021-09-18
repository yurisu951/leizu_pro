package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.product.ColorImage;
import com.chloe.leizu_pro.bean.product.Inventory;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.bean.user.UserCollection;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    boolean addUser(User user);

    boolean addKeep(UserCollection userCollection);

    boolean loginUser(HttpSession session, String account, String password, String remember);

    List<Integer> getUserKeep(Integer userId);

    boolean removeKeep(UserCollection userCollection);

    List<Product> getColorNameByProductId(List<Integer> productIds);

    List<Inventory> getInventoryListByColorId(Integer colorId);

    User getUserProfile(Integer userId);
}
