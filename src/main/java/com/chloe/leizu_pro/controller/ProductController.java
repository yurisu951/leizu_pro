package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.bean.PromoCode;
import com.chloe.leizu_pro.service.ProductService;
import com.chloe.leizu_pro.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    PromoService promoService;

    @RequestMapping("/product")
    public String addProduct(){
        Product product = new Product();
        product.setCategory(1);
        product.setProductName("TestName");
        product.setPromo("AVC");
        product.setPromoPrice(100);
        product.setPrice(250);

        productService.addProduct(product);
        return "success";
    }








    @RequestMapping(value = "/testPrmo", method = RequestMethod.GET)
    public String testAddPromo(){
        PromoCode promo1 = new PromoCode("2P198", "限時搶購．任選2件198");
        PromoCode promo2 = new PromoCode("3P80DUP", "秋裝上市．任兩件享優惠");
        PromoCode promo3 = new PromoCode("sport", "運動一夏．任選149起");
        PromoCode promo4 = new PromoCode("sale", "超值精選．滿2件58折起");
        PromoCode promo5 = new PromoCode("1P299", "驚喜特惠．任選299");
        PromoCode promo6 = new PromoCode("D100", "換季推薦．任選330起");

        promoService.addPromotion(promo1);
        promoService.addPromotion(promo2);
        promoService.addPromotion(promo3);
        promoService.addPromotion(promo4);
        promoService.addPromotion(promo5);
        promoService.addPromotion(promo6);

        return "success";
    }
}
