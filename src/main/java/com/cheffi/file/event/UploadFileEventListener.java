package com.cheffi.file.event;

import com.cheffi.file.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class UploadFileEventListener {

	private final FileStorageService fileStorageService;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK, classes = UploadFileEvent.class)
	public void onRollbackAfterUploadFile(UploadFileEvent event) {
		log.error("작업에 실패해 추가된 파일을 삭제합니다. keys: {}", event.getAddedS3keys().toString());
		fileStorageService.deleteBackups(event.getAddedS3keys());
	}

}
