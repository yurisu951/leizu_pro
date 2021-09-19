package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.product.ColorImage;
import com.chloe.leizu_pro.bean.product.Inventory;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.PurchaseDetails;
import com.chloe.leizu_pro.bean.user.TradingRecord;
import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.bean.user.UserCollection;
import com.chloe.leizu_pro.mapper.product.ColorImageMapper;
import com.chloe.leizu_pro.mapper.product.InventoryMapper;
import com.chloe.leizu_pro.mapper.product.ProductMapper;
import com.chloe.leizu_pro.mapper.user.PurchaseDetailsMapper;
import com.chloe.leizu_pro.mapper.user.TradingRecordMapper;
import com.chloe.leizu_pro.mapper.user.UserCollectionMapper;
import com.chloe.leizu_pro.mapper.user.UserMapper;
import com.chloe.leizu_pro.service.UserService;
import com.chloe.leizu_pro.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
    @Autowired
    TradingRecordMapper tradingRecordMapper;
    @Autowired
    PurchaseDetailsMapper purchaseDetailsMapper;


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
    public boolean loginUser(HttpSession session, String account, String password,String remember){
        String email = null;
        String phone = null;
        if (account.contains("@")){
            email = account;
        } else {
            phone = account;
        }
        User user = userMapper.getUserByEmailOrPhone(email, phone);

        String dbPwd = user.getPassword();
        if (UserUtils.pwdMatches(password, dbPwd)){
            session.setAttribute("user", user.getUserId());
            if ("yes".equals(remember)){
                User temp = new User(null,password,null, email,phone, null);
                session.setAttribute("remember", temp);
            } else {
                session.removeAttribute("remember");
            }
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

    @Override
    public User getUserProfile(Integer userId) {
        return userMapper.getUserProfileById(userId);
    }

    @Override
    public boolean updateUserProfile(Map<String, String> params, Integer userId) {
        String userName = params.get("userName").trim();
        String email = params.get("email").trim();
        String password = params.get("password").trim();
        String address = params.get("address").trim();
        String phone = params.get("phone").trim();
        User user = userMapper.getUserProfileById(userId);
        if (userName!= null && !("".equals(userName))) user.setUserName(userName);

        if (email!= null && !("".equals(email))) user.setEmail(email);

        if (password!= null && !("".equals(password))){
            user.setPassword(UserUtils.pwdEncode(password));
        };

        if (address!= null && !("".equals(address))) user.setAddress(address);

        if (phone!= null && !("".equals(phone))) user.setPhone(phone);

        int i = userMapper.updateUser(user);
        if (i>0) return true;
        return false;
    }

    @Override
    public ModelAndView getUserOrders(ModelAndView mav, Integer userId){
        mav.addObject("userOrders", tradingRecordMapper.getUserOrders(userId));
        mav.addObject("orderLength", tradingRecordMapper.getUserOrdersLength(userId));
        return mav;
    }


    @Override
    public List<PurchaseDetails> getOrderDetails(Integer cartId) {
        return purchaseDetailsMapper.getOrderDetails(cartId);
    }

    @Override
    public String checkPwd(Integer userId, String password){
        User userById = userMapper.getUserProfileById(userId);
        if(UserUtils.pwdMatches(password, userById.getPassword())) return "true";
        return "false";
    }



}
