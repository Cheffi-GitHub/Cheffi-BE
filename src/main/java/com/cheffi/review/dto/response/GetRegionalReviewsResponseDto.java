package com.cheffi.review.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class GetRegionalReviewsResponseDto {

    private Long id;
    private String title;
    private boolean bookmarked;

    public GetRegionalReviewsResponseDto(Long id, String title, boolean bookmarked) {
        this.id = id;
        this.title = title;
        this.bookmarked = bookmarked;
    }

    //TODO 데이터 생성시 삭제
    public static List<GetRegionalReviewsResponseDto> getMockDtoes() {

        List<GetRegionalReviewsResponseDto> result = new ArrayList<>();

        for (Long i = 1L; i < 11; i++) {

            boolean booleanValue = i % 2 == 0;
            result.add(new GetRegionalReviewsResponseDto(i, "title" + i, booleanValue));
        }

        return result;
    }
}
