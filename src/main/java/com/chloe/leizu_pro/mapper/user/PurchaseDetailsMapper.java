package com.chloe.leizu_pro.mapper.user;

import com.chloe.leizu_pro.bean.user.PurchaseDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PurchaseDetailsMapper {

    int addPurchaseDetails(PurchaseDetails purchaseDetails);

    int addPurchaseDetailsList(List<PurchaseDetails> recordList);

    List<PurchaseDetails> getOrderDetails(Integer cartId);

}
