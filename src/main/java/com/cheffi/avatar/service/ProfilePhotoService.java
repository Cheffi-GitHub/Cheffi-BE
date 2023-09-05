package com.cheffi.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.domain.ProfilePhoto;
import com.cheffi.avatar.repository.ProfilePhotoRepository;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.constant.S3RootPath;
import com.cheffi.common.dto.ImageFileInfo;
import com.cheffi.common.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfilePhotoService {
	private final ProfilePhotoRepository profilePhotoRepository;
	private final FileUploadService fileUploadService;

	@Transactional
	public String deletePhotoFromDB(Avatar avatar) {
		ProfilePhoto photo = avatar.getPhoto();
		String s3key = photo.getS3Key();
		profilePhotoRepository.delete(photo);

		return s3key;
	}

	@Transactional
	public ProfilePhoto addPhoto(Avatar avatar, MultipartFile file) {
		ImageFileInfo imageFileInfo = fileUploadService.uploadImageToS3(file, S3RootPath.PROFILE_PHOTO);

		ProfilePhoto photo;
		try {
			photo = profilePhotoRepository.save(imageFileInfo.toProfilePhoto());
			avatar.changePhoto(photo);
		} catch (Exception e) {
			deletePhotoFromS3(imageFileInfo.S3key());
			throw new BusinessException(e.getMessage());
		}

		return photo;
	}

	@Transactional
	public ProfilePhoto addDefaultPhoto(Avatar avatar) {
		ProfilePhoto photo = profilePhotoRepository.save(ImageFileInfo.getDefaultPhoto());
		avatar.changePhoto(photo);
		return photo;
	}

	public void deletePhotoFromS3(String s3key) {
		if (s3key == null)
			return;
		fileUploadService.removeFromS3(s3key);
	}

}
