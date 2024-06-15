package com.cheffi.event.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.cheffi.event.event.ReviewReadEvent;
import com.cheffi.review.service.ViewHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ReviewReadEventListener {

	private final ViewHistoryService viewHistoryService;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = ReviewReadEvent.class)
	public void onReadingSaveViewHistory(ReviewReadEvent event) {
		viewHistoryService.saveViewHistory(event);
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = ReviewReadEvent.class)
	public void onReadingIncreaseViewCount(ReviewReadEvent event) {
		viewHistoryService.increaseViewCount(event);
	}

}
