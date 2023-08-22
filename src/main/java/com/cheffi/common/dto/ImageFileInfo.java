package com.cheffi.common.dto;

import lombok.Builder;

@Builder
public record ImageFileInfo(
	String url,
	String key,
	Long byteSize,
	Integer width,
	Integer height
) {

	public static ImageFileInfo of(String fileUrl, String key, ImageSize imageSize) {
		return ImageFileInfo.builder()
			.url(fileUrl)
			.key(key)
			.byteSize(imageSize.byteSize())
			.width(imageSize.width())
			.height(imageSize.height())
			.build();
	}
}
