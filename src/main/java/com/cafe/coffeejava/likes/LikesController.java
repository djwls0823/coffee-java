package com.cafe.coffeejava.likes;

import com.cafe.coffeejava.common.model.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@Tag(name="좋아요", description = "좋아요 관련 API")
public class LikesController {
    private final LikeService likeService;

    @PostMapping("/{feedId}")
    @Operation(summary = "좋아요 등록 and 삭제")
    public ResultResponse<Boolean> LikesToggle(@PathVariable long feedId) {
        boolean result = likeService.LikesToggle(feedId);

        return ResultResponse.<Boolean>builder()
                             .statusCode(String.valueOf(HttpServletResponse.SC_OK))
                             .resultMsg(result ? "좋아요 등록 성공" : "좋아요 삭제 성공")
                             .resultData(result)
                             .build();
    }
}
