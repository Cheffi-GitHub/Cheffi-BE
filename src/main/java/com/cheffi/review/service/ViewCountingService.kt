package com.cheffi.review.service

import com.cheffi.event.event.ReviewReadEvent
import com.cheffi.notification.dto.MessageResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.lang.Thread.sleep

@Service
class ViewCountingService(
    private val webClient: WebClient
) {

    companion object {
        const val VIEW_COUNT_REQUEST_URL = "http://localhost:8081/reviews/count"
    }

    fun requestViewCount(
        event: ReviewReadEvent,
    ): MessageResponse {
        var message: String = "Default Message"
        var attempt = 0

        while (attempt < 3) {
            try {
                val result = webClient.post()
                    .uri(VIEW_COUNT_REQUEST_URL)
                    .bodyValue(event)
                    .retrieve()
                    .bodyToMono<String>().block()
                return result?.let { MessageResponse.success(it) }
                    ?: throw RuntimeException("응답 값이 Null 입니다.")
            } catch (e: Exception) {
                message = e.message.toString()
                sleep(1000L * (attempt + 1))
                attempt++
            }
        }

        return MessageResponse.fail(message)
    }


}
