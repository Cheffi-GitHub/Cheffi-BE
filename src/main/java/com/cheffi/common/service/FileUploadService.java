package com.cheffi.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cheffi.common.config.exception.business.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileUploadService {

	private final String bucketName;
	private final AmazonS3 amazonS3;

	public FileUploadService(@Value("${cloud.aws.s3.bucket}") String bucketName,
		AmazonS3 amazonS3) {
		this.bucketName = bucketName;
		this.amazonS3 = amazonS3;
	}

	public String uploadFileToS3(MultipartFile file) {
		try (InputStream inputStream = file.getInputStream()) {
			String fileName = file.getOriginalFilename();
			String key = UUID.randomUUID() + "/" + fileName;

			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());
			metadata.setContentType(file.getContentType());

			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);

			amazonS3.putObject(putObjectRequest);

			return amazonS3.getUrl(bucketName, key).toString();
		} catch (IOException e) {
			log.warn(e.toString());
			throw new BusinessException(e.getMessage());
		}
	}
}
