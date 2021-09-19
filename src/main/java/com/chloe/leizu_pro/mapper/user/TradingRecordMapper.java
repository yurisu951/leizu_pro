package com.chloe.leizu_pro.mapper.user;

import com.chloe.leizu_pro.bean.user.TradingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TradingRecordMapper {
    int addTradingRecord(TradingRecord tradingRecord);
    int addTradingRecordList(List<TradingRecord> records);

    List<TradingRecord> getUserOrders(Integer userId);

    Integer getUserOrdersLength(Integer userId);

}
