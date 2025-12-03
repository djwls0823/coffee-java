package com.cafe.coffeejava.user.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPatchRes {
    private Long userId;
    private String email;
    private String password;     // 비밀번호 비교를 위해 필요
    private String name;
    private String nickname;
    private String userPic;
    private Integer userStatus;  // 0=정상, 1=탈퇴 등
}