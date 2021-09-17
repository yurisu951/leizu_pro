package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.bean.user.UserCollection;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    boolean addUser(User user);

    boolean addKeep(UserCollection userCollection);

    boolean loginUser(User user, HttpSession session);

    List<Integer> getUserKeep(Integer userId);

    boolean removeKeep(UserCollection userCollection);
}
