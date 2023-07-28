package com.cheffi.common.dto;

import com.cheffi.common.constant.DetailedAddress;
import com.cheffi.review.domain.Restaurant;
import lombok.Getter;

/**
 * RestaurantValue 보여줄 값만 정의한 InnerClass
 */
@Getter
public class RestaurantValue {

    private String detail;
    private String town;
    private String city;
    private String province;
    private String name;

    public RestaurantValue(String restaurantName,
                           DetailedAddress detailedAddress) {

        this.detail = restaurantName;
        this.town = detailedAddress.getProvince();
        this.city = detailedAddress.getCity();
        this.province = detailedAddress.getTown();
        this.name = detailedAddress.getDetail();
    }

    //TODO 데이터 생기면 삭제
    public static RestaurantValue getMockRestaurantValue() {

        return new RestaurantValue();
    }

    //TODO 데이터 생기면 삭제
    public RestaurantValue() {
        this.detail = "맛집 상가명";
        this.town = "시/도";
        this.city = "구/시/군";
        this.province = "동/읍/면/리";
        this.name = "상세주소";
    }
}
