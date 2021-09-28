package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.Cart;
import org.json.JSONObject;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpSession;

public interface CartService {

    Cart addCartOnAjax(JSONObject obj, Cart cart);

    Cart removeItemFromCart(Cart cart, Integer inventoryId);

    Cart buyCart(MultiValueMap<String, String> params, Integer userId);

    boolean commitCart(MultiValueMap<String, String> params, HttpSession session);
}
