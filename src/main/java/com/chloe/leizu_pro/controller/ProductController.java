package com.chloe.leizu_pro.controller;


import com.chloe.leizu_pro.bean.product.ColorImage;
import com.chloe.leizu_pro.bean.product.Product;
import com.chloe.leizu_pro.service.ProductService;
import com.chloe.leizu_pro.service.SearchService;
import com.chloe.leizu_pro.service.UserService;
import com.chloe.leizu_pro.utils.ProductPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    SearchService searchService;
    @Autowired
    UserService userService;



    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/women")
    public ModelAndView womenMainPage(){
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryMainPage(mav, productService, "women");
    }

    @RequestMapping("/men")
    public ModelAndView menMainPage(){
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryMainPage(mav, productService, "men");
    }

    @RequestMapping(value = {"/men/products/{id}"},method = RequestMethod.GET)
    public ModelAndView menCategoryPage(@PathVariable("id") Integer categoryId) {
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryListPage(mav, productService, "men", categoryId);
    }

    @RequestMapping(value = {"/women/products/{id}"},method = RequestMethod.GET)
    public ModelAndView womenCategoryPage(@PathVariable("id") Integer categoryId){
        ModelAndView mav = new ModelAndView();
        return ProductPageUtils.categoryListPage(mav, productService, "women", categoryId);
    }

    @RequestMapping(value = {"/men/details/{id}","/men/details/{id}/{color}"}, method = RequestMethod.GET)
    public ModelAndView productDetailsMenPage(HttpSession session,@PathVariable("id") Integer productId,
                                              @PathVariable(value = "color", required = false) String colorName){
        ModelAndView mav = new ModelAndView();
        ProductPageUtils.productDetailsPage(mav, productService, "men", productId, colorName);
        Integer userid = (Integer) session.getAttribute("user");
        if (userid != null){
            mav.addObject("keepList", userService.getUserKeep(userid));
        }
        return mav;
    }

    @RequestMapping(value = {"/women/details/{id}","/women/details/{id}/{color}"}, method = RequestMethod.GET)
    public ModelAndView productDetailsWomenPage(HttpSession session, @PathVariable("id") Integer productId,
                                                @PathVariable(value = "color", required = false) String colorName){
        ModelAndView mav = new ModelAndView();
        ProductPageUtils.productDetailsPage(mav, productService, "women", productId, colorName);
        Integer userid = (Integer) session.getAttribute("user");
        if (userid != null){
            mav.addObject("keepList", userService.getUserKeep(userid));
        }
        return mav;
    }

    @RequestMapping(value = "/load/{gender}", method = RequestMethod.POST)
    @ResponseBody
    public List<Product> loadMoreProduct(@PathVariable("gender") String gender){
        int  indexRan = (int)(Math.random()*50+1);
        List<Product> productListByLimit = productService.getProductListByLimit( indexRan, 20, gender);
        return productListByLimit;
    }

    @RequestMapping(value = {"/search", "/search/{gender}"},method = RequestMethod.GET)
    public ModelAndView searchPage(@RequestParam(value = "keyWord", required = false) String keyWord,
                                   @RequestParam(value = "keyWords", required = false) String keyWords,
                                   @PathVariable(value = "gender", required = false)String gender){
        ModelAndView mav = new ModelAndView();
        List<ColorImage> productList = null;
        Integer maxPage = 0;
        List<Integer> ids = null;

        if (keyWord != null){
            productList = searchService.getListByIds(keyWord);
            keyWords =keyWord;
        } else {
            if (keyWords != null && !(keyWords.trim().equals(""))){
                ids = searchService.getIdListByKeyword(keyWords ,gender);
                maxPage = searchService.getMaxPageFronIdList(ids);
                productList = searchService.getListLimit(ids, 0);
                maxPage = maxPage /20 +(maxPage%20>0? 1:0);
            } else {
                productList = searchService.getRandList(gender);
                maxPage = 20;
            }
        }


        mav.addObject("productList", productList);
        mav.addObject("maxPage", maxPage);
        mav.addObject("list", ids);
        mav.addObject("keyWords", keyWords);
        mav.addObject("gender",gender);
        mav.setViewName("product/product_search");
        return mav;
    }

    @PostMapping(value = {"/load/search"})
    @ResponseBody
    public List<ColorImage> LoadSearchResult(@RequestBody Map<String, Object> params){
        List<Integer> ids = (List<Integer>) params.get("productList");
        Integer maxPage = (Integer) params.get("maxPage");
        Integer index = (Integer) params.get("index");
        List<ColorImage> productList = null;
        if (index < maxPage) {
            productList = searchService.getListLimit(ids, index * 20);
        }
        return productList;
    }

    @PostMapping(value = {"/load/search/r"})
    @ResponseBody
    public List<ColorImage> LoadRandSearch(@RequestBody Map<String, Object> params){
        String gender = (String) params.get("gender");
        return searchService.getRandList(gender);
    }


}
