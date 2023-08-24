package com.cheffi.avatar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import com.cheffi.avatar.domain.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

	@Query("select a from Avatar a"
		+ " left join fetch a.photo p"
		+ " where a.id = :id")
	Optional<Avatar> findByIdWithPhoto(@Param("id") Long avatarId);

	boolean existsByNickname(@NonNull String nickname);
}
