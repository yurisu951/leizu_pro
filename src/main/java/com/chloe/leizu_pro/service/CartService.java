package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.Cart;
import org.json.JSONObject;

public interface CartService {

    Cart addCartOnAjax(JSONObject obj, Cart cart);

    Cart removeItemFromCart(Cart cart, Integer inventoryId);
}
