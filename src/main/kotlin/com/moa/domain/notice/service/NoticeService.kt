package com.moa.domain.notice.service

import com.moa.domain.notice.domain.entity.Notice
import com.moa.domain.notice.dto.request.NoticeCreateRequest
import com.moa.domain.notice.dto.response.NoticeResponse
import com.moa.domain.notice.repository.NoticeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeService(private val noticeRepository: NoticeRepository) {
    @Transactional(readOnly = true)
    fun getNotices(pageable: Pageable): Page<NoticeResponse> {
        val notices = noticeRepository.findAll(pageable)

        return notices.map { NoticeResponse.of(it) }
    }

    @Transactional(readOnly = true)
    fun getNotice(noticeId: Long): NoticeResponse {
        val notice = noticeRepository.findByIdOrNull(noticeId)
            ?: throw IllegalArgumentException("해당 공지사항이 존재하지 않습니다.")

        return NoticeResponse.of(notice)
    }

    @Transactional
    fun createNotice(request: NoticeCreateRequest) {
        val notice = Notice(
            title = request.title,
            content = request.content,
            important = request.important
        )

        noticeRepository.save(notice)
    }
}