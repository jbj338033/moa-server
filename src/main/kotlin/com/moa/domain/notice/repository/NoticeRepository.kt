package com.moa.domain.notice.repository

import com.moa.domain.notice.domain.entity.Notice
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository: JpaRepository<Notice, Long> {
}