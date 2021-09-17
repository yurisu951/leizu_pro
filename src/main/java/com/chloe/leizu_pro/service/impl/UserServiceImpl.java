package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.mapper.user.UserMapper;
import com.chloe.leizu_pro.service.UserService;
import com.chloe.leizu_pro.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

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
}
