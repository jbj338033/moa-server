package com.moa.domain.user.dto.response

import com.moa.domain.user.domain.entity.User
import java.util.UUID

data class UserMeResponse(
    val id: UUID,
    val dodamId: String,
    val grade: Int,
    val room: Int,
    val number: Int,
    val name: String,
    val profileImage: String?,
    val role: String,
    val email: String
) {
    companion object {
        fun of(user: User) = UserMeResponse(
            id = user.id!!,
            dodamId = user.dodamId,
            grade = user.grade,
            room = user.room,
            number = user.number,
            name = user.name,
            profileImage = user.profileImage,
            role = user.role.name,
            email = user.email
        )
    }
}