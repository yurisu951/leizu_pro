package com.chloe.leizu_pro.controller;

import com.chloe.leizu_pro.bean.ColorImage;
import com.chloe.leizu_pro.bean.PromoCode;
import com.chloe.leizu_pro.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ActivitiesController {
    @Autowired
    ActivitiesService activitiesService;

    @RequestMapping(value = {"/{gender}/activites/{promoCode}/{page}",
            "/{gender}/activites/{promoCode}"})
    public ModelAndView activitesPage(@PathVariable("gender") String gender,
                                  @PathVariable("promoCode") String promoCode,
                                      @PathVariable(value = "page", required = false) Integer index){
        if (index == null) index = 1;
        ModelAndView mav = new ModelAndView();
        Map<String, List<PromoCode>> promoList = activitiesService.getPromoList(promoCode);
        mav.addObject("gender", gender);
        mav.addObject("promoList", promoList);

        List<Integer> productIdList = activitiesService.getProductIdListOnPromo(promoCode, gender);
        Integer totalPage = activitiesService.getTotalPage(productIdList);
        List<ColorImage> productList = activitiesService.getProductList(productIdList, index);

        int start = 0;
        int maxPage = 0;
        if (totalPage - index  > 5){
            start = index;
            maxPage = start + 5;
        } else {
            maxPage = totalPage;
            start = (maxPage - 5 < 0)? 1:(maxPage - 5);

        }
        mav.addObject("promoProductList", productList);
        mav.addObject("maxPage", maxPage);  // at least 1
        mav.addObject("start", start);
        mav.addObject("index", index);
        mav.addObject("promoCode", promoCode);
        mav.setViewName("product_activities");
        return mav;
    }

    @RequestMapping(value = {"/get/inventory"}, method = RequestMethod.POST,params = "id")
    @ResponseBody
    public Integer getInventory(@RequestParam("id") Integer inventoryId){
        return activitiesService.getInventory(inventoryId);
    }

}
