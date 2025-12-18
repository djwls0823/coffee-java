package com.cafe.coffeejava.dashboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DashboardGetReportCount {
    @Schema(title = "신고 유형 ID", example = "1")
    private long reportTypeId;
    @Schema(title = "신고 유형", example = "스팸/광고")
    private String reportTypeName;
    @Schema(title = "유형별 신고 수", example = "1")
    private int reportCount;
}