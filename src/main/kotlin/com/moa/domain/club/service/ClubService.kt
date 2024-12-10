package com.moa.domain.club.service

import com.moa.domain.club.dto.response.ClubResponse
import com.moa.domain.club.repository.ClubRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ClubService(
    private val clubRepository: ClubRepository
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
}