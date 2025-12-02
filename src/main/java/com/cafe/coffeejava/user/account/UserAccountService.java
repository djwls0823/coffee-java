package com.cafe.coffeejava.user.account;

import com.cafe.coffeejava.user.account.model.UserSignUpReq;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountMapper userAccountMapper;

    public int postUser(UserSignUpReq req) {
        req.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));

        return userAccountMapper.insUser(req);
    }
}
