package com.moa.domain.notice.domain.entity

import com.moa.global.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "notice")
class Notice(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "important", nullable = false)
    val important: Boolean,
): BaseTimeEntity()