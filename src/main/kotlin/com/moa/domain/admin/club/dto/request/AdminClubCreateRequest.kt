package com.moa.domain.admin.club.dto.request

import java.util.UUID

data class AdminClubCreateRequest(
    val name: String,
    val description: String,
    val image: String,
    val memberIds: List<UUID>,
    val masterId: UUID
)