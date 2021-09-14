package com.chloe.leizu_pro.controller;

import com.chloe.leizu_pro.bean.Cart;
import com.chloe.leizu_pro.service.CartService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/shop/cart", method = RequestMethod.POST)
    @ResponseBody
    public Cart addCart(HttpSession session, String product){
        JSONObject jsonObject = new JSONObject(product);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
        }
        Cart cartOnAjax = cartService.addCartOnAjax(jsonObject, cart);
        session.setAttribute("cart", cart);
        return cart;
    }

    @RequestMapping(value = "/shop/cart/{inventoryId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeCart(HttpSession session,@PathVariable("inventoryId") String inventoryId){
        Cart cart = (Cart) session.getAttribute("cart");
        Cart cart1 = cartService.removeItemFromCart(cart, Integer.valueOf(inventoryId));
        session.setAttribute("cart", cart1);
        return "success";
    }

    @RequestMapping("/shop/getCart")
    @ResponseBody
    public Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        return cart;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView cart(HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.addObject("cart", session.getAttribute("cart"));
        mav.setViewName("cart");
        return mav;
    }

}
