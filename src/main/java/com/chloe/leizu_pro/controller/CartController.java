package com.chloe.leizu_pro.controller;

import com.chloe.leizu_pro.bean.Cart;
import com.chloe.leizu_pro.service.CartService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/shop/cart", method = RequestMethod.POST)
    @ResponseBody
    public Cart addCart(HttpSession session, String product){
        JSONObject jsonObject = new JSONObject(product);
        System.out.println(jsonObject);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
        }
        Cart cartOnAjax = cartService.getCartOnAjax(jsonObject, cart);
        session.setAttribute("cart", cart);
        return cart;
    }

    @RequestMapping("/shop/getCart")
    @ResponseBody
    public Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        return cart;
    }
}
