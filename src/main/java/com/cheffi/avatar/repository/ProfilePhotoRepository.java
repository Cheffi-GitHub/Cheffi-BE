package com.cheffi.avatar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheffi.avatar.domain.ProfilePhoto;

public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Long> {
}
