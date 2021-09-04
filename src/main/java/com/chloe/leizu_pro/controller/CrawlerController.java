package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.bean.ContentImage;
import com.chloe.leizu_pro.bean.Inventory;
import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.service.ColorImageService;
import com.chloe.leizu_pro.service.ContentImageService;
import com.chloe.leizu_pro.service.InventoryService;
import com.chloe.leizu_pro.service.ProductService;
import com.chloe.leizu_pro.utils.Crawler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/test")
public class CrawlerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ColorImageService colorImageService;

    @Autowired
    private ContentImageService contentImageService;

    @Autowired
    private InventoryService inventoryService;



//    @RequestMapping(method = RequestMethod.GET, value = "/addProductAndColor")
    public ModelAndView addProductAndColorByCrawler() throws IOException {
        JSONArray list = Crawler.getIdList();
        JSONArray newList = new JSONArray();

        for(int i = 0; i < list.size(); i++){
            JSONObject item = (JSONObject)list.get(i);
            String pathId = (String) item.get("pathId");
            String id = (String) item.get("id");
            Integer productId = Integer.valueOf(id);
            String category = (String) item.get("categoryNum");
            String productName = (String) item.get("name");
            String price = (String) item.get("price");
            String promoCode = (String) item.get("promoCode");
            String promoPrice = (String) item.get("promoPrice");
            String productImage = (String) item.get("mainImageUrl");
            String colorId = (String) item.get("product_color_id");
            String colorImage = (String) item.get("product_color_url");
            String colorName = productName.substring(productName.lastIndexOf("-")+1);
            productName = productName.substring(0, productName.lastIndexOf("-"));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pathId", pathId);
            newList.add(jsonObject);


            Product product = new Product();
            product.setId(productId);
            product.setCategory(Integer.valueOf(category));
            product.setProductName(productName);
            product.setPrice(Integer.valueOf(price));
            product.setPromo(promoCode);
            product.setPromoPrice(Integer.valueOf(promoPrice));
            ColorImage colorImage1 = new ColorImage();
            colorImage1.setId(Integer.valueOf(colorId));
            colorImage1.setProductId(productId);
            colorImage1.setProductColor(colorName);
            colorImage1.setProductColorImage(colorImage);
            colorImage1.setProductImage(productImage);

            try {
                productService.addProductByCrawler(product);
            } catch (Exception e){
                System.out.println(e.toString());
            }
            try {
                colorImageService.addColorImageByCrawler(colorImage1);
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
//        modelAndView.addObject("list", newList);
        return modelAndView;
    }


//    @RequestMapping(value = "/addContentImageAndInventory")
    public String addContentImage() throws IOException {
        List<Integer> colorIamgeIds = colorImageService.getColorIamgeIds();
        JSONArray productDetailsOnlyImg = Crawler.getProductDetailsOnlyImg(colorIamgeIds);
        for(int i = 0; i < productDetailsOnlyImg.size(); i++){
            JSONObject obj = (JSONObject) productDetailsOnlyImg.get(i);
            String id7Word = (String) obj.get("id7Word");
            JSONArray content = (JSONArray) obj.get("content");
            Integer colorId = Integer.valueOf(id7Word);
            Integer productId = Integer.valueOf(id7Word.substring(0,5));

            Inventory inventory = new Inventory();
            inventory.setId(Integer.valueOf(id7Word+"1"));
            inventory.setProductId(productId);
            inventory.setSize("S");
            inventory.setColor(colorId);
            inventory.setQuantity((int)(Math.random()*50+1));

            Inventory inventory2 = new Inventory();
            inventory2.setId(Integer.valueOf(id7Word+"2"));
            inventory2.setProductId(productId);
            inventory2.setSize("M");
            inventory2.setColor(colorId);
            inventory2.setQuantity((int)(Math.random()*50+1));

            Inventory inventory3 = new Inventory();
            inventory3.setId(Integer.valueOf(id7Word+"3"));
            inventory3.setProductId(productId);
            inventory3.setSize("L");
            inventory3.setColor(colorId);
            inventory3.setQuantity((int)(Math.random()*50+1));

            Inventory inventory4 = new Inventory();
            inventory4.setId(Integer.valueOf(id7Word+"4"));
            inventory4.setProductId(productId);
            inventory4.setSize("XL");
            inventory4.setColor(colorId);
            inventory4.setQuantity((int)(Math.random()*50+1));

            try {
                inventoryService.addInventoryByCrawler(inventory);
                inventoryService.addInventoryByCrawler(inventory2);
                inventoryService.addInventoryByCrawler(inventory3);
                inventoryService.addInventoryByCrawler(inventory4);
            } catch (Exception e){
                System.out.println(e.toString());
            }

            for(int j = 0; j < content.size(); j++){
                String imageUrl = (String) content.get(j);
                ContentImage contentImage = new ContentImage();
                contentImage.setProductId(productId);
                contentImage.setImage(imageUrl);

                try {
                    contentImageService.addContentImageByCrawler(contentImage);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

        }

        return "success";
    }



}
