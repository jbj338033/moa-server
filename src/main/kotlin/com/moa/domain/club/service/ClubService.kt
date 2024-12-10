package com.moa.domain.club.service

import com.moa.domain.club.domain.entity.ClubApply
import com.moa.domain.club.dto.request.ClubApplyRequest
import com.moa.domain.club.dto.response.ClubResponse
import com.moa.domain.club.repository.ClubApplyRepository
import com.moa.domain.club.repository.ClubRepository
import com.moa.global.security.holder.SecurityHolder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ClubService(
    private val clubRepository: ClubRepository,
    private val clubApplyRepository: ClubApplyRepository,
    private val securityHolder: SecurityHolder
) {
    @Transactional(readOnly = true)
    fun getClubs(pageable: Pageable): Page<ClubResponse> {
        val clubs = clubRepository.findAll(pageable)

        return clubs.map { ClubResponse.of(it) }
    }

    @Transactional(readOnly = true)
    fun getClub(clubId: UUID): ClubResponse {
        val club = clubRepository.findByIdOrNull(clubId)
            ?: throw IllegalArgumentException("해당 동아리가 존재하지 않습니다.")

        return ClubResponse.of(club)
    }

    @Transactional
    fun applyClub(clubId: UUID, request: ClubApplyRequest) {
        val club = clubRepository.findByIdOrNull(clubId)
            ?: throw IllegalArgumentException("해당 동아리가 존재하지 않습니다.")
        val user = securityHolder.user

        if (clubApplyRepository.existsByClubAndUser(club, user)) {
            throw IllegalArgumentException("이미 지원한 동아리입니다.")
        }

        val apply = ClubApply(
            club = club,
            user = user,
            introduction = request.introduction,
            experience = request.experience,
            motivation = request.motivation
        )

        clubApplyRepository.save(apply)
    }

    @Transactional
    fun cancelApply(clubId: UUID) {
        val club = clubRepository.findByIdOrNull(clubId)
            ?: throw IllegalArgumentException("해당 동아리가 존재하지 않습니다.")
        val user = securityHolder.user

        val apply = clubApplyRepository.findByClubAndUser(club, user)
            ?: throw IllegalArgumentException("지원하지 않은 동아리입니다.")

        apply.cancel()

        clubApplyRepository.save(apply)
    }
}