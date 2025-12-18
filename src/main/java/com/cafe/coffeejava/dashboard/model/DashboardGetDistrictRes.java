package com.cafe.coffeejava.dashboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DashboardGetDistrictRes {
    @Schema(title = "행정구역 이름", example = "중구")
    private String districtName;
    @Schema(title = "행정구역별 게시글 수", example = "1")
    private int countFeedByDistrict;
}