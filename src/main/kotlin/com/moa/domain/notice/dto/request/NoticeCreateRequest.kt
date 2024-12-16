package com.moa.domain.notice.dto.request

data class NoticeCreateRequest(
    val title: String,
    val content: String,
    val important: Boolean
)