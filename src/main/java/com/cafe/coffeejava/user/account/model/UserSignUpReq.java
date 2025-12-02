package com.cafe.coffeejava.user.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpReq {
    @JsonIgnore
    private long userId;
    @Schema(title = "유저 아이디", example = "aaa@aaa.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,10}$", message = "유효하지 않은 형식의 이메일입니다.")
    private String email;
    @Schema(title = "유저 비밀번호", example = "123!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Schema(title = "유저 이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(title = "유저 닉네임", example = "나는홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;
}
