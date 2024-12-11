package com.moa.domain.club.domain.entity

import com.moa.domain.club.domain.enums.ClubApplyStatus
import com.moa.domain.user.domain.entity.User
import com.moa.global.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "club_applies")
class ClubApply(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    val club: Club,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "introduction", nullable = false)
    val introduction: String, // 자기소개

    @Column(name = "experience", nullable = false)
    val experience: String, // 경험

    @Column(name = "motivation", nullable = false)
    val motivation: String, // 동기

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: ClubApplyStatus = ClubApplyStatus.PENDING
): BaseTimeEntity() {
    fun cancel() {
        status = ClubApplyStatus.CANCELED
    }
}