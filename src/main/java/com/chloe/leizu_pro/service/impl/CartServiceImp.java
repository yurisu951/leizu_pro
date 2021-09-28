package com.chloe.leizu_pro.service.impl;

import com.chloe.leizu_pro.bean.Cart;
import com.chloe.leizu_pro.bean.product.Inventory;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.PurchaseDetails;
import com.chloe.leizu_pro.bean.user.TradingRecord;
import com.chloe.leizu_pro.bean.user.User;
import com.chloe.leizu_pro.mapper.product.InventoryMapper;
import com.chloe.leizu_pro.mapper.product.ProductMapper;
import com.chloe.leizu_pro.mapper.user.PurchaseDetailsMapper;
import com.chloe.leizu_pro.mapper.user.TradingRecordMapper;
import com.chloe.leizu_pro.mapper.user.UserMapper;
import com.chloe.leizu_pro.service.CartService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TradingRecordMapper tradingRecordMapper;
    @Autowired
    PurchaseDetailsMapper purchaseDetailsMapper;

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

    @Override
    public Cart buyCart(MultiValueMap<String, String> params, Integer userId) {
        Set<String> keys = params.keySet();
        List<String> selectedProduct = new ArrayList<>(params.get("selectedProduct"));
        List<PurchaseDetails> result = new ArrayList<>();
        Integer totalQuantity = 0;
        Integer totalPrice = 0;
        Integer cartId = tradingRecordMapper.getNewTradingCode() + 1;

        for (String key : keys){
            if (selectedProduct.contains(key)){
                List<String> list = params.get(key);
                Integer inventoryId = Integer.valueOf(list.get(0));
                Integer quantity = Integer.valueOf(list.get(1));
                Inventory inventory = inventoryMapper.getInventoryInfo(inventoryId);
                PurchaseDetails purchaseDetails = new PurchaseDetails();
                purchaseDetails.setId(null);
                purchaseDetails.setCartId(cartId);
                purchaseDetails.setInventoryId(inventoryId);
                purchaseDetails.setProductId(inventory.getProductInfo().getId());
                Integer price = inventory.getProductInfo().getPrice();
                purchaseDetails.setPrice(price);
                Integer promoPrice = inventory.getProductInfo().getPromoPrice();
                purchaseDetails.setDiscount((promoPrice!=null) ?price-promoPrice:null);
                purchaseDetails.setQuantity(quantity);
                totalQuantity += quantity;

                result.add(purchaseDetails);
            }
            if ("totalPrice".equals(key)){
                totalPrice = Integer.valueOf(params.get(key).get(0));
            }
        }
        TradingRecord tradingRecord = new TradingRecord();
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        tradingRecord.setCartId(cartId);
        tradingRecord.setUserId(userId);
        tradingRecord.setBuyDate(today);
        tradingRecord.setTotalPrice(totalPrice);
        tradingRecord.setTotalQuantity(totalQuantity);
        tradingRecord.setStatus(null);

        try {
            tradingRecordMapper.addTradingRecord(tradingRecord);
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Cart cart = new Cart();
            cart.setCartId(null);
            cart.setUserId(userId);
            cart.setPurchaseDetails(result);
            cart.setTradingRecord(tradingRecord);
            return cart;
        }
    }

    @Override
    public boolean commitCart(MultiValueMap<String, String> params, HttpSession session) {
        User userOrigin = userMapper.getUserProfileById(Integer.valueOf(params.get("userId").get(0)));
        // 更新会员资料
        userOrigin.setUserName(params.get("username").get(0));
        userOrigin.setPhone(params.get("userPhone").get(0));
        userOrigin.setAddress(params.get("userAddress").get(0));
        userMapper.updateUser(userOrigin);

        // 确定购买的内容
        Cart buyCart = (Cart) session.getAttribute("buyCart");



        List<PurchaseDetails> purchaseDetails = buyCart.getPurchaseDetails();
        List<Integer> pdList = new ArrayList<>();
        for (PurchaseDetails pd :purchaseDetails) {
            pdList.add(pd.getInventoryId());
        }

        try {
                purchaseDetailsMapper.addPurchaseDetailsList(purchaseDetails);

                // 需要删除session的购物车（已购买部分）
                Cart cart = (Cart) session.getAttribute("cart");
                for (Integer invenId: pdList) {
                    removeItemFromCart(cart, invenId);
                }

        } catch (Exception e){
            System.out.println(e);
            return false;
        } finally {

        }

        return true;
    }



}
