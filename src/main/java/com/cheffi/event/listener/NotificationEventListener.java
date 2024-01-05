package com.cheffi.event.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.cheffi.event.event.ReviewCreateEvent;
import com.cheffi.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NotificationEventListener {

	private final NotificationService notificationService;

	/**
	 * `@Async` 를 사용하거나 `JDBC`를 사용한다면
	 * `@Transactional(propagation = Propagation.REQUIRES_NEW)`가 없어도 Insert 가 된다.
	 */
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = ReviewCreateEvent.class)
	public void onReviewCreation(ReviewCreateEvent event) {
		notificationService.notifyFollowersOfReviewCreation(event.getWriterId(), event.getNickname());
	}
}
