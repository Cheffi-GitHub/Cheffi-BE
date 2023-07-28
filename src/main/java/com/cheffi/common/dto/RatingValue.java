package com.cheffi.common.dto;

import com.cheffi.review.domain.Rating;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RatingValue {

    private String  ratingType;
    private int     totalScore;

    public RatingValue(String ratingType, int totalScore) {
        this.ratingType = ratingType;
        this.totalScore = totalScore;
    }

    //TODO 데이터 생기면 삭제
    public static List<RatingValue> getMockRatingValues() {

        List<RatingValue> result = new ArrayList<>();

        result.add(new RatingValue("GOOD", 30));
        result.add(new RatingValue("Bad", -7));

        return result;
    }
}
