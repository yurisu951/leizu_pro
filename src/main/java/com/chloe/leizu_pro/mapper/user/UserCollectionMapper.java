package com.chloe.leizu_pro.mapper.user;

import com.chloe.leizu_pro.bean.user.UserCollection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserCollectionMapper {
    int addUserCollection(UserCollection userCollection);

    int removeUserCollection(Integer userId, Integer productId);

    List<Integer> getUserKeeps(Integer userId);

}
