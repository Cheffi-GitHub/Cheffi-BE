package com.cheffi.notification.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.common.dto.CursorPage;
import com.cheffi.notification.domain.Notification;
import com.cheffi.notification.dto.GetNotificationRequest;
import com.cheffi.notification.dto.NotificationDto;
import com.cheffi.notification.repository.NotificationJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class NotificationService {

	private final NotificationJpaRepository notificationJpaRepository;

	@Transactional
	public CursorPage<NotificationDto, Long> getNotifications(GetNotificationRequest request, Long avatarId) {
		List<Notification> notifications = notificationJpaRepository.findByAvatar(request, avatarId);
		List<NotificationDto> result = notifications.stream().map(NotificationDto::of).toList();

		notifications.stream()
			.filter(Notification::isUnchecked)
			.forEach(Notification::check);

		return CursorPage.of(result, request.getSize(),
			NotificationDto::id);
	}
}
