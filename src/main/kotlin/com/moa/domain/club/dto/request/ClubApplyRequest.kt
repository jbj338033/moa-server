package com.moa.domain.club.dto.request

data class ClubApplyRequest(
    val introduction: String, // 자기소개
    val experience: String, // 경험
    val motivation: String // 동기
)