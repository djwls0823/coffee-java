package com.cafe.coffeejava.user.account;

import com.cafe.coffeejava.common.exception.CustomException;
import com.cafe.coffeejava.config.CookieUtils;
import com.cafe.coffeejava.config.constant.JwtConst;
import com.cafe.coffeejava.config.jwt.JwtTokenProvider;
import com.cafe.coffeejava.config.jwt.JwtUser;
import com.cafe.coffeejava.config.security.AuthenticationFacade;
import com.cafe.coffeejava.user.account.model.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountMapper userAccountMapper;
    private final JwtConst jwtConst;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtils  cookieUtils;
    private final AuthenticationFacade  authenticationFacade;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public int postUser(UserSignUpReq req) {
        req.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));

        if(userAccountMapper.countByEmail(req.getEmail()) > 0) {
            throw new CustomException("이미 사용중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        if (userAccountMapper.countByNickname(req.getNickname()) > 0) {
            throw new CustomException("이미 사용중인 닉네임입니다.",  HttpStatus.BAD_REQUEST);
        }

        return userAccountMapper.insUser(req);
    }

    // 로그인
    @Transactional
    public UserSignInRes loginUser(UserSignInReq req, HttpServletResponse response) {
        UserSignInRes res = userAccountMapper.selUserByEmail(req.getEmail());

        if (res == null || !BCrypt.checkpw(req.getPassword(), res.getPassword())) {
            throw new CustomException("아이디 혹은 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        if (res.getUserStatus() == 1) {
            throw new CustomException("비활성화 된 계정입니다.", HttpStatus.FORBIDDEN);
        }

        JwtUser jwtUser = new JwtUser();

        jwtUser.setSignedUserId(res.getUserId());
        jwtUser.setRoles(res.getRole());

        String accessToken = jwtTokenProvider.generateToken(jwtUser, jwtConst.getAccessTokenExpiry());
        String refreshToken = jwtTokenProvider.generateToken(jwtUser, jwtConst.getRefreshTokenExpiry());

        cookieUtils.setCookie(response, jwtConst.getRefreshTokenCookieName(), refreshToken, jwtConst.getRefreshTokenCookieExpiry());
        res.setAccessToken(accessToken);

        return res;
    }

    // 유저 탈퇴
    public int patchUser(UserPatchReq req) {
        Long loginUserId = authenticationFacade.getSignedUserId();

        if (!loginUserId.equals(req.getUserId())) {
            throw new CustomException("계정 탈퇴 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        // 유저 정보 조회
        UserPatchRes userInfo = userAccountMapper.selUserInfoByUserId(req.getUserId());

        // 비밀번호 확인
        if (!passwordEncoder.matches(req.getPassword(), userInfo.getPassword())) {
            throw new CustomException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        return userAccountMapper.updateUser(req);
    }
}
