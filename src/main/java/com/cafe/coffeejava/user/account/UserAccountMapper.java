package com.cafe.coffeejava.user.account;

import com.cafe.coffeejava.user.account.model.UserSignUpReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper {
    int insUser(UserSignUpReq req);
}
