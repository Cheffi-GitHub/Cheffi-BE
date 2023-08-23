package com.cheffi.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheffi.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u"
		+ " from User u"
		+ " left join fetch u.avatar a"
		+ " left join fetch a.photo p"
		+ " where u.email = :email")
	Optional<User> findByEmailWithAvatar(@Param("email") String email);
}
