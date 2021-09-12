package com.chloe.leizu_pro.service;

import com.chloe.leizu_pro.bean.Cart;
import org.json.JSONObject;

public interface CartService {

    Cart getCartOnAjax(JSONObject obj, Cart cart);
}
