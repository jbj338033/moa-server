package com.moa.domain.club.repository

import com.moa.domain.club.domain.entity.Club
import com.moa.domain.club.domain.entity.ClubApply
import com.moa.domain.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface ClubApplyRepository: JpaRepository<ClubApply, Long> {
    fun findAllByUser(user: User): List<ClubApply>
    fun findByClubAndUser(club: Club, user: User): ClubApply?

    fun existsByClubAndUser(club: Club, user: User): Boolean
}