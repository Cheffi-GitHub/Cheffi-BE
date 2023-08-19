package com.cheffi.avatar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheffi.avatar.domain.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
