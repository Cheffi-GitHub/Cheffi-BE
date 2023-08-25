package com.cheffi.common.dto;

import com.cheffi.avatar.domain.ProfilePhoto;

import lombok.Builder;

@Builder
public record ImageFileInfo(
	String url,
	String S3key,
	Long byteSize,
	Integer width,
	Integer height
) {

	public static ImageFileInfo of(String fileUrl, String s3Key, ImageSize imageSize) {
		return ImageFileInfo.builder()
			.url(fileUrl)
			.S3key(s3Key)
			.byteSize(imageSize.byteSize())
			.width(imageSize.width())
			.height(imageSize.height())
			.build();
	}

	private static final String DEFAULT_URL = "https://cheffibucket.s3.ap-northeast-2.amazonaws.com"
		+ "/profile/57f47d71-c6f2-4c9a-a628-58b9e70eb320/Cheffi_Photo_2023-08-25-13-45-55.jpeg";
	private static final Long DEFAULT_SIZE = 50_000L;
	private static final Integer DEFAULT_WIDTH = 1440;
	private static final Integer DEFAULT_HEIGHT = 1440;

	public static ProfilePhoto getDefaultPhoto() {
		return ProfilePhoto.builder()
			.url(DEFAULT_URL)
			.s3Key(null)
			.size(DEFAULT_SIZE)
			.width(DEFAULT_WIDTH)
			.height(DEFAULT_HEIGHT)
			.isDefault(true)
			.build();
	}

	public ProfilePhoto toProfilePhoto() {
		return ProfilePhoto.builder()
			.url(url)
			.s3Key(S3key)
			.size(byteSize)
			.width(width)
			.height(height)
			.isDefault(false)
			.build();
	}

}
