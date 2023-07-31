package com.cheffi.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RatingInfoDto {

    @Schema(description = "평가 타입", example = "GOOD")
    private String ratingType;
    @Schema(description = "해당 타입의 총합 점수", example = "30")
    private int totalScore;

    public RatingInfoDto(String ratingType, int totalScore) {
        this.ratingType = ratingType;
        this.totalScore = totalScore;
    }

}
