package com.chloe.leizu_pro.mapper.user;

import com.chloe.leizu_pro.bean.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

    void addUser(User user);


}
