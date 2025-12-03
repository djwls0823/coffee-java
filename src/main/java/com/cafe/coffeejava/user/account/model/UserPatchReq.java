package com.cafe.coffeejava.user.account.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원 계정 탈퇴시 유저 정보")
public class UserPatchReq {
    @Schema(description = "유저 pk", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;

    @Schema(description = "유저 비밀번호", example = "123!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
