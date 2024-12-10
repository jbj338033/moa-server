package com.moa.infra.dauth

data class UserInfoResponse(
    val status: Int,
    val message: String,
    val data: UserInfoData
)

data class UserInfoData(
    val uniqueId: String,
    val grade: Int,
    val room: Int,
    val number: Int,
    val name: String,
    val profileImage: String?,
    val role: String,
    val email: String,
)