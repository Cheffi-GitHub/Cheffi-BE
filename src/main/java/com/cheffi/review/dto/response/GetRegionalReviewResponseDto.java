package com.cheffi.review.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class GetRegionalReviewResponseDto {

    private Long id;
    private String title;
    private boolean bookmarked;

    public GetRegionalReviewResponseDto(Long id, String title, boolean bookmarked) {
        this.id = id;
        this.title = title;
        this.bookmarked = bookmarked;
    }

    //TODO 데이터 생성시 삭제
    public static List<GetRegionalReviewResponseDto> getMockDtoes() {

        List<GetRegionalReviewResponseDto> result = new ArrayList<>();

        for (Long i = 1L; i < 11; i++) {

            boolean booleanValue = i % 2 == 0;
            result.add(new GetRegionalReviewResponseDto(i, "title" + i, booleanValue));
        }

        return result;
    }
}
