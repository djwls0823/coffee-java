package com.cafe.coffeejava.user.account;

import com.cafe.coffeejava.common.model.ResultResponse;
import com.cafe.coffeejava.user.account.model.UserSignUpReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name="유저", description = "유저 관련 API")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping
    @Operation(summary = "유저 회원가입")
    public ResultResponse<Integer> postUser(@RequestBody UserSignUpReq req) {
        int result = userAccountService.postUser(req);
        return ResultResponse.<Integer>builder()
                             .resultMsg("회원가입 완료")
                             .resultData(result)
                             .build();
    }
}
