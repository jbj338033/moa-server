package com.moa.domain.notice.controller

import com.moa.domain.notice.dto.request.NoticeCreateRequest
import com.moa.domain.notice.service.NoticeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notices")
class NoticeController(
    private val noticeService: NoticeService
) {
    @Operation(summary = "공지사항 목록 조회")
    @GetMapping
    fun getNotices(@PageableDefault pageable: Pageable) = noticeService.getNotices(pageable)

    @Operation(summary = "공지사항 상세 조회")
    @GetMapping("/{noticeId}")
    fun getNotice(@PathVariable noticeId: Long) = noticeService.getNotice(noticeId)

    @Operation(summary = "공지사항 등록")
    @PostMapping
    fun createNotice(@RequestBody request: NoticeCreateRequest) = noticeService.createNotice(request)
}