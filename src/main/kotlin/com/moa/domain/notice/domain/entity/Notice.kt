package com.moa.domain.notice.domain.entity

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

    @Column(name = "must_read", nullable = false)
    val mustRead: Boolean,
)