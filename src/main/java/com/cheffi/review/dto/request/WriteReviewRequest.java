package com.cheffi.review.dto.request;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter @Setter
public class WriteReviewRequest {

    @NotBlank
    @Schema(description = "식당 이름")
    private String restaurantName;
    @NotBlank
    @Schema(description = "식당 법정주소")
    private int addressCode;
    @NotBlank
    @Schema(description = "리뷰 제목")
    private String title;
    @NotBlank
    @Schema(description = "리뷰 내용")
    private String text;

    @Schema(description = "사진")
    private List<MultipartFile> files;
    @NotBlank
    @Schema(description = "메뉴종류, 가격")
    private Map<String, String> foodInfo;

    @Builder
    public WriteReviewRequest(String restaurantName,
                                 int addressCode,
                                 String title,
                                 String text,
                                 List<MultipartFile> files,
                                 Map<String, String> foodInfo) {

        this.restaurantName = restaurantName;
        this.addressCode = addressCode;
        this.title = title;
        this.text = text;
        this.files = files;
        this.foodInfo = foodInfo;
    }
}
