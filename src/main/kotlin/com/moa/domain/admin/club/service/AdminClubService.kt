package com.moa.domain.admin.club.service

import com.moa.domain.admin.club.dto.request.AdminClubCreateRequest
import com.moa.domain.club.domain.entity.Club
import com.moa.domain.club.domain.enums.ClubMemberRole
import com.moa.domain.club.repository.ClubRepository
import com.moa.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminClubService(
    private val userRepository: UserRepository,
    private val clubRepository: ClubRepository
) {
    @Transactional
    fun createClub(request: AdminClubCreateRequest) {
        val club = Club(
            name = request.name,
            description = request.description,
            image = request.image
        )

        request.memberIds.forEach {
            val user = userRepository.findByIdOrNull(it) ?: return

            club.addMember(user, ClubMemberRole.MEMBER)
        }

        val master = userRepository.findByIdOrNull(request.masterId) ?: return

        club.addMember(master, ClubMemberRole.MASTER)

        clubRepository.save(club)
    }
}