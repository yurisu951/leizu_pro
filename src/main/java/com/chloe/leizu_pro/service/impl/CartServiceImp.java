package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.Cart;
import com.chloe.leizu_pro.bean.Inventory;
import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.mapper.InventoryMapper;
import com.chloe.leizu_pro.mapper.ProductMapper;
import com.chloe.leizu_pro.service.CartService;
import com.chloe.leizu_pro.service.ProductService;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    InventoryMapper inventoryMapper;

    @Override
    public Cart addCartOnAjax(JSONObject obj, Cart cart) {
        String inventoryId = (String) obj.get("id");
        Integer buyNumber = Integer.valueOf((String) obj.get("buyNumber"));
        Integer productId = Integer.valueOf(inventoryId.substring(0, 5));
        Product product = productMapper.getProductById(productId);
        Inventory inventory = inventoryMapper.getInventoryById(Integer.valueOf(inventoryId));
        List<Inventory> otherInventories = inventoryMapper.getInventoryListByProductId(productId);

        product.setBuyNumber(buyNumber);
        product.setThisProductInfo(inventory);
        product.setOtherProductsInfo(otherInventories);

        List<Product> productList = cart.getProductList();
        if (productList == null) {
            productList = new ArrayList<>();
            cart.setProductList(productList);
        }
        productList.add(product);
        return cart;
    }

    @Override
    public Cart removeItemFromCart(Cart cart, Integer inventoryId) {
        List<Product> productList = cart.getProductList();
        for (Product product:productList) {
            if (product.getThisProductInfo().getId().equals(inventoryId)){
                productList.remove(product);
                break;
            }
        }
        return cart;
    }
}
