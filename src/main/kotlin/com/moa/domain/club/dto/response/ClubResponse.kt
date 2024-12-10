package com.moa.domain.club.dto.response

import com.moa.domain.club.domain.entity.Club
import java.util.UUID

data class ClubResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val image: String,
    val memberCount: Int
) {
    companion object {
        fun of(club: Club) = ClubResponse(
            id = club.id!!,
            name = club.name,
            description = club.description,
            image = club.image,
            memberCount = club.members.size
        )
    }
}