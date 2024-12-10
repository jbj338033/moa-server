package com.moa.global.security.jwt.provider

import com.moa.domain.user.repository.UserRepository
import com.moa.global.security.details.CustomUserDetails
import com.moa.global.security.jwt.config.JwtProperties
import com.moa.global.security.jwt.dto.JwtResponse
import com.moa.global.security.jwt.enums.JwtType
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.spec.SecretKeySpec

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository
) {
    private val key = SecretKeySpec(
        jwtProperties.secretKey.toByteArray(StandardCharsets.UTF_8),
        Jwts.SIG.HS512.key().build().algorithm
    )

    fun generateToken(dodamId: String) = JwtResponse(
        accessToken = generateAccessToken(dodamId),
        refreshToken = generateRefreshToken(dodamId)
    )

    private fun generateAccessToken(dodamId: String) = Date().let {
        Jwts.builder()
            .header()
            .type(JwtType.ACCESS.name)
            .and()
            .subject(dodamId)
            .issuedAt(it)
            .issuer(jwtProperties.issuer)
            .expiration(Date(it.time + jwtProperties.accessTokenExpiration))
            .signWith(key)
            .compact()
    }

    private fun generateRefreshToken(dodamId: String) = Date().let {
        Jwts.builder()
            .header()
            .type(JwtType.REFRESH.name)
            .and()
            .subject(dodamId)
            .issuedAt(it)
            .issuer(jwtProperties.issuer)
            .expiration(Date(it.time + jwtProperties.refreshTokenExpiration))
            .signWith(key)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val user = userRepository.findByDodamId(getSubject(token)) ?: throw IllegalArgumentException("Invalid token")
        val details = CustomUserDetails(user)

        return UsernamePasswordAuthenticationToken(details, null, details.authorities)
    }

    fun getSubject(token: String) = getClaims(token).subject

    fun extractToken(request: HttpServletRequest) = request.getHeader(jwtProperties.header)?.removePrefix(jwtProperties.prefix)

    fun getType(token: String) = JwtType.valueOf(Jwts.parser().verifyWith(key).requireIssuer(jwtProperties.issuer).build().parseSignedClaims(token).header.type)

    private fun getClaims(token: String) = Jwts.parser().verifyWith(key)
        .requireIssuer(jwtProperties.issuer)
        .build()
        .parseSignedClaims(token)
        .payload
}