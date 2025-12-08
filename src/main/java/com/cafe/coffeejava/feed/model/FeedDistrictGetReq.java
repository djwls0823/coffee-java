package com.cafe.coffeejava.feed.model;

import com.cafe.coffeejava.common.model.Paging;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedDistrictGetReq extends Paging {
    private Long districtId;

    public FeedDistrictGetReq(Integer page, Integer size) {
        super(page, size);
    }
}