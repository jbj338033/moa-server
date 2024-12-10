package com.moa.domain.club.domain.entity

import com.moa.domain.club.domain.enums.ClubMemberRole
import com.moa.domain.user.domain.entity.User
import jakarta.persistence.*

@Entity
@Table(name = "club_members")
class ClubMember(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    val club: Club,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: ClubMemberRole
)