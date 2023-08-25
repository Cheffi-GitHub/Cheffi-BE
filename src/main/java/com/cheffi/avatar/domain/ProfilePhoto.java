package com.cheffi.avatar.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProfilePhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String url;
	private String s3Key;
	private Long size;
	private Integer width;
	private Integer height;
	@NotNull
	private boolean isDefault;

	@Builder
	public ProfilePhoto(String url, String s3Key, Long size, Integer width, Integer height, boolean isDefault) {
		this.url = url;
		this.s3Key = s3Key;
		this.size = size;
		this.width = width;
		this.height = height;
		this.isDefault = isDefault;
	}
}
