package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.product.ColorImage;
import com.chloe.leizu_pro.bean.product.Inventory;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.bean.user.UserCollection;
import com.chloe.leizu_pro.mapper.product.ColorImageMapper;
import com.chloe.leizu_pro.mapper.product.InventoryMapper;
import com.chloe.leizu_pro.mapper.product.ProductMapper;
import com.chloe.leizu_pro.mapper.user.UserCollectionMapper;
import com.chloe.leizu_pro.mapper.user.UserMapper;
import com.chloe.leizu_pro.service.UserService;
import com.chloe.leizu_pro.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserCollectionMapper userCollectionMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    InventoryMapper inventoryMapper;


    @Override
    public boolean addUser(User user) {
        try {
            String password = user.getPassword();
            user.setPassword(UserUtils.pwdEncode(password));
            userMapper.addUser(user);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addKeep(UserCollection userCollection) {
        int i = userCollectionMapper.addUserCollection(userCollection);
        if (i > 0) return true;
        return false;
    }

    @Override
    public boolean loginUser(User searchUser, HttpSession session){
        User user = userMapper.getUserByEmailOrPhone(searchUser.getEmail(), searchUser.getPhone());
        System.out.println(user);
        String keyinpwd = searchUser.getPassword();
        String dbPwd = user.getPassword();
        if (UserUtils.pwdMatches(keyinpwd, dbPwd)){
            session.setAttribute("user", user.getUserId());
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> getUserKeep(Integer userId) {
        return userCollectionMapper.getUserKeeps(userId);
    }

    @Override
    public boolean removeKeep(UserCollection userCollection) {
        int i = userCollectionMapper.removeUserCollection(userCollection.getUserId(), userCollection.getProductId());
        if (i > 0) return true;
        return false;
    }

    @Override
    public List<Product> getColorNameByProductId(List<Integer> productIds) {
        return productMapper.getProductWithColorNames(productIds);
    }

    @Override
    public List<Inventory> getInventoryListByColorId(Integer colorId){
        return inventoryMapper.getInventoryListByColorId(colorId);
    }



}
