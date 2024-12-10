package com.moa.domain.notice.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notices")
class NoticeController {
    @Operation(summary = "공지사항 목록 조회")
    @GetMapping
    fun getNotices(@PageableDefault pageable: Pageable) = "공지사항 목록 조회"

    @Operation(summary = "공지사항 상세 조회")
    @GetMapping("/{noticeId}")
    fun getNotice(@PathVariable noticeId: Long) = "공지사항 상세 조회"
}