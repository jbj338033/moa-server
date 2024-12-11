package com.moa.domain.auth.service

import com.moa.domain.auth.dto.request.LoginRequest
import com.moa.domain.auth.dto.request.ReissueRequest
import com.moa.domain.auth.dto.response.LoginResponse
import com.moa.domain.user.domain.entity.User
import com.moa.domain.user.domain.enums.UserRole
import com.moa.domain.user.repository.UserRepository
import com.moa.global.security.jwt.dto.JwtResponse
import com.moa.global.security.jwt.provider.JwtProvider
import com.moa.infra.dauth.DAuthClient
import com.moa.infra.dauth.UserInfoData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val dauthClient: DAuthClient,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
) {
    @Transactional
    fun login(request: LoginRequest): JwtResponse {
        val code = dauthClient.getCode(request.id, request.password)
        val token = dauthClient.getAccessToken(code)
        val info = dauthClient.getUserInfo(token.accessToken)

        val user = userRepository.findByDodamId(info.uniqueId)
            ?.apply { updateUserInfo(info) }
            ?: User(
                dodamId = info.uniqueId,
                grade = info.grade,
                room = info.room,
                number = info.number,
                name = info.name,
                profileImage = info.profileImage,
                role = UserRole.valueOf(info.role),
                email = info.email,
            )

        userRepository.save(user)

        return jwtProvider.generateToken(user.dodamId)
    }

    private fun User.updateUserInfo(info: UserInfoData) {
        grade = info.grade
        room = info.room
        number = info.number
        name = info.name
        profileImage = info.profileImage
        email = info.email
        role = UserRole.valueOf(info.role)
    }

    fun reissue(request: ReissueRequest): JwtResponse {
        val dodamId = jwtProvider.getSubject(request.refreshToken)
        return jwtProvider.generateToken(dodamId)
    }
}