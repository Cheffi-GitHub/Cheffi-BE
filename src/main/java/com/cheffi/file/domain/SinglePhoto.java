package com.cheffi.file.domain;

import com.cheffi.common.domain.ImageFile;

public interface SinglePhoto {

	ImageFile getFile();

	default String getKey() {
		return getFile().getS3Key();
	}

	default boolean isUploadedImage() {
		return getKey() != null;
	}

}
