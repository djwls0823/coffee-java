package com.cafe.coffeejava.user.mypage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myPage")
@RequiredArgsConstructor
@Tag(name="마이 페이지", description = "마이 페이지 관련 API")
public class UserMyPageController {
}
