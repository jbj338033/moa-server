package com.moa.domain.notice.dto.response

import com.moa.domain.notice.domain.entity.Notice
import java.time.LocalDateTime

data class NoticeResponse(
    val id: Long,
    val title: String,
    val content: String,
    val important: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun of(notice: Notice) = NoticeResponse(
            id = notice.id!!,
            title = notice.title,
            content = notice.content,
            important = notice.important,
            createdAt = notice.createdAt,
            updatedAt = notice.updatedAt,
        )
    }
}