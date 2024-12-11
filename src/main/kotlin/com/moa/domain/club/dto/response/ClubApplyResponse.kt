package com.moa.domain.club.dto.response

import com.moa.domain.club.domain.entity.ClubApply
import com.moa.domain.club.domain.enums.ClubApplyStatus

data class ClubApplyResponse(
    val id: Long,
    val club: ClubResponse,
    val introduction: String,
    val experience: String,
    val motivation: String,
    val status: ClubApplyStatus
) {
    companion object {
        fun of(apply: ClubApply) = ClubApplyResponse(
            id = apply.id!!,
            club = ClubResponse.of(apply.club),
            introduction = apply.introduction,
            experience = apply.experience,
            motivation = apply.motivation,
            status = apply.status
        )
    }
}