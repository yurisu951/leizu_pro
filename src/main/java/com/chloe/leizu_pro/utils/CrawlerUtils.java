package com.chloe.leizu_pro.utils;

import com.chloe.leizu_pro.bean.product.Inventory;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.bean.user.PurchaseDetails;
import com.chloe.leizu_pro.bean.user.TradingRecord;
import com.chloe.leizu_pro.mapper.product.InventoryMapper;
import com.chloe.leizu_pro.mapper.product.ProductMapper;
import com.chloe.leizu_pro.mapper.user.PurchaseDetailsMapper;
import com.chloe.leizu_pro.mapper.user.TradingRecordMapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CrawlerUtils {

    public static void addFakeData(ProductMapper productMapper, InventoryMapper inventoryMapper, TradingRecordMapper tradingRecordMapper,
                                   PurchaseDetailsMapper purchaseDetailsMapper){
        for (int i = 5; i < 16 ;i++){
            Integer cartId = i;
            String[] genderList = new String[]{"men", "women"};
            int ranGender = (int)(Math.random()*2+1);
            if (ranGender >1) ranGender = 1;
            List<Integer> productIdList = productMapper.getRandomListByGender(genderList[ranGender]);
            List<PurchaseDetails> buyList = new ArrayList<>();
            Integer userId = (int)(Math.random()*5+2);
            String ranDate = String.valueOf((int)(Math.random()*28+1));
            Date date = Date.valueOf("2021-08-" + ranDate);
            Date buyDate = new Date(date.getTime());
            Integer totalQuantity = 0;
            Integer totalPrice = 0;

            for (Integer productId :productIdList) {
                boolean flag = (int)(Math.random()*2) > 0 ? true : false;
                if (flag){
                    List<Inventory> inventoryList = inventoryMapper.getInventoryListByProductId(productId);
                    int rand = (int)(Math.random()* inventoryList.size());
                    Inventory inventory = inventoryList.get(rand);
                    Integer inventoryId = inventory.getId();
                    Product product = productMapper.getProductById(productId);
                    Integer price = product.getPrice();
                    Integer promoPrice = product.getPromoPrice();
                    Integer discount = null;
                    Integer quantity =(int)(Math.random()* 3 +1 );
                    totalQuantity += quantity;
                    if (promoPrice != null){
                        discount = price - product.getPromoPrice();
                        totalPrice += promoPrice * quantity;
                    } else {
                        totalPrice += price * quantity;
                    }
                    PurchaseDetails pd = new PurchaseDetails(null, cartId, productId, inventoryId, price, discount,quantity);
                    buyList.add(pd);
                }
            }
            TradingRecord tradingRecord = new TradingRecord(cartId, userId, buyDate, totalQuantity, totalPrice);
            tradingRecordMapper.addTradingRecord(tradingRecord);
            purchaseDetailsMapper.addPurchaseDetailsList(buyList);
            System.out.println("OK");
        }

    }
}
