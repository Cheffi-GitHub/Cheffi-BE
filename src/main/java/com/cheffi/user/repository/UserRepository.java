package com.cheffi.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheffi.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u"
		+ " from User u"
		+ " left join fetch u.userRoles ur"
		+ " left join fetch ur.role r"
		+ " where u.email = :email")
	Optional<User> findByEmailWithRoles(@Param("email") String email);

	@Query("select u"
		+ " from User u"
		+ " left join fetch u.userRoles ur"
		+ " left join fetch ur.role r"
		+ " where u.id = :id")
	Optional<User> findByIdWithRoles(@Param("id") Long userId);
}
