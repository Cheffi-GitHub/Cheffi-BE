package com.cheffi.file.domain;


import com.cheffi.common.domain.ImageFile;

public interface MultiPhoto {

	ImageFile getFile();

	default String getKey() {
		return getFile().getS3Key();
	}

}
