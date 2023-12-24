package com.cheffi.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import com.cheffi.notification.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query("""
		select n
		from Notification n
		where n.avatar.id = :avatar
		""")
	List<Notification> findAllByAvatar(@Param("avatar") Long avatarId);

	@Query("""
		select n
		from Notification n
		where n.avatar.id = :avatar
		and n.id in :ids
		""")
	List<Notification> findByAvatarAndId(@Param("avatar") Long avatarId, @Param("ids") List<Long> ids);

	@Query("""
		select (count(n) > 0)
		from Notification n
		where n.checked = false
		and n.avatar.id = :avatar
		""")
	boolean existsUncheckedByAvatar(@Param("avatar") @NonNull Long avatar);
}
