package com.cheffi.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewInfoDto {

    @Schema(description = "리뷰 ID", example = "1")
    private Long id;
    @Schema(description = "리뷰 제목", example = "태초동에 생긴 맛집!!")
    private String title;
    @Schema(description = "리뷰의 적힌 본문 내용", example = "초밥 태초세트 추천해요")
    private String text;
    @Schema(description = "리뷰의 평가 총합 점수", example = "113")
    private Integer ratingCnt;
    @Schema(description = "북마크 여부", example = "true")
    private boolean bookmarked;

    @Builder
    public ReviewInfoDto(Long id,
                         String title,
                         String text,
                         Integer ratingCnt,
                         boolean bookmarked) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.ratingCnt = ratingCnt;
        this.bookmarked = bookmarked;
    }
}
