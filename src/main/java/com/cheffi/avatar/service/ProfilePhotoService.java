package com.cheffi.avatar.service;

import com.cheffi.common.domain.ImageFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.domain.ProfilePhoto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfilePhotoService {

	private static final String DEFAULT_PROFILE_PHOTO_URL = "https://cheffibucket.s3.ap-northeast-2.amazonaws.com/" +
            "profile/57f47d71-c6f2-4c9a-a628-58b9e70eb320/Cheffi_Photo_2023-08-25-13-45-55.jpeg";

	public ProfilePhoto getDefaultPhoto() {
		return new ProfilePhoto(ImageFile.externalImage(DEFAULT_PROFILE_PHOTO_URL));
	}

}
